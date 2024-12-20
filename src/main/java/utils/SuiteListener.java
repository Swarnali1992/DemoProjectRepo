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
	public void onTestFailure(ITestListener result) throws IOException {		
		String filename=System.getProperty("user.dir")+File.separator+"screenshots"+File.separator+((ITestResult) result).getMethod().getMethodName();
		File srcFile = ((TakesScreenshot)BaseTest.driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File (filename + ".png"));	
	}
	
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}

}
