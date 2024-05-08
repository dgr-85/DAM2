package secret.encode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MainProcess {

	public static final byte[] IV_PARAM = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B,
			0x0C, 0x0D, 0x0E, 0x0F };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println(System.getProperty("user.dir"));
		System.out.println("Enter file name:");
		String fileName = sc.nextLine();
		File initialFile = createFileFromName(fileName);
		if (initialFile == null) {
			System.out.println("File " + fileName + " not found.");
		} else {
			System.out.println("Enter password:");
			String password = sc.nextLine();
			SecretKey sKey = generateSecretKey192(password);
			File processedFile = null;
			if (initialFile.getName().endsWith(".aes")) {
				processedFile = processFile(sKey, initialFile, Cipher.DECRYPT_MODE);
			} else {
				processedFile = processFile(sKey, initialFile, Cipher.ENCRYPT_MODE);
			}
			if (processedFile != null) {
				System.out.println("File processed succesfully. Location: " + processedFile.getPath());
			} else {
				System.out.println("File processing aborted.");
			}
		}
		sc.close();
	}

	private static File createFileFromName(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			return file;
		}
		return null;
	}

	private static SecretKey generateSecretKey192(String password) {
		SecretKey sKey = null;
		try {
			byte[] data = password.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(data);
			byte[] key = Arrays.copyOf(hash, 192 / 8);
			sKey = new SecretKeySpec(key, "AES");
		} catch (Exception ex) {
			System.err.println("Error while generating AES key:" + ex);
		}
		return sKey;
	}

	private static File processFile(SecretKey sKey, File fileToProcess, int mode) {
		File processedFile;
		if (mode == Cipher.ENCRYPT_MODE) {
			processedFile = new File(fileToProcess.getName() + ".aes");
		} else {
			processedFile = new File(fileToProcess.getName().replace(".aes", ""));
		}
		try (FileInputStream in = new FileInputStream(fileToProcess);
				FileOutputStream out = new FileOutputStream(processedFile)) {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
			cipher.init(mode, sKey, iv);
			byte[] buffer = new byte[8192];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				byte[] outputFragment = cipher.update(buffer, 0, bytesRead);
				if (outputFragment != null) {
					out.write(outputFragment);
				}
			}
			byte[] lastFragment = cipher.doFinal();
			if (lastFragment != null) {
				out.write(lastFragment);
			}
		} catch (BadPaddingException ex) {
			System.out.println("Incorrect password.");
			processedFile.delete();
			processedFile = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return processedFile;
	}

}
