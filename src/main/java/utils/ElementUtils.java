package utils;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ElementUtils {
	  public static void click(WebElement element) {
	        element.click();
	    }

	    public static void enterText(WebElement element, String text) {
	        element.clear();
	        element.sendKeys(text);
	    }

	    public static String getText(WebElement element) {
	        return element.getText();
	    }
	    
	    public static boolean windowHandling(WebDriver driver, WebElement element, String expectedPageTitle) {
	    	boolean flag = false;
	    	String mainWindowHandle = driver.getWindowHandle();
	        Set<String> allWindowHandles = driver.getWindowHandles();
	        for(String winHandle : allWindowHandles) {
	        	driver.switchTo().window(winHandle);
	        	String actualPageTitle = driver.getTitle();
	        	if(actualPageTitle.equalsIgnoreCase(expectedPageTitle))
	        		flag = true;
	        	{
	        		System.out.println("Inside Page");	
	        	}
	         }
	        return flag;
	    }
	    
	    public static String mainWindow(WebDriver driver) {
	    	String mainWindowHandle = driver.getWindowHandle();
	    	return mainWindowHandle;
	    }
	    
	    public static void selectDropdownValue(WebDriver driver, WebElement elementDropdownList, String condition, String dropdownValue) {
	    	Select select = new Select(elementDropdownList);
	    	 elementDropdownList.click();
	    	switch(condition.toUpperCase())
	    	{
	    	case "VISIBLE TEXT": 
	    		select.selectByVisibleText(dropdownValue);
	    		break;
	    	case "VALUE":
	    		select.selectByValue(dropdownValue);
	    		break;
	    	case "INDEX":
	    		select.selectByIndex(Integer.parseInt(dropdownValue));
	    		break;
	    		default:
	    		System.out.println("Unable to select dropdown value.");
	    	}
	    }
	    
	    

}
