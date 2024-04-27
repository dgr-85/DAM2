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
		Integer password = 0;
		Integer size = 256;
		Boolean resultIsUTF8Encoded = false;

		System.out.print("Searching");
		while (password <= 99999 && !resultIsUTF8Encoded) {
			SecretKey sKey = generateSecretKey(String.format("%05d", password), size);
			decryptData(sKey, encodedMessage);
			if (!(resultIsUTF8Encoded = (decryptedString != null && decryptedString.matches("[\\p{Print}]+"))))
				password++;
			if (password % 500 == 0) {
				System.out.print(".");
			}
		}
		if (password > 99999) {
			System.out.println(System.lineSeparator()
					+ "All possible passwords have proven ineffective. Code cracking has failed.");
		} else {
			System.out.println(System.lineSeparator() + "Password found: " + String.format("%05d", password));
			System.out.println("Message decrypted: " + decryptedString);
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
				System.err.println("Error while generating AES key:" + ex);
			}
		}
		return sKey;
	}

	public static void decryptData(SecretKey key, byte[] data) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			decryptedData = cipher.doFinal(data);
			decryptedString = new String(decryptedData, StandardCharsets.UTF_8);
		} catch (Exception ex) {
		}
	}
}
