package files;

import java.io.File;
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

	private static List<File> allDirectories = new ArrayList<>();

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
		System.out.println("Listing contents of current directory...");
		checkDir(".");
		for (File file2 : allDirectories) {
			System.out.println(file2.getName());
		}
		System.out.println("Enter a directory name:");
		String customDirectory = sc.nextLine();
		System.out.println("Listing contents of directory " + customDirectory + "...");
		List<File> directories = checkDirectoryTree(customDirectory);
		System.out.println("Directories named " + customDirectory + ": " + directories.size());
		if (directories.size() > 0) {
			for (File f : directories) {
				System.out.println(f.getName());
			}
		}
		for (File file3 : allDirectories) {
			System.out.println(file3.getName());
		}
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

	public static void checkDirectory(String path, List<File> matchingDirectories, String searchingDirectory) {
		String absolutePath = new File(path).getAbsolutePath();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(absolutePath))) {
			for (Path dirFile : stream) {
				File pathFile = new File(dirFile.toString());
				allDirectories.clear();
				// System.out.print(pathFile.getName() + " - ");
				// checkFileType(pathFile);
				if (pathFile.isDirectory()) {
					if (pathFile.getName().equals(searchingDirectory)) {
						matchingDirectories.add(pathFile);
					}
					checkDir(pathFile.getName());
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
				File file = new File(path.toString());
				if (file.isDirectory() && file.getName().equals(directory)) {
					// matchingDirectories.add(file);
					checkDir(file.getName());
				}
				// System.out.println(file.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matchingDirectories;
	}

	public static void checkDir(String dir) throws IOException {
		File file = new File(dir);
		if (file.isDirectory()) {
			allDirectories.add(file);
			File[] subFiles = file.listFiles();
			for (File f : subFiles) {
				if (f.isDirectory()) {
					checkDir(f.getCanonicalPath());
				}
			}
		}
	}

}
