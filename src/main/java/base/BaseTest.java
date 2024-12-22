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
import utils.Config;
import utils.ConfigReaderUtils;

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
 * This method is executed before the start of each test to initialize various components 
 * necessary for running the tests. It sets up the driver, loads configurations, 
 * retrieves environment and test data, and configures the report generation.
 * 
 * The method performs the following actions
 * 
 *   Sets up the browser driver using the {@link #setUpDriver(String)} method.</li>
 *   Captures HTTP requests during the test execution using {@link #captureRequest.captureHttpRequest(WebDriver, String)}.</li>
 *   Loads properties and configurations based on the given environment using {@link ConfigReaderUtils#loadProperties(String)}.</li>
 *   Initializes URL, database, and other configuration values from property files.</li>
 *   Launches the base URL in the specified browser.</li>
 *   Configures the browser window and sets implicit wait times.</li>
 *   Configures the reporting framework with custom settings for HTML report generation.</li>
 * 
 * 
 * @param browser The name of the browser to be used for testing (e.g., "chrome", "firefox").
 * @param env The environment configuration (e.g., "test", "staging", "production") that will be loaded from properties.
 * @throws IOException If there is an issue reading property files or accessing external resources like the Excel file.
 * @throws RuntimeException If an unexpected runtime error occurs during test setup.
 * @return None
 */



	@BeforeTest
	@Parameters({"browser","env"})
	public void beforeTest(String browser, String env) throws IOException, RuntimeException{
		setUpDriver(browser);
		captureRequest.captureHttpRequest(driver, browser);
		ConfigReaderUtils.loadProperties(env);
		System.out.println("Running in environment : " +env);
		try {
			baseURL = ConfigReaderUtils.getProperty("baseurl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("URL launched : " + baseURL);
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		ConfigReaderUtils.loadProperties(env);
		System.out.println("Running in environment : " +env);
		baseURL = ConfigReaderUtils.getProperty("baseurl");
		System.out.println("URL launched : " + baseURL);
		dbName = ConfigReaderUtils.getProperty("database");
		System.out.println("Database Name : " + dbName);
		excelName = ConfigReaderUtils.getProperty("excelfilename");
		System.out.println("Test Data retrieved from Excel : " + excelName);
		excelFilePath = ConfigReaderUtils.getProperty("excelfilepath");
		System.out.println("Test Data Excel is stored in the path : " + excelFilePath);
		rowNum = ConfigReaderUtils.getProperty("excelrownum");
		System.out.println("Test Data should be fetched from row number : " + rowNum);
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator+"reports"+File.separator+"DemoProject");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		sparkReporter.config().setTheme(Theme.DARK);
		extent.setSystemInfo("HostName", "Machine Swarnali");
		extent.setSystemInfo("Username", "Swarnali");
		sparkReporter.config().setDocumentTitle("Automation Report of Demo Project");
		sparkReporter.config().setReportName("Automation Test Results by Swarnali Lahiri ");
	}
	
	
	
	/**
	 * This method is executed before each test method in a test suite. It performs setup operations 
	 * such as initializing the ExtentTest instance, capturing HTTP requests, and preparing 
	 * any necessary configurations for the test environment.
	 *
	 * e method logs the name of the test method being executed and creates a new test instance 
	 * in ExtentReports. It also includes placeholders for additional setup, like loading properties, 
	 * launching the browser, setting the URL, and retrieving test data, although some of the setup 
	 * steps are currently commented out.
	 *
	 * @param testMethod The test method being executed. This is used to log the test name in the report.
	 * @param browser The name of the browser to be used for the test (e.g., "chrome", "firefox").
	 * @param env The environment in which the test is to be executed (e.g., "test", "staging", "production").
	 * @return None
	 */
	
	
	@BeforeMethod
	@Parameters({"browser","env"})
	public void beforeMethod(Method testMethod, String browser, String env) {
		logger=extent.createTest(testMethod.getName());
		System.out.println("The Test Method executing :   "  +testMethod);
		//setUpDriver(browser);
		captureRequest.captureHttpRequest(driver, browser);
//		ConfigReaderUtils.loadProperties(env);
//		System.out.println("Running in environment : " +env);
//		baseURL = ConfigReaderUtils.getProperty("baseurl");
//		System.out.println("URL launched : " + baseURL);
//		dbName = ConfigReaderUtils.getProperty("database");
//		System.out.println("Database Name : " + dbName);
//		excelName = ConfigReaderUtils.getProperty("excelfilename");
//		System.out.println("Test Data retrieved from Excel : " + excelName);
//		excelFilePath = ConfigReaderUtils.getProperty("excelfilepath");
//		System.out.println("Test Data Excel is stored in the path : " + excelFilePath);
//		rowNum = ConfigReaderUtils.getProperty("excelrownum");
//		System.out.println("Test Data should be fetched from row number : " + rowNum);
//		driver.get(baseURL);
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
}
	
	
	
	/**
	 * Initializes and sets up the appropriate WebDriver based on the provided browser name.
	 * This method configures the WebDriver for Chrome, Firefox, or Edge using the WebDriverManager 
	 * and creates a new driver instance.
	 * 
	 * If the provided browser name does not match any supported browser (CHROME, FIREFOX, EDGE), 
	 * a RuntimeException will be thrown.
	 *
	 * @param browser The name of the browser to be used for the test. Supported values are:
	 *                "CHROME", "FIREFOX", and "EDGE".
	 * @throws WebDriverException If an error occurs while setting up the WebDriver (e.g., if the browser driver 
	 *                            is not found or there is an issue initializing the driver).
	 * @throws RuntimeException If the provided browser name is unsupported.
	 * @return None
	 */
	
	public void setUpDriver(String browser) throws WebDriverException{
		if(browser.equalsIgnoreCase("CHROME")){
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();	
		}
		else if(browser.equalsIgnoreCase("FIREFOX")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();	
		}
		else if(browser.equalsIgnoreCase("EDGE")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();	
		}
		else{
            throw new RuntimeException("Unsupported browser: " + browser);
        }
		
	}
	
	
	/**
	 * Logs the outcome of the test method execution.
	 * 
	 * This method is executed after each test method to log the result of the test, including
	 * 
	 *   If the test failed, the failure reason (exception message) and the test name are logged with a RED color.
	 *   If the test was skipped, the test name is logged with an ORANGE color.
	 *   If the test passed, the test name is logged with a GREEN color.
	 * 
	 *
	 * @param result The result of the test method execution, containing information about the test status (PASS, FAIL, SKIP).
	 *
	 * @return None
	 */
	
	
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " --  Test Case Failed " , ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " --  Test Case Failed " , ExtentColor.RED));
		}
		else if(result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " --  Test Case Skipped " , ExtentColor.ORANGE));	
		}
		else if(result.getStatus() == ITestResult.SUCCESS){
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " --  Test Case Passed " , ExtentColor.GREEN));
		}
	
	}
	
	/**
	 * Cleans up resources and finalizes the test execution.
	 * 
	 * This method performs the following actions after each test
	 * 
	 *   Quits the WebDriver session by calling {@link WebDriver#quit()} to close all associated browser windows and clean up the driver instance.
	 *   Flushes the ExtentReports to ensure that all test results are written to the report file using {@link ExtentReports#flush()}
	 * @return None
	 */
	
	
	@AfterTest
	public void afterTest() {
		driver.quit();
		extent.flush();
	}
}