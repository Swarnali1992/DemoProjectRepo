package base;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.CaptureRequest;
import utils.ConfigReaderUtils;
import utils.ScreenshotUtils;

public class BaseTest {
public static WebDriver driver;
public ExtentSparkReporter sparkReporter;
public ExtentReports extent;
public ExtentTest logger;
public String baseURL;
public static String dbName;
public static String excelName; 
public static String excelFilePath;
public static String rowNum;
CaptureRequest captureRequest = new CaptureRequest();
/**
 * This method is executed before each test in the test suite. It sets up the WebDriver, 
 * loads configuration properties, initializes Extent Reports, and prepares the test environment.
 * 
 * The method accepts two parameters:
 * 
 *     browser The browser type (e.g., "chrome", "firefox") to initialize the WebDriver.
 *    env: The environment (e.g., "prod", "dev") for which the properties are loaded.
 * 
 * 
 * It performs the following actions:
 * 
 *     Sets up the WebDriver for the specified browser.
 *     Captures HTTP requests made by the driver for the given browser.
 *     Loads the configuration properties for the specified environment (e.g., base URL, database name).
 *     Launches the browser and navigates to the specified base URL.
 *     Maximizes the browser window and sets an implicit wait.
 *     Sets up Extent Reports for test reporting, including setting the theme and document title.
 *
 * 
 * Any errors during the setup process (e.g., missing properties or failed WebDriver initialization) 
 * are caught, logged, and rethrown as runtime exceptions to ensure proper test failure handling.
 *
 * @param browser The browser type to initialize (e.g., "chrome", "firefox").
 * @param env The environment configuration to load properties for (e.g., "dev", "prod").
 * @throws IOException If an error occurs while reading properties from the configuration file.
 * @throws RuntimeException If a runtime error occurs during WebDriver setup or ExtentReports initialization.
 * @return None
 */

@BeforeTest
@Parameters({"browser", "env"})
public void beforeTest(String browser, String env) throws Exception {
    try {
        // Setup the WebDriver
        setUpDriver(browser);
        captureRequest.captureHttpRequest(driver, browser);

        // Load properties
        ConfigReaderUtils.loadProperties(env);
        System.out.println("Running in environment: " + env);

        // Get the base URL from properties file
        try {
            baseURL = ConfigReaderUtils.getProperty("baseurl");
            System.out.println("URL launched: " + baseURL);
        } catch (IOException e) {
            System.err.println("Error reading base URL from properties: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to get base URL", e);
        }

        // Navigate to the URL and configure browser settings
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);

        // Load other properties
        dbName = ConfigReaderUtils.getProperty("database");
        System.out.println("Database Name: " + dbName);

        excelName = ConfigReaderUtils.getProperty("excelfilename");
        System.out.println("Test Data retrieved from Excel: " + excelName);

        excelFilePath = ConfigReaderUtils.getProperty("excelfilepath");
        System.out.println("Test Data Excel is stored in the path: " + excelFilePath);

        rowNum = ConfigReaderUtils.getProperty("excelrownum");
        System.out.println("Test Data should be fetched from row number: " + rowNum);

        // Setup extent report
        try {
            sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "reportsapi" + File.separator + "DemoProject");
           
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            sparkReporter.config().setTheme(Theme.DARK);
            extent.setSystemInfo("HostName", "Machine Swarnali");
            extent.setSystemInfo("Username", "Swarnali");
            sparkReporter.config().setDocumentTitle("Automation Report of Demo Project");
            sparkReporter.config().setReportName("Automation Test Results by Swarnali Lahiri");
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
 * This method is executed before each test method. It initializes the logger for ExtentReports 
 * and captures HTTP requests. Additionally, it prints the test method name to the console.
 * 
 *	The method accepts the following parameters:
 * 
 *     testMethod: The test method being executed.
 *     browser: The browser type (e.g., "chrome", "firefox") to initialize the WebDriver.
 *     env: The environment (e.g., "prod", "dev") for which the properties are loaded.
 * 
 * 
 * Any errors during the execution of this method (e.g., logger initialization or HTTP request capturing) 
 * are caught and logged appropriately.
 * 
 * @param testMethod The test method that is being executed.
 * @param browser The browser type to initialize (e.g., "chrome", "firefox").
 * @param env The environment configuration to load properties for (e.g., "dev", "prod").
 * @return None
 */
@BeforeMethod
@Parameters({"browser", "env"})
public void beforeMethod(Method testMethod, String browser, String env) {
    try {
        // Initialize logger for the test method in ExtentReports
        logger = extent.createTest(testMethod.getName());
        System.out.println("The Test Method executing: " + testMethod);

        // Capture HTTP requests made by the driver for the given browser
        captureRequest.captureHttpRequest(driver, browser);
    } catch (Exception e) {
        // Handle any unexpected errors that may occur during the beforeMethod execution
        System.err.println("An error occurred during the beforeMethod setup: " + e.getMessage());
        e.printStackTrace();
        throw new RuntimeException("Failed to execute beforeMethod", e);
    }
}
	
	

/**
 * This method sets up the WebDriver based on the provided browser type. It supports Chrome, Firefox, 
 * and Edge browsers. If an unsupported browser is provided, a RuntimeException is thrown.
 * 
 * @param browser The browser type (e.g., "chrome", "firefox", "edge").
 * @throws WebDriverException If the browser is unsupported or an error occurs while setting up the WebDriver.
 * @return None
 */
public void setUpDriver(String browser) throws WebDriverException {
    try {
        if (browser.equalsIgnoreCase("CHROME")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("FIREFOX")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("EDGE")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            // Handle unsupported browsers
            throw new RuntimeException("Unsupported browser: " + browser);
        }
    } catch (WebDriverException e) {
        // Log and throw WebDriverException if setup fails
        System.err.println("Error setting up WebDriver for browser: " + browser);
        e.printStackTrace();
        throw new WebDriverException("Failed to set up WebDriver for " + browser, e);
    } catch (Exception e) {
        // General exception handling for unexpected errors
        System.err.println("An unexpected error occurred while setting up the WebDriver.");
        e.printStackTrace();
        throw new RuntimeException("Failed to set up WebDriver due to unexpected error", e);
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
    try {
    	
    	System.out.println("After Method executing" + result.getStatus());
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
 * This method is executed after all tests in the test class have finished. It performs the following tasks:
 * 
 * 	Closes the browser session by quitting the WebDriver instance.
 *  Flushes the ExtentReports to generate the final test report.
 * 
 * 
 * @throws RuntimeException If an error occurs during the WebDriver shutdown or report flushing process.
 * @return None
 */
@AfterTest
public void afterTest() {
    try {
        // Close the browser and end the WebDriver session
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully.");
        }

        // Flush the ExtentReports to generate the final report
        if (extent != null) {
            extent.flush();
            System.out.println("Extent Reports flushed successfully.");
        }
    } catch (Exception e) {
        // Log any exceptions that occur during the cleanup process
        System.err.println("Error during afterTest execution: " + e.getMessage());
        e.printStackTrace();
        // Rethrow the exception to indicate failure in the cleanup process
        throw new RuntimeException("Error during afterTest execution", e);
    }
}
}