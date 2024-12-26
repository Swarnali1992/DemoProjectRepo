package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class ScreenshotUtils {
	
	/**
	 * Here’s a utility code that takes a screenshot during a Selenium test and saves it to a file
	 *
	 * @param driver The WebDriver instance used to interact with the browser.
	 * @param screenshotName The name provided to the screenshot
	 * @return None
	 */
	
	// Method to take a screenshot
    public static void takeScreenshot(WebDriver driver, String screenshotName) {
    	// Take screenshot and store it as a file
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
        // Generate a unique name for the screenshot based on current timestamp
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        
        try {
        	  // Define the destination file path
            File destinationFile = new File( (("C:\\Users\\user\\eclipse-workspace\\DemoProject\\screenshots\\")+screenshotName+"_"+timeStamp) + ".png");

            // Copy the screenshot to the destination file
            FileUtils.copyFile(screenshotFile, destinationFile);
            System.out.println("Screenshot saved to: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error while saving the screenshot: " + e.getMessage());
        }
    }
}
