package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {
		  
	  /**
	   * Scrolls the page to bring the specified WebElement into view.
	   * 
	   * @param driver The WebDriver instance.
	   * @param element The WebElement that should be scrolled into view.
	   * @return None
	   */
	  public static void scrollToElement(WebDriver driver, WebElement element) {
	      if (driver == null) {
	          System.err.println("WebDriver is null. Cannot perform scrolling.");
	          return; // Avoid further execution if driver is null
	      }

	      if (element == null) {
	          System.err.println("WebElement is null. Cannot scroll to a null element.");
	          return; // Avoid further execution if element is null
	      }

	      try {
	          // Execute JavaScript to scroll the element into view
	          JavascriptExecutor js = (JavascriptExecutor) driver;
	          js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
	      } catch (Exception e) {
	          // Handle any potential exceptions (e.g., JavaScript execution issues)
	          System.err.println("Error occurred while scrolling to element: " + e.getMessage());
	          e.printStackTrace();
	      }
	  }
	  

/**
 * Clicks on an element using JavaScript.
 * 
 * @param driver The WebDriver instance.
 * @param element The WebElement to be clicked.
 * @return None
 */
public static void clickUsingJS(WebDriver driver, WebElement element) {
    if (driver == null) {
        System.err.println("WebDriver is null. Cannot perform click operation.");
        return; // Avoid further execution if driver is null
    }

    if (element == null) {
        System.err.println("WebElement is null. Cannot click on a null element.");
        return; // Avoid further execution if element is null
    }

    try {
        // Execute JavaScript to click on the element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
        System.out.println("Element clicked using JavaScript.");
    } catch (Exception e) {
        // Handle any potential exceptions (e.g., JavaScript execution issues)
        System.err.println("Error occurred while clicking the element using JavaScript: " + e.getMessage());
        e.printStackTrace();
    }
}
	  
	  
/**
 * Scrolls the window by a specified amount (by default scrolls to the bottom of the page).
 * 
 * @param driver The WebDriver instance.
 * @return None
 */
public static void scrollBy(WebDriver driver) {
    if (driver == null) {
        System.err.println("WebDriver is null. Cannot perform scroll operation.");
        return; // Avoid further execution if driver is null
    }

    try {
        // Execute JavaScript to scroll the window
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)"); // Scrolls down by the height of the page
        System.out.println("Scrolled to the bottom of the page.");
    } catch (Exception e) {
        // Handle any potential exceptions during JavaScript execution
        System.err.println("Error occurred while scrolling: " + e.getMessage());
        e.printStackTrace();
    }
}
	    
	    
/**
 * Retrieves the title of the current web page using JavaScript.
 * 
 * @param driver The WebDriver instance.
 * @return The title of the current web page.
 */
public static String getPageTitle(WebDriver driver) {
    if (driver == null) {
        System.err.println("WebDriver is null. Cannot retrieve the page title.");
        return null; // Avoid further execution if driver is null
    }

    try {
        // Execute JavaScript to get the document title
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String pageTitle = js.executeScript("return document.title;").toString();
        System.out.println("Page Title: " + pageTitle); // Optional: log the page title
        return pageTitle;
    } catch (Exception e) {
        // Handle any potential exceptions during JavaScript execution
        System.err.println("Error occurred while retrieving the page title: " + e.getMessage());
        e.printStackTrace();
        return null;
    }
}
	 
}
