package utils;
import java.time.Duration;
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
	 * @param i The maximum duration to wait for the element to become visible. If the element does not become visible
	 *          within this duration, a {@link org.openqa.selenium.TimeoutException} will be thrown.
	 * 
	 * @return The WebElement that became visible. This element is returned when it becomes visible in the browser.
	 * 
	 * @throws org.openqa.selenium.TimeoutException If the element does not become visible within the specified timeout.
	 * 
	 */
	
	  public static WebElement waitForVisibility(WebDriver driver, WebElement element, Duration i) {
	        WebDriverWait wait = new WebDriverWait(driver, i);
	        return wait.until(ExpectedConditions.visibilityOf(element));
	    }

	  /**
	   * This method waits for the given element to become clickable within the specified timeout.
	   * An element is considered clickable if it is visible and enabled.
	   *
	   * @param driver The WebDriver instance used to interact with the browser. It is used to initiate the wait.
	   * @param element The WebElement that needs to become clickable. The method waits for this element
	   *                to be both visible and enabled.
	   * @param timeout The maximum duration to wait for the element to become clickable. If the element does not become
	   *                clickable within this duration, a {@link org.openqa.selenium.TimeoutException} will be thrown.
	   * 
	   * @return The WebElement that became clickable. This is returned when the element is clickable.
	   * 
	   * @throws org.openqa.selenium.TimeoutException If the element does not become clickable within the specified timeout.
	   */
	  
   public static WebElement waitForClickable(WebDriver driver, WebElement element, Duration timeout)  {
       WebDriverWait wait = new WebDriverWait(driver, timeout);
       return wait.until(ExpectedConditions.elementToBeClickable(element));
   }

}
