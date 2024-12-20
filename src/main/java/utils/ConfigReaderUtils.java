package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReaderUtils {
	 private static Properties properties = new Properties();

	    public static void loadProperties(String env) {
	        String filePath = "C:\\Users\\user\\eclipse-workspace\\DemoProject\\src\\main\\resources\\config." + env + ".properties";
	        try (FileInputStream input = new FileInputStream(filePath)) {
	            properties.load(input);
	        } catch (IOException e) {
	            throw new RuntimeException("Failed to load configuration file: " + filePath, e);
	        }
	    }

	    public static String getProperty(String key) {
	        return properties.getProperty(key);
	    }

}
