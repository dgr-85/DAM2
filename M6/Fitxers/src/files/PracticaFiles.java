package files;

import java.io.File;
import java.io.IOException;

public class PracticaFiles {

	public static void main(String[] args) throws IOException {
		File file = new File("Documents/Exercicis_M6/datos.txt");
		if (file.exists()) {
			System.out.println(file.getPath());
			System.out.println(file.getAbsolutePath());
			System.out.println(file.getCanonicalPath());
			System.out.println(file.length());
		} else {
			System.out.println("File not found.");
			if (file.createNewFile()) {
				System.out.println("File created.");
				System.out.println(file.getPath());
				System.out.println(file.getAbsolutePath());
				System.out.println(file.getCanonicalPath());
				System.out.println(file.length());
			}

		}
	}

}
