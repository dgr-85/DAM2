package message.hack;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class MainProcess {

	static byte[] decryptedData = null;

	public static void main(String[] args) {
		String encodedMessage = "0xB3, 0xF9, 0xB2, 0x72, 0xA9, 0x07, 0x1F, 0xA4, 0x91, 0xA0, 0x1B, 0x48, 0x0D, 0x4F, 0xEC, 0xA0, 0x56, 0x54, 0x4A, 0xBB, 0x3C, 0xF1, 0xFF, 0x4F, 0xD0, 0x7F, 0xA6, 0x9B, 0x8F, 0x74, 0xF6, 0x8F";
		int password = 0;
		String salt = "128";
		Boolean eureka = false;

		while (!eureka) {
			SecretKey sKey = generateSecretKey(String.format("%05d", password), salt);
			eureka = decryptData(sKey, encodedMessage.getBytes());
			password++;
			System.out.println(String.format("%05d", password));
		}

		try {
			System.out.println(new String(decryptedData, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println("Error transforming bytes to string.");
		}
	}

	public static SecretKey generateSecretKey(String password, String salt) {
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
			return new SecretKeySpec(keyFactory.generateSecret(spec).getEncoded(), "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Boolean decryptData(SecretKey key, byte[] data) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			decryptedData = cipher.doFinal(data);
			return true;
		} catch (Exception ex) {
			// System.err.println("Could not decrypt the data: " + ex);
			return false;
		}

	}
}
