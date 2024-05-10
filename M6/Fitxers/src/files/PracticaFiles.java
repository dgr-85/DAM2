package files;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PracticaFiles {

	private static List<File> matchingDirectories = new ArrayList<>();
	private static Scanner sc = new Scanner(System.in);

	private static FileFilter filter = new FileFilter() {
		@Override
		public boolean accept(File pathname) {
			return pathname.isDirectory() && !Files.isSymbolicLink(pathname.toPath());
		}
	};

	public static void main(String[] args) throws IOException {
		File file = new File("Documents/Exercicis_M6/datos.txt");
		System.out.println("Searching file " + file.getPath() + "...");
		if (file.exists()) {
			System.out.println("File found.");
			printFileStats(file);
		} else {
			System.out.println("File not found. Creating...");
			String[] routeSteps = String.valueOf(file).split("/");
			String lastStep = routeSteps[routeSteps.length - 1];
			String directoriesRoute = String.join(File.separator, Arrays.copyOf(routeSteps, routeSteps.length - 1));
			Files.createDirectories(Paths.get(directoriesRoute));
			directoriesRoute += (File.separator + lastStep);
			if (file.createNewFile()) {
				System.out.println("File created.");
				printFileStats(file);
			} else {
				System.out.println("Error while creating file.");
			}
		}

		/* ========================================================================= */
		System.out.println("Listing contents of current directory...");
		checkDirectoryContents(".");
		matchingDirectories.clear();
		System.out.println("Enter a directory name:");
		String customDirectory = sc.nextLine();
		System.out.println("Searching directory " + customDirectory + "...");
		searchDirectoryByName(Paths.get(System.getProperty("user.dir")).getRoot().toString(), customDirectory);
		manageMatchingDirectories(matchingDirectories, customDirectory);
		sc.close();
	}

	public static void printFileStats(File file) throws IOException {
		System.out.println("Path: " + file.getPath());
		System.out.println("Absolute Path: " + file.getAbsolutePath());
		System.out.println("Canonical Path: " + file.getCanonicalPath());
		System.out.println("Size: " + file.length() + " bytes");
	}

	public static String checkFileType(File file) {
		if (file.isDirectory()) {
			return "Directory" + System.lineSeparator();
		} else if (file.isFile()) {
			return "File" + System.lineSeparator();
		} else {
			return "Unknown type" + System.lineSeparator();
		}
	}

	public static void checkDirectoryContents(String directory) {
		File file = new File(directory);
		File[] subFiles = file.listFiles();
		for (File f : subFiles) {
			System.out.print(f.getName() + " - " + checkFileType(f));
		}
	}

	public static void manageMatchingDirectories(List<File> directories, String matchingName) {
		if (directories.size() == 0) {
			System.out.println("Directory " + matchingName + " not found.");
		} else if (directories.size() == 1) {
			System.out.println("Listing contents of directory " + matchingName + ":");
			checkDirectoryContents(matchingName);
		} else {
			System.out.println(directories.size() + " directories named " + matchingName + " found. Locations:");
			for (int i = 0; i < directories.size(); i++) {
				System.out.println((i + 1) + ") " + directories.get(i).getAbsolutePath());
			}
			System.out.println("Choose one of them by number:");
			Integer chosenDirectory = chooseNumber(directories.size());
			checkDirectoryContents(directories.get(chosenDirectory - 1).getAbsolutePath());
		}
	}

	public static Integer chooseNumber(Integer number) {
		while (true) {
			try {
				Integer chosenNumber = Integer.parseInt(sc.nextLine());
				if (chosenNumber < 1 || chosenNumber > number) {
					System.out.println("You must choose a number between 1 and " + number + ".");
				} else {
					return chosenNumber;
				}
			} catch (Exception e) {
				System.out.println("Invalid number.");
			}
		}
	}

	public static void searchDirectoryByName(String directory, String matchingName) throws IOException {
		File file = new File(directory);
		if (file.isDirectory() && !Files.isSymbolicLink(file.toPath()) && file.getName().equals(matchingName)) {
			matchingDirectories.add(file);
		}
		File[] subFiles = file.listFiles(filter);
		if (subFiles != null && subFiles.length > 0) {
			for (File f : subFiles) {
				searchDirectoryByName(f.getCanonicalPath(), matchingName);
			}
		}
	}
}
