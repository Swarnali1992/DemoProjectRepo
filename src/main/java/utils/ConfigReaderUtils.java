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
	     * Retrieves the value for a given key from the properties file.
	     * If the key is not found, it returns null.
	     * 
	     * @param key The key for which the property value is to be retrieved.
	     * @return The value associated with the key in the properties file, or null if the key is not found.
	     * @throws RuntimeException if there is any error retrieving the property.
	     */
	    public static String getProperty(String key) throws IOException, RuntimeException {
	        String value = null;
	        try {
	            value = properties.getProperty(key);

	            if (value == null) {
	                throw new RuntimeException("Property not found for key: " + key);
	            }
	        } catch (NullPointerException e) {
	            System.err.println("Error: NullPointerException while accessing the properties file.");
	            e.printStackTrace();
	            throw new RuntimeException("NullPointerException while accessing the properties file.", e);
	        } catch (RuntimeException e) {
	            System.err.println("Error: RuntimeException occurred while fetching property for key: " + key);
	            e.printStackTrace();
	            throw e;  // Rethrow the RuntimeException
	        } catch (Exception e) {
	            System.err.println("Unexpected error occurred while fetching property: " + e.getMessage());
	            e.printStackTrace();
	            throw new RuntimeException("Unexpected error occurred while fetching property.", e);
	        }
	        return value;
	    }
	}

