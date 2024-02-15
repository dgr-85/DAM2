package uf3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ResourceSaver {

	Map<Pattern, String> folders;

	public ResourceSaver() {
		this.folders = new HashMap<>();
	}

	public void addResourceType(String regex, String folder) {
		if (!folders.containsKey(Pattern.compile(regex))) {
			folders.put(Pattern.compile(regex), folder);
		}
	}

	public void createFolderTree() throws IOException {
		for (String str : folders.values()) {
			File f = new File(str);
			if (f.exists()) {
				System.out.println("Folder '" + str + "' already exists.");
			} else if (!f.exists() && !f.mkdirs()) {
				throw new IOException("Error creating folder " + str + ".");
			} else {
				System.out.println("Folder '" + str + "' created.");
			}
		}
	}

	public void saveResource(String resource) throws MalformedURLException, IOException {
		URL url = new URL(resource);
		URLConnection con = url.openConnection();
		String contentType = con.getContentType();

		for (Map.Entry<Pattern, String> entry : folders.entrySet()) {
			Pattern key = entry.getKey();
			String value = entry.getValue();

			if (key.matcher(contentType).matches()) {
				String path = url.getPath();
				path = path.substring(path.lastIndexOf('/') + 1);
				String completePath = value + File.separator + path;

				if (Files.exists(Paths.get(value).resolve(path))) {
					System.out.println("Resource '" + path + "' found in " + completePath + ". Download cancelled.");
				} else {
					try (InputStream inStream = url.openStream();
							FileOutputStream outStream = new FileOutputStream(completePath)) {
						byte[] buffer = new byte[8192];
						int currentBytes = 0;
						int totalBytes = 0;

						while ((currentBytes = inStream.read(buffer)) != -1) {
							outStream.write(buffer, 0, currentBytes);
							totalBytes += currentBytes;
						}
						System.out.println(totalBytes + " bytes read.");
						System.out.println("Resource saved in " + completePath + ".");
					}
				}
				return;
			}
		}
	}

}
