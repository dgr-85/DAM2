package base64.message;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.Base64;

public class Signatory {
	public static void main(String[] args) {
		String str = "Fem una prova";
		String codedStr = Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
		System.out.println(str + ": " + codedStr);

		try {
			KeyStore ks = loadKeyStore("src/key.jks", "password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static KeyStore loadKeyStore(String ksFile, String ksPwd) throws Exception {
		KeyStore ks = KeyStore.getInstance("JCEKS"); // Gets a keystore instance of JCEKS type
		File f = new File(ksFile); // File instance related to the file path
		if (f.isFile()) {
			// It is a file
			FileInputStream in = new FileInputStream(f); // Prepare a file input stream to read the file
			ks.load(in, ksPwd.toCharArray()); // Loads the keystore object with the data from the file. Uses the
			// password needed to access the data
		}
		return ks; // The keystore object corresponding to the keystore file
	}
}
