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
    
 // Utility method to send GET request
    @SuppressWarnings("deprecation")
	protected CloseableHttpResponse sendGetRequest(String url) throws Exception {
        HttpGet request = new HttpGet(url);
        return httpClient.execute(request);
}
}