package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {
	  public static void scrollToElement(WebDriver driver, WebElement element) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView(true);", element);
	    }

	    public static void clickUsingJS(WebDriver driver, WebElement element) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].click();", element);
	    }
	    
	    public static void scrollBy(WebDriver driver) {
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	    }
	    
	    public static String getPageTitle(WebDriver driver) {
	    	JavascriptExecutor js = (JavascriptExecutor)driver;
	    	String sText =  js.executeScript("return document.title;").toString();
	    	return sText;

	    }
}
