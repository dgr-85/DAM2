package uf3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ResourceSaver {

	Map<Pattern, String> folders;

	public ResourceSaver() {
		this.folders = new HashMap<>();
	}

	public void addResourceType(String regex, String folder) {
		Pattern p = Pattern.compile(regex);
		if (!folders.containsKey(p)) {
			folders.put(p, folder);
		}
	}

	public void createFolderTree() throws IOException {
		for (String str : folders.values()) {
			File f = new File(str);
			if (!f.exists() && !f.mkdirs()) {
				throw new IOException("Error creating folder " + str + ".");
			}
		}
		File f = new File("unknown");
		f.mkdirs();
	}

	public void saveResource(String resource) throws MalformedURLException, IOException {
		URL url = new URL(resource);
		URLConnection con = url.openConnection();
		String contentType = con.getContentType();

		for (Map.Entry<Pattern, String> entry : folders.entrySet()) {
			Pattern key = entry.getKey();
			String value = entry.getValue();

			if (key.matcher(contentType).matches()) {
				File newFolder = new File(value);
				if (!newFolder.exists() && !newFolder.mkdirs()) {
					throw new IOException("Error creating folder " + value + ".");
				}

				String path = url.getPath();
				path = path.substring(path.lastIndexOf('/') + 1);
				String completePath = value + File.separator + path;

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
					return;
				}
			}
		}
	}

}
