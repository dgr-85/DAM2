package message.hack;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MainProcess {

	static byte[] decryptedData = null;
	static String decryptedString = null;

	public static void main(String[] args) {
		byte[] encodedMessage = { (byte) 0xB3, (byte) 0xF9, (byte) 0xB2, 0x72, (byte) 0xA9, 0x07, 0x1F, (byte) 0xA4,
				(byte) 0x91, (byte) 0xA0, 0x1B, 0x48, 0x0D, 0x4F, (byte) 0xEC, (byte) 0xA0, 0x56, 0x54, 0x4A,
				(byte) 0xBB, 0x3C, (byte) 0xF1, (byte) 0xFF, 0x4F, (byte) 0xD0, 0x7F, (byte) 0xA6, (byte) 0x9B,
				(byte) 0x8F, 0x74, (byte) 0xF6, (byte) 0x8F };
		int password = 0;
		int size = 256;
		Boolean eureka = false;
		Boolean itWorks = false;

		System.out.print("Searching");
		while (!eureka && password <= 99999 && !itWorks) {
			SecretKey sKey = generateSecretKey(String.format("%05d", password), size);
			eureka = decryptData(sKey, encodedMessage);

			password++;

			itWorks = (decryptedString != null && decryptedString.matches("[\\p{Print}]+"));

			if (password % 10 == 0) {
				System.out.print(".");
			}
		}
		if (password > 99999) {
			System.out.println(
					System.lineSeparator() + "All possible passwords have proven wrong. Code cracking has failed.");
		} else {
			System.out.println(System.lineSeparator() + String.format("%05d", password));
			System.out.println(decryptedString);
		}
	}

	public static SecretKey generateSecretKey(String password, int size) {
		SecretKey sKey = null;
		if ((size == 128) || (size == 192) || (size == 256)) {
			try {
				byte[] data = password.getBytes("UTF-8");
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				byte[] hash = md.digest(data);
				byte[] key = Arrays.copyOf(hash, size / 8);
				sKey = new SecretKeySpec(key, "AES");
			} catch (Exception ex) {
				System.err.println("Could not generate the AES key:" + ex);
			}
		}
		return sKey;
	}

	public static Boolean decryptData(SecretKey key, byte[] data) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			decryptedData = cipher.doFinal(data);
			decryptedString = new String(decryptedData, StandardCharsets.UTF_8);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
}
