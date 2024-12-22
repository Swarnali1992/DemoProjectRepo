package base;
import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
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

	@BeforeTest
	@Parameters({"browser","env"})
	public void beforeTest(String browser, String env){
		setUpDriver(browser);
		captureRequest.captureHttpRequest(driver, browser);
		ConfigReaderUtils.loadProperties(env);
		System.out.println("Running in environment : " +env);
		baseURL = ConfigReaderUtils.getProperty("baseurl");
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
	
	public void setUpDriver(String browser){
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
	

	@AfterTest
	public void afterTest() {
		driver.quit();
		extent.flush();
	}
}