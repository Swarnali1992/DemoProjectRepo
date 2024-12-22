package utils;

import java.util.List;
import java.util.Set;
import utils.IdentifyWebElement;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import pageEvent.SwagLabsPageEvent;
import pageObject.SwagLabsPageObject;

public class ElementUtils {
	IdentifyWebElement ele = new IdentifyWebElement();
	SwagLabsPageEvent swagLabsPage = new SwagLabsPageEvent();
	
	/**
	 * Clicks on the provided WebElement
	 * 
	 * This method is a wrapper around Selenium's {@code WebElement.click()} method. It performs a click action on the 
	 * given element in the browser.
	 * 
	 * @param element The WebElement to be clicked. The method attempts to perform a click action on this element.
	 * @throws org.openqa.selenium.ElementNotInteractableException If the element is not interactable, for example, if it is not visible or enabled.
	 * @throws org.openqa.selenium.ElementClickInterceptedException If the click action is intercepted by another element, causing the click to fail.
	 * @return None
	 */
	
	  public static void click(WebElement element) throws ElementClickInterceptedException,ElementNotInteractableException {
	        element.click();
	    }
	  
	  /**
	   * Enters text into the provided WebElement (typically an input field or text box).
	   * 
	   * This method clears any existing text in the given element and then enters the specified text.
	   * It is useful for filling out forms or updating values in text fields.
	   *
	   * 
	   * @param element The WebElement where the text needs to be entered. 
	   *                This should be a valid input element such as a text field, password field, etc.
	   * @param text The text to be entered into the input field.
	   * 
	   * @return None
	   * @throws NullPointerException If the provided WebElement is null.
	   */

	    public static void enterText(WebElement element, String text) throws NullPointerException {
	        element.clear();
	        element.sendKeys(text);
	    }

	    /**
	     * Retrieves the visible text from the given WebElement.
	     * 
	     * This method is used to get the visible text from an element such as a label, button, or a paragraph. 
	     * It returns the text that is rendered inside the element, excluding HTML tags or attributes.
	     * 
	     * 
	     * @param element The WebElement from which the text is to be retrieved.
	     *                This should be an element that contains visible text, such as a label, button, or paragraph.
	     * @return A String representing the visible text inside the specified element.
	     *         If the element contains no text, it will return an empty string.
	     * 
	     * @throws NullPointerException If the provided WebElement is null.
	     */
	    
	    public static String getText(WebElement element) throws NullPointerException {
	        return element.getText();
	    }
	    
	    
	    /**
	     * Handles multiple browser windows and switches to the window that matches the expected page title.
	     * 
	     * This method helps in managing multiple browser windows or tabs by iterating through all open windows,
	     * switching to each window, and comparing the page title with the expected page title.
	     * If a window with the expected title is found, it switches to that window and returns {@code true}.
	     * 
	     *
	     * @param driver The WebDriver instance used to interact with the browser.
	     * @param element The WebElement which is expected to trigger the opening of a new window or tab. This element is not used directly in the method but can be part of the operation.
	     * @param expectedPageTitle The expected title of the page that the method will check in the new window.
	     * @return {@code true} if a window with the expected page title is found, {@code false} otherwise.
	     *
	     * @throws NullPointerException If the WebDriver or element is null.
	     */
	    
	    
	    public static boolean windowHandling(WebDriver driver, WebElement element, String expectedPageTitle) throws NullPointerException {
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
	    
	    
	    /**
	     * Retrieves the handle of the main (parent) window in the current WebDriver session.
	     * 
	     * In Selenium WebDriver, each window or tab has a unique identifier known as a window handle. 
	     * This method returns the handle of the main window, which is typically the first window 
	     * that was opened in the WebDriver session.
	     *  
	     * @param driver The WebDriver instance controlling the browser. 
	     *               It is used to interact with the browser window to fetch its handle.
	     * @return The handle of the main (parent) window as a {@link String}.
	     *         This is the unique identifier for the window or tab that is considered the "main" window.
	     * 
	     * @throws IllegalStateException If the WebDriver is not properly initialized or if the driver is unable to access the current window handle.
	     */
	    
	    
	    
	    public static String mainWindow(WebDriver driver) throws IllegalStateException{
	    	String mainWindowHandle = driver.getWindowHandle();
	    	return mainWindowHandle;
	    }
	    
	    
	    /**
	     * Selects a value from a dropdown list based on the specified condition.
	     * 
	     * This method allows selecting an option from a dropdown using one of three selection methods:
	     * 1. By visible text
	     * 2. By value attribute
	     * 3. By index
	     * 
	     * @param driver The WebDriver instance used to interact with the browser.
	     * @param elementDropdownList The WebElement representing the dropdown list from which a value needs to be selected.
	     * @param condition The condition that specifies how to select the dropdown value. It can be:
	     *                  <ul>
	     *                      <li>"VISIBLE TEXT" - Selects the option by its visible text.</li>
	     *                      <li>"VALUE" - Selects the option by its "value" attribute.</li>
	     *                      <li>"INDEX" - Selects the option by its index (starting from 0).</li>
	     *                  </ul>
	     * @param dropdownValue The value used for selection. It could be:
	     *                      <ul>
	     *                          <li>The visible text of the dropdown option (if using "VISIBLE TEXT").</li>
	     *                          <li>The "value" attribute of the dropdown option (if using "VALUE").</li>
	     *                          <li>The index of the option in the dropdown (if using "INDEX").</li>
	     *                      </ul>
	     * @throws IllegalArgumentException If an invalid condition is provided (other than "VISIBLE TEXT", "VALUE", or "INDEX").
	     * @return None
	     */
	    
	    
	    
	    public static void selectDropdownValue(WebDriver driver, WebElement elementDropdownList, String condition, String dropdownValue) throws IllegalArgumentException{
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
	    
	    /**
	     * Selects a submenu option from a list of submenus based on the provided name.
	     * 
	     * This method iterates through a list of submenu elements (retrieved by XPath) and clicks on the submenu
	     * that matches the given name (case-insensitive). After clicking the correct submenu, a message is printed.
	     * 
	     * @param selectedSubMenu The name of the submenu to be selected. This name will be compared case-insensitively to the text of each submenu.
	     *                        If a match is found, that submenu is clicked.
	     * 
	     * @return None
	     * @throws NoSuchElementException If no submenu matches the provided name.
	     * @throws ElementClickInterceptedException If the submenu element is blocked by another element and cannot be clicked.
	     */
	    
	    
	    
	    public void selectSubMenu(String selectedSubMenu) throws NoSuchElementException, ElementClickInterceptedException{
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
