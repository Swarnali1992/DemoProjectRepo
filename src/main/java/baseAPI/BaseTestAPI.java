package baseAPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
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
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Config;
import utils.ConfigReaderUtils;
import utils.ScreenshotUtils;

import java.util.Properties;

public class BaseTestAPI {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_500 = 500;
	public int RESPONSE_STATUS_CODE_400 = 400;
	public int RESPONSE_STATUS_CODE_401 = 401;
	public int RESPONSE_STATUS_CODE_201 = 201;
	protected WebDriver driver;
	public static String apibaseURL;
	public static String excelName; 
	public static String excelFilePath;
   // HttpClient for API testing
    public CloseableHttpClient httpClient;
	
    
    
    
    @BeforeTest
    @Parameters({"env"})
    public void beforeTest(String env) throws Exception {
        try {
             // Load properties from configuration
            ConfigReaderUtils.loadProperties(env);
            System.out.println("Running in environment: " + env);

            // Get the base URL from properties file
            try {
            	apibaseURL = ConfigReaderUtils.getProperty("apibaseurl");
            	//String browser = properties.getProperty("browser");
            	System.out.println("The Base URL of API retrieved from Config File : " +apibaseURL);
            } catch (IOException e) {
                System.err.println("Error reading base URL from properties: " + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("Failed to get base URL", e);
            }

            // Setup extent report
            try {
            	sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "reports" + File.separator + "DemoProjectAPI");
            	 System.out.println("Extent Report Path: " + System.getProperty("user.dir") + File.separator + "reportsapi" + File.separator + "DemoProject");
            	extent = new ExtentReports();
                extent.attachReporter(sparkReporter);

                sparkReporter.config().setTheme(Theme.DARK);
                extent.setSystemInfo("HostName", "Machine Swarnali");
                extent.setSystemInfo("Username", "Swarnali");
                sparkReporter.config().setDocumentTitle("Automation Report of Demo Project API");
                sparkReporter.config().setReportName("Automation Test Results by Swarnali");
            } catch (Exception e) {
                System.err.println("Error setting up ExtentReports: " + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("Failed to setup ExtentReports", e);
            }
        } catch (IOException | RuntimeException e) {
            System.err.println("An error occurred during the before test setup: " + e.getMessage());
            e.printStackTrace();
            throw e;  // Rethrow the exception to indicate test setup failure
        }
    }
    
    
    
    
    
    
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
    @Parameters({"env"})
	@BeforeMethod
    public void setup(Method testMethod, String env) throws FileNotFoundException, IOException {
    	// Set up Selenium WebDriver (choose browser based on configuration)
    //	String filePath = "C:\\Users\\user\\eclipse-workspace\\DemoProject\\src\\main\\resources\\config.api.properties";
   //     try (FileInputStream input = new FileInputStream(filePath)) {
     //   	Properties properties = new Properties();
       // 	properties.load(input);
    	try {
    		 // Initialize logger for the test method in ExtentReports
            logger = extent.createTest(testMethod.getName());
            System.out.println("The Test Method executing: " + testMethod);
        	// apibaseURL = properties.getProperty("apibaseurl");
        	//String browser = properties.getProperty("browser");
        	//System.out.println("The Base URL of API retrieved from Config File : " +apibaseURL);
        	 HttpClients.createDefault();
             HttpClients.custom();
             } 
    	catch (Exception e) {
            System.err.println("An error occurred during the setup: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to setup the test", e);
        }
 }
    
    
    /**
     * This method is executed after each test method. It logs the result of the test (pass, fail, or skip) 
     * in the ExtentReports logger and captures a screenshot if the test fails.
     * 
     * The method performs the following actions based on the test result
     * 
     * 	If the test fails, it logs the failure message and captures a screenshot.
     *  If the test is skipped, it logs the skip message.
     *  If the test passes, it logs the success message.
     * 
     * @param result The result of the test method execution, containing information about the test status.
     * @return None
     */
    @AfterMethod
    public void afterMethod(ITestResult result) {
    	System.out.println("After Method executing : " + result.getStatus());
        try {
            if(result.getStatus() == ITestResult.FAILURE) {
                logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " -- Test Case Failed", ExtentColor.RED));
                logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " -- Test Case Failed", ExtentColor.RED));
                // Capture screenshot when test fails
                ScreenshotUtils.takeScreenshot(driver, result.getName() + "_Failure");
            }
            else if(result.getStatus() == ITestResult.SKIP) {
                logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " -- Test Case Skipped", ExtentColor.ORANGE));	
            }
            else if(result.getStatus() == ITestResult.SUCCESS) {
                logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " -- Test Case Passed", ExtentColor.GREEN));
                System.out.println("Hello");
            }
        } catch (Exception e) {
            // Log any exceptions that occur in afterMethod
            System.err.println("An error occurred in afterMethod: " + e.getMessage());
            e.printStackTrace();
            // You can throw a RuntimeException to fail the test execution in case of an error during the after-method process
            throw new RuntimeException("Error in afterMethod execution", e);
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
    
    
    @AfterTest
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