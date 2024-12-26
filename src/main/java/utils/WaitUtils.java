package utils;
import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
	/**
	 * This method waits for the given element to become visible within the specified timeout duration.
	 * An element is considered visible if it is both present in the DOM and has a non-zero size.
	 *
	 * @param driver The WebDriver instance used to interact with the browser. It is used to initiate the wait.
	 * @param element The WebElement that needs to become visible. The method waits for this element
	 *                to be present in the DOM and have a non-zero size.
	 * @param timeoutInSeconds The maximum duration to wait for the element to become visible. If the element does not become visible
	 *          within this duration, a {@link org.openqa.selenium.TimeoutException} will be thrown.
	 * 
	 * @return The WebElement that became visible. This element is returned when it becomes visible in the browser.
	 * 
	 * @throws org.openqa.selenium.TimeoutException If the element does not become visible within the specified timeout.
	 * 
	 */
	
	/**
	 * Waits for an element to be visible on the page within a specified timeout.
	 * 
	 * @param driver The WebDriver instance used for interacting with the browser.
	 * @param element The WebElement that is being waited for visibility.
	 * @param timeoutInSeconds The maximum time to wait for the element to become visible.
	 * @return The WebElement once it is visible within the specified timeout.
	 * @throws TimeoutException If the element is not visible within the timeout period.
	 */
	public static WebElement waitForVisibility(WebDriver driver, WebElement element, int timeoutInSeconds) throws TimeoutException {
	    // Create a WebDriverWait instance
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	    
	    try {
	        // Wait until the element is visible
	        return wait.until(ExpectedConditions.visibilityOf(element));
	    } catch (TimeoutException e) {
	        // Log and rethrow the exception for better visibility
	        System.out.println("Element not visible after " + timeoutInSeconds + " seconds: " + e.getMessage());
	        throw e;  // Rethrow the exception to notify the calling code
	    }
	}
	  
	/**
	 * Waits for the given element to become clickable within the specified timeout.
	 * 
	 * @param driver The WebDriver instance that is interacting with the browser.
	 * @param element The WebElement to wait for.
	 * @param timeout The maximum time to wait for the element to be clickable.
	 * @return The WebElement once it becomes clickable.
	 * @throws TimeoutException If the element is not clickable within the specified timeout.
	 */
	public static WebElement waitForClickable(WebDriver driver, WebElement element, Duration timeout)throws TimeoutException {
	    // Initialize WebDriverWait with a Duration object
	    WebDriverWait wait = new WebDriverWait(driver, timeout);

	    try {
	        // Wait for the element to become clickable and return it
	        return wait.until(ExpectedConditions.elementToBeClickable(element));
	    } catch (TimeoutException e) {
	        // Handle TimeoutException if the element isn't clickable within the timeout period
	        System.out.println("Element not clickable after " + timeout.getSeconds() + " seconds: " + e.getMessage());
	        throw e; // Re-throw the exception after logging it
	    }
	}

}
