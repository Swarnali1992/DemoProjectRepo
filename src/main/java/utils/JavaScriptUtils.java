package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {
	/**
	 * Scrolls the web page to bring the specified element into view.
	 * 
	 * This method uses JavaScript to scroll the page and ensures that the provided element 
	 * is brought into the visible portion of the web browser window.
	 * 
	 * @param driver The WebDriver instance that controls the browser.
	 * @param element The WebElement to which the page should scroll.
	 * @return None
	 * @throws NullPointerException If the provided {@code driver} or {@code element} is {@code null}.
	 */
	
	  public static void scrollToElement(WebDriver driver, WebElement element) throws NullPointerException  {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView(true);", element);
	    }

	  /**
	   * Clicks on the specified web element using JavaScript.
	   * 
	   * This method uses JavaScript to trigger a click event on the provided web element.
	   * This approach can be useful when the element is not interactable through standard
	   * WebDriver methods, such as when the element is obscured by another element or not 
	   * clickable in the traditional way due to other factors (e.g., CSS styles).
	   * 
	   * @param driver The WebDriver instance that controls the browser.
	   * @param element The WebElement that needs to be clicked.
	   * 
	   * @return None
	   * @throws NullPointerException If the provided {@code driver} or {@code element} is {@code null}.
	   */
	  
	    public static void clickUsingJS(WebDriver driver, WebElement element) throws NullPointerException {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].click();", element);
	    }
	    
	  
	    /**
	     * Scrolls the webpage by a specified distance using JavaScript.
	     * 
	     * This method uses JavaScript to scroll the webpage vertically by a specified number of pixels. It is particularly
	     * useful for scenarios where you need to scroll the page down to load more content or interact with elements that
	     * are not currently visible on the screen.
	     * 
	     * This specific implementation scrolls the page to the bottom (scrolling by the height of the document).
	     * 
	     * @param driver The WebDriver instance that controls the browser.
	     * 
	     * @return None
	     * @throws NullPointerException If the {@code driver} is {@code null}.
	     */
	    public static void scrollBy(WebDriver driver) throws NullPointerException{
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	    } 
	    
	    /**
	     * Retrieves the title of the current webpage using JavaScript execution.
	     * 
	     * This method executes JavaScript to get the title of the current webpage. The title is retrieved from the 
	     * document's title property and returned as a string. This can be used for verification purposes in automated 
	     * testing, logging, or any situation where the page title needs to be captured.
	     * 	   
	     * @param driver The WebDriver instance controlling the browser.
	     * 
	     * @return The title of the current webpage as a string.
	     * 
	     * @throws NullPointerException If the {@code driver} is {@code null}.
	     */
	    
	    public static String getPageTitle(WebDriver driver) throws NullPointerException {
	    	JavascriptExecutor js = (JavascriptExecutor)driver;
	    	String pageText =  js.executeScript("return document.title;").toString();
	    	return pageText;
	    }
	 
}
