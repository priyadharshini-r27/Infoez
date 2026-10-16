package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static Properties prop;

	public static Properties init_prop() {
		prop = new Properties();

		File file = new File("./src/test/resources/config/config.properties");

		if (!file.exists()) {
			file = new File("./Config/Config.properties");
		}

		try (FileInputStream fis = new FileInputStream(file)) {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not load config.properties file at path: " + file.getAbsolutePath());
		}
		return prop;
	}

	public static String getProperty(String key) {
		if (prop == null) {
			init_prop();
		}
		return prop.getProperty(key);
	}
}