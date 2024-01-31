package uf3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ResourceSaver {

	Map<String, String> folders;

	public ResourceSaver() {
		this.folders = new HashMap<>();
	}

	public void addResourceType(String regex, String folder) {
		if (!folders.containsKey(regex)) {
			folders.put(regex, folder);
		}
	}

	public void createFolderTree() {
		for (String str : folders.values()) {
			File f = new File(str);
			if (!f.exists() && f.isDirectory()) {
				f.mkdir();
			}
		}
	}

	public void saveResource(URL resource) {
		try {
			URLConnection con = resource.openConnection();
			String contentType = con.getContentType();
			byte[] buffer = new byte[512];
			int currentChars;

			for (String regex : folders.keySet()) {
				if (Pattern.matches(regex, contentType)) {
					File f = new File(folders.get(regex));
					if (!f.exists()) {
						InputStream inStream = resource.openStream();
						InputStreamReader reader = new InputStreamReader(inStream);
						FileOutputStream outStream = new FileOutputStream(f);
						while ((currentChars = reader.read()) != -1) {
							outStream.write(buffer);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
