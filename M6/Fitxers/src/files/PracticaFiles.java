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
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("."))) {
			for (Path dirFile : stream) {
				File pathFile = new File(dirFile.toString());
				System.out.print(pathFile.getName() + " - ");
				checkFileType(pathFile);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		System.out.println("Enter an absolute path to a directory:");
		String customDirectory = sc.nextLine();
		System.out.println("Listing contents of directory " + customDirectory + "...");
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(customDirectory))) {
			for (Path dirFile : stream) {
				File pathFile = new File(dirFile.toString());
				System.out.print(pathFile.getName() + " - ");
				checkFileType(pathFile);
			}
		} catch (Exception e) {
			System.out.println("Directory " + customDirectory + " not found.");
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

	public List<File> recursiveSubCheck(File file){
		List<File> subFiles=new ArrayList<>();
		Files.
		return subFiles;				
	}

}
