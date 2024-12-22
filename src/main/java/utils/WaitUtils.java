package utils;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
	  public static WebElement waitForVisibility(WebDriver driver, WebElement element, Duration i) {
	        WebDriverWait wait = new WebDriverWait(driver, i);
	        return wait.until(ExpectedConditions.visibilityOf(element));
	    }

   public static WebElement waitForClickable(WebDriver driver, WebElement element, Duration timeout) {
       WebDriverWait wait = new WebDriverWait(driver, timeout);
       return wait.until(ExpectedConditions.elementToBeClickable(element));
   }

}
