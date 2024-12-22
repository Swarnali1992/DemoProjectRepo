package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReaderUtils {
	 private static Properties properties = new Properties();
	 /**
	  * Loads the properties from a configuration file based on the specified environment.
	  * 
	  * This method loads a properties file located at the path:
	  * <code>src/main/resources/config.{env}.properties</code>, where the <code>{env}</code> is replaced by the value
	  * of the {@code env} parameter. The method uses {@link FileInputStream} to read the file and the {@link Properties} class 
	  * to load the configuration.
	  * 
	  * @param env The environment name (e.g., "dev", "prod", "test") that determines which properties file to load.
	  *            The file name is expected to follow the pattern: <code>config.{env}.properties</code>.
	  * @throws IOException If there is an issue reading the file (e.g., file not found or read error).
	  * @throws RuntimeException If the configuration file cannot be loaded, wrapping the {@link IOException}.
	  * @return None
	  */
	    public static void loadProperties(String env) throws IOException, RuntimeException{
	        String filePath = "C:\\Users\\user\\eclipse-workspace\\DemoProject\\src\\main\\resources\\config." + env + ".properties";
	        try (FileInputStream input = new FileInputStream(filePath)) {
	            properties.load(input);
	        } catch (IOException e) {
	            throw new RuntimeException("Failed to load configuration file: " + filePath, e);
	        }
	    }

	    

/**
 * Retrieves the value of a property from the loaded configuration.
 * 
 * This method fetches the value associated with the specified key from the {@link Properties} object. It assumes that the 
 * properties have been previously loaded (e.g., by calling {@link loadProperties(String)}). If the key does not exist, it returns 
 * {@code null} or can be customized to return a default value.
 * 
 * @param key The key of the property to be fetched from the configuration file.
 * @return The value associated with the specified key, or {@code null} if the key does not exist.
 * @throws IOException If the properties have not been loaded properly or the properties file was not found.
 * @throws RuntimeException If the properties have not been loaded or are unavailable when calling this method.
 */
	    
	    public static String getProperty(String key) throws IOException,RuntimeException{
	        return properties.getProperty(key);
	    }

}
