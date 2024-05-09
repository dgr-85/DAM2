package files;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PracticaFiles {

	private static List<File> matchingDirectories = new ArrayList<>();

	private static FileFilter filter = new FileFilter() {
		@Override
		public boolean accept(File pathname) {
			return pathname.isDirectory() && !Files.isSymbolicLink(pathname.toPath());
		}
	};

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
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
		// checkDirectory(".");
		for (File file2 : matchingDirectories) {
			System.out.println(file2.getName());
		}
		matchingDirectories.clear();
		System.out.println("Enter a directory name:");
		String customDirectory = sc.nextLine();
		System.out.println("Listing contents of directory " + customDirectory + "...");
		searchDirectoryByName(Paths.get(System.getProperty("user.dir")).getRoot().toString(), customDirectory);
		System.out.println("Contents in matchingDirectories:");
		for (File file3 : matchingDirectories) {
			System.out.println(file3.getAbsolutePath());
		}

		sc.close();
	}

	public static void printFileStats(File file) throws IOException {
		System.out.println("Path: " + file.getPath());
		System.out.println("Absolute Path: " + file.getAbsolutePath());
		System.out.println("Canonical Path: " + file.getCanonicalPath());
		System.out.println("Size: " + file.length() + " bytes");
	}

	public static void checkFileType(File file) {
		if (file.isDirectory()) {
			System.out.print("Directory");
		} else if (file.isFile()) {
			System.out.print("File");
		} else {
			System.out.print("Unknown type");
		}
		System.out.print(System.lineSeparator());
	}

	public static void checkDirectoryContents(String path, List<File> matchingDirectories, String searchingDirectory) {
		String absolutePath = new File(path).getAbsolutePath();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(absolutePath))) {
			for (Path dirFile : stream) {
				File pathFile = new File(dirFile.toString());
				// System.out.print(pathFile.getName() + " - ");
				// checkFileType(pathFile);
				if (pathFile.isDirectory()) {
					if (pathFile.getName().equals(searchingDirectory)) {
						matchingDirectories.add(pathFile);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Directory " + path + " not found.");
		}
	}

	public static List<File> checkDirectoryTree(String directory) {
		List<File> matchingDirectories = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("/"))) {
			for (Path path : stream) {
				File file = path.toFile();
				if (file.isDirectory()) {
					matchingDirectories.add(file);
					searchDirectoryByName(file.getAbsolutePath(), directory);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matchingDirectories;
	}

	public static void searchDirectoryByName(String directory, String name) throws IOException {
		File file = new File(directory);
		if (file.isDirectory() && !Files.isSymbolicLink(file.toPath()) && file.getName().equals(name)) {
			matchingDirectories.add(file);
		}
		File[] subFiles = file.listFiles(filter);
		if (subFiles != null && subFiles.length > 0) {
			for (File f : subFiles) {
				searchDirectoryByName(f.getCanonicalPath(), name);
			}
		}
	}
}
