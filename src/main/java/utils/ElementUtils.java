package utils;

import java.util.List;
import java.util.Set;
import utils.IdentifyWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import pageEvent.SwagLabsPageEvent;
import pageObject.SwagLabsPageObject;

public class ElementUtils {
	IdentifyWebElement ele = new IdentifyWebElement();
	
	SwagLabsPageEvent swagLabsPage = new SwagLabsPageEvent();
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
	    
	    public void selectSubMenu(String selectedSubMenu) {
	    	List<WebElement> subMenu = ele.getWebElements("XPATH", SwagLabsPageObject.subMenuOptions);
	    	for(WebElement sbmenu: subMenu) {
	  		  String subMenuName = sbmenu.getText();
	  		  if(subMenuName.equalsIgnoreCase(selectedSubMenu))
	  		  {
	  			sbmenu.click();
	  			System.out.println("On About 1 page");
	  		  }
	  	  }
	    }
	    
	 
	   
}
