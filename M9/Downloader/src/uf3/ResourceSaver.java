package uf3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	}

	public void saveResource(String resource) throws MalformedURLException, IOException {
		try {
			URL url = new URL(resource);
			URLConnection con = url.openConnection();
			String contentType = con.getContentType();
			char[] buffer = new char[512];
			int currentChars;

			for (Pattern regex : folders.keySet()) {
				if (Pattern.matches(regex.toString(), contentType)) {
					File f = new File(folders.get(regex));
					if (!f.exists()) {
						InputStream inStream = url.openStream();
						InputStreamReader reader = new InputStreamReader(inStream);
						FileOutputStream outStream = new FileOutputStream(f);
						while ((currentChars = reader.read()) != -1) {
							String str = String.copyValueOf(buffer, 0, currentChars);
							outStream.write(str.length());
						}
						outStream.close();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
