package baseAPI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.http.HttpRequest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Config;
import utils.ConfigReaderUtils;
import java.util.Properties;

public class BaseTestAPI {
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_500 = 500;
	public int RESPONSE_STATUS_CODE_400 = 400;
	public int RESPONSE_STATUS_CODE_401 = 401;
	public int RESPONSE_STATUS_CODE_201 = 201;
	protected WebDriver driver;
	public static String apibaseURL;
   // HttpClient for API testing
    public CloseableHttpClient httpClient;
	
    
    /**
     * Sets up the configuration for the API and WebDriver based on properties from a configuration file.
     * 
     * This method performs the following actions:
     * 
     *   Loads properties from a configuration file located at {@code "C:\\Users\\user\\eclipse-workspace\\DemoProject\\src\\main\\resources\\config.api.properties"}
     *   Retrieves the base URL for the API and the browser configuration from the properties file.
     *   Initializes HTTP clients using Apache HttpClient (default and custom clients).
     * 
     * 
     * @throws FileNotFoundException If the specified configuration file cannot be found.
     * @throws IOException If there is an error reading the configuration file or loading the properties.
     * @return None
     */
    
    
     
    
    @SuppressWarnings("deprecation")
	@BeforeClass
    public void setup() throws FileNotFoundException, IOException {
    	// Set up Selenium WebDriver (choose browser based on configuration)
    	String filePath = "C:\\Users\\user\\eclipse-workspace\\DemoProject\\src\\main\\resources\\config.api.properties";
        try (FileInputStream input = new FileInputStream(filePath)) {
        	Properties properties = new Properties();
        	properties.load(input);
        	apibaseURL = properties.getProperty("apibaseurl");
        	String browser = properties.getProperty("browser");
        	System.out.println("The Base URL of API retrieved from Config File : " +apibaseURL);
        	 HttpClients.createDefault();
             HttpClients.custom();
             } 
 }

    
    /**
     * Cleans up and releases resources after a test is completed.
     * 
     * This method performs the following actions:
     * 
     *   Quits the WebDriver session if it is still running, ensuring that the browser is properly closed.
     *   Closes the Apache HttpClient if it is still open, releasing any associated resources.
     * 
     * 
     * Any exceptions thrown during the cleanup process are caught and logged, ensuring that the test environment is cleaned up without affecting further execution.</p>
     * @return None
     */
    
    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	
    }
    
    
    /**
     * Sends an HTTP GET request to the specified URL and returns the response.
     * 
     * This method creates an HTTP GET request for the given URL, executes the request using an 
     * Apache HttpClient instance, and returns the response in the form of {@link CloseableHttpResponse}.
     * 
     * @param url The URL to which the GET request will be sent. It must be a valid URL.
     * @return CloseableHttpResponse The response of the HTTP GET request.
     * @throws Exception If there is an error while creating the request, executing it, or processing the response.
     * This could include network-related errors, invalid URLs, or issues with the HTTP client.
     */
    
    
    
 // Utility method to send GET request
    @SuppressWarnings("deprecation")
	protected CloseableHttpResponse sendGetRequest(String url) throws Exception {
        HttpGet request = new HttpGet(url);
        return httpClient.execute(request);
}
}