package base64.message;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.Scanner;

public class Validator {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String alias = "fitxers";
		String password = "password";
		KeyStore ks;
		if ((ks = loadKeyStore("src/key.jks", password)) != null) {
			Key key = getKeyFromKeyStore(ks, alias, password);
			Certificate cert = null;
			if (key != null && key instanceof PrivateKey) {
				if ((cert = getCertificateFromKeyStore(ks, alias)) != null) {
					System.out.println("Certificate obtained successfully: " + System.lineSeparator() + cert);
					PublicKey publicKey = cert.getPublicKey();
					System.out.println("Public key obtained successfully: " + System.lineSeparator() + publicKey);
					System.out.println("Please, enter the path to the file you wish to validate:");
					File file = getFileFromUser(sc);
					System.out.println("Please, enter the base64 signature associated to this file:");
					String signature = sc.nextLine();
					if (checkFileAndSignature(file, signature, cert)) {
						System.out.println("File " + file.getName() + " verified. Signature is valid.");
					} else {
						System.out.println("Incorrect signature for file " + file.getName() + ".");
					}
				} else {
					System.out.println("Error while obtaining certificate. Alias might be wrong.");
				}
			} else {
				System.out.println("Error while obtaning key. Alias and/or password might be wrong.");
			}
		} else {
			System.out.println("Error while loading the KeyStore.");
		}
		sc.close();
	}

	public static KeyStore loadKeyStore(String ksFile, String ksPwd) {
		KeyStore ks;
		try {
			ks = KeyStore.getInstance("JKS");
			File f = new File(ksFile);
			if (f.isFile()) {
				FileInputStream in = new FileInputStream(f);
				ks.load(in, ksPwd.toCharArray());
			}
			return ks;
		} catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException e) {
			return null;
		}
	}

	public static Key getKeyFromKeyStore(KeyStore ks, String alias, String password) {
		try {
			return ks.getKey(alias, password.toCharArray());
		} catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static Certificate getCertificateFromKeyStore(KeyStore ks, String alias) {
		try {
			return ks.getCertificate(alias);
		} catch (KeyStoreException e) {
			return null;
		}
	}

	public static File getFileFromUser(Scanner sc) {
		while (true) {
			String str = sc.nextLine();
			File file = new File(str);
			if (file.exists() && file.isFile()) {
				return file;
			} else {
				System.out.println("File " + file.getName() + " not found.");
			}
		}
	}

	public static boolean checkFileAndSignature(File file, String strSignature, Certificate certificate) {
		try {
			byte[] fileToBytes = Files.readAllBytes(file.toPath());
			byte[] signatureToBytes = Base64.getDecoder().decode(strSignature);
			Signature signature = Signature.getInstance("SHA256withRSA");
			signature.initVerify(certificate.getPublicKey());
			signature.update(fileToBytes);
			return signature.verify(signatureToBytes);
		} catch (IOException e) {
			System.out.println("Error while translating file to byte array.");
		} catch (NoSuchAlgorithmException e) {
			System.out.println(
					"Error while obtaining signature. The specified algorithm might be wrong or lack providers implementing a signature for it.");
		} catch (InvalidKeyException e) {
			System.out.println("Error while verifying signature. The provided public key might be invalid.");
		} catch (SignatureException e) {
			System.out.println("Error while updating file. Signature might not have been initialized correctly.");
		}
		return false;
	}
}
