package utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import base.BaseTest;

public class SuiteListener implements ITestListener, IAnnotationTransformer{
	/**
	 * This method is invoked when a test fails during execution.
	 * It captures a screenshot of the current browser window and saves it to a file.
	 *
	 * @param result The ITestListener result object that contains information about the failed test,
	 *               including the test method that failed. It is used here to get the method name to create
	 *               a unique filename for the screenshot.
	 *               
	 * @return None
	 * @throws IOException If there is an issue with file I/O operations (e.g., saving the screenshot file).
	 */
	
	public void onTestFailure(ITestListener result) throws IOException {		
		String filename=System.getProperty("user.dir")+File.separator+"screenshots"+File.separator+((ITestResult) result).getMethod().getMethodName();
		File srcFile = ((TakesScreenshot)BaseTest.driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File (filename + ".png"));	
	}
	/**
	 * This method is used to modify the behavior of a test method in TestNG.
	 * It sets a custom retry analyzer for the provided test method.
	 *
	 * @param annotation The ITestAnnotation object that represents the annotations of the test method.
	 *                   This parameter allows us to modify the configuration of the test method, including setting
	 *                   the retry behavior.
	 * @param testClass The object representing the test class that contains the test method.
	 *                  This parameter can be used to retrieve metadata or annotations related to the test class.
	 * @param testConstructor The object representing the constructor of the test class.
	 *                        This is usually provided to support parameterized tests or other specialized test setups.
	 * @param testMethod The object representing the specific test method being configured.
	 *                   This allows the modification of the test method itself, including retry logic.
	 * @return None
	 */
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}

}
