package dad.javafx.terminaltrainer.utils;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class ResourceUtils {
	
	public static String getResourceAsString(String resourcePath) {
		try {
			return IOUtils.toString(ResourceUtils.class.getResourceAsStream(resourcePath), StandardCharsets.UTF_8);
		} catch (IOException e) {
			return null;
		}
	}
	
	public static void copyResourceToFile(String resource, File dest) throws Exception  {
		copyInputStreamToFile(FileUtils.class.getResourceAsStream(resource), dest);
	}
	
}
