package base64.message;

import java.io.File;
import java.io.FileFilter;
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

public class Signatory {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String alias = "fitxers";
		String password = "password";
		KeyStore ks;
		if ((ks = loadKeyStore("src/key.jks", password)) != null) {
			Key key = getKeyFromKeyStore(ks, alias, password);
			if (key != null && key instanceof PrivateKey) {
				Certificate cert;
				if ((cert = getCertificateFromKeyStore(ks, alias)) != null) {
					System.out.println("Certificate obtained successfully: " + System.lineSeparator() + cert);
					PublicKey publicKey = cert.getPublicKey();
					System.out.println("Public key obtained successfully: " + System.lineSeparator() + publicKey);
					System.out.println("Please, enter the path to the directory whose contents you wish to sign:");
					File directory = getDirectoryFromUser(sc);
					printSignaturesFromDirectoryContents(directory, key);
				} else {
					System.out.println("Error while obtaining certificate. Alias might be wrong.");
				}
			} else {
				System.out.println("Error while obtaining key. Alias and/or password might be wrong.");
			}
		} else {
			System.out.println("Error while loading KeyStore.");
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

	public static File getDirectoryFromUser(Scanner sc) {
		while (true) {
			String dir = sc.nextLine();
			File directory = new File(dir);
			if (directory.exists() && directory.isDirectory()) {
				return directory;
			} else {
				System.out.println("Directory " + directory.getName() + " not found.");
			}
		}
	}

	public static void printSignaturesFromDirectoryContents(File directory, Key key) {
		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isFile() && !Files.isSymbolicLink(pathname.toPath());
			}
		};
		System.out.println("Directory " + directory.getName() + " found. Number of files among contents: "
				+ directory.listFiles(filter).length);
		for (File file : directory.listFiles(filter)) {
			try {
				byte[] fileContent = Files.readAllBytes(file.toPath());
				Signature signature = Signature.getInstance("SHA256withRSA");
				signature.initSign((PrivateKey) key);
				signature.update(fileContent);
				byte[] digitalSignature = signature.sign();
				String base64Signature = Base64.getEncoder().encodeToString(digitalSignature);
				System.out.println("File: " + file.getName());
				System.out.println("Signature: " + base64Signature);
			} catch (IOException | NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
				e.printStackTrace();
			}
		}
	}

}
