package utils;

import java.io.File;
import java.io.IOException;
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
        
        // Define the destination file path
        File destinationFile = new File(screenshotName + ".png");

        try {
            // Copy the screenshot to the destination file
            FileUtils.copyFile(screenshotFile, destinationFile);
            System.out.println("Screenshot saved to: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error while saving the screenshot: " + e.getMessage());
        }
    }
}
