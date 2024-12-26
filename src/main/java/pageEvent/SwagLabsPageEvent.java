package pageEvent;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import base.BaseTest;
import pageObject.LoginPageObject;
import pageObject.SwagLabsPageObject;
import utils.ExcelUtils;
import utils.IdentifyWebElement;
import utils.JavaScriptUtils;
import utils.WaitUtils;

public class SwagLabsPageEvent {
	IdentifyWebElement ele = new IdentifyWebElement();
	public static String sheetName = "SearchProduct";
	String searchProductData;
	
	/**
	 * This method verifies that the login was successful by checking if the "Swag Labs" header is displayed on the page.
	 * It uses an assertion to confirm that the header is present in the DOM, indicating a successful login.
	 *
	 * @throws AssertionError If the assertion fails, meaning the "Swag Labs" header is not found on the page.
	 */
	public void verifyLoginSuccessful() throws Exception{
	    try {
	        // Verify if the "Swag Labs" header is present on the page
	        boolean isSwagLabsHeaderDisplayed = ele.getWebElements("XPATH", SwagLabsPageObject.swagLabsHdr).size() > 0;

	        // Assert that the header is displayed (indicating successful login)
	        Assert.assertTrue(isSwagLabsHeaderDisplayed, "Login not successful. Swag Labs Page not displayed.");
	        System.out.println("Login successful. Swag Labs Page is displayed.");
	    } catch (AssertionError e) {
	        // Log the error if the assertion fails and rethrow the exception
	        System.out.println("Error: Login not successful. Swag Labs Page not displayed.");
	        e.printStackTrace();
	        throw e;  // Rethrow the AssertionError to fail the test
	    } catch (Exception e) {
	        // Catch any unforeseen exceptions
	        System.out.println("An unexpected error occurred during the login verification process: " + e.getMessage());
	        e.printStackTrace();
	        throw new RuntimeException("Unexpected error during login verification.", e);
	    }
	}
	
	public void clickOpenMenuIcon() throws InterruptedException {
		ele.getWebElement("ID", SwagLabsPageObject.openMenuIcon).click();
		Thread.sleep(6000);
	}
	/**
	 * Clicks the icon to close the menu. This method targets the menu close button 
	 * (identified by its ID) and simulates a click action to close the menu.
	 */
	public void clickCloseMenuIcon() {
	    // Click on the "Close Menu" icon to close the menu
	    ele.getWebElement("ID", SwagLabsPageObject.closeMenuIcon).click();
	}
	public void clickLogout() {
		ele.getWebElement("ID", SwagLabsPageObject.logOutSubMenu).click();
	}
	
	public List<WebElement> getSubMenus() {
	List<WebElement> subMenu = ele.getWebElements("XPATH", SwagLabsPageObject.subMenuOptions);
	return subMenu;
	}
	/**
	 * Clicks the "Add to Cart" button for the specific product (identified by its ID) 
	 * on the page. This method specifically targets the "Swag Lab Backpack" product 
	 * and simulates a click action to add it to the cart. After clicking, it waits 
	 * for 5 seconds to ensure the action is completed.
	 * 
	 * @throws InterruptedException if the thread is interrupted while waiting for the 5-second delay.
	 */
	public void clickAddCartProduct() throws InterruptedException {
	    // Click on the "Swag Lab Backpack" product to add it to the cart
	    ele.getWebElement("ID", SwagLabsPageObject.swagLabBagPack).click();
	    
//	    // Wait for 5 seconds to ensure the action completes
//	    Thread.sleep(5000);
	}

	
	/**
	 * Clicks on the "Go to Cart" icon to navigate to the cart page. This method 
	 * attempts to find the cart icon using its class name and simulates a click 
	 * action to navigate to the shopping cart. 
	 *
	 * The method handles the following exceptions:
	 * 
	 *   NoSuchElementException} - If the cart icon is not found using the given class name.
	 *   Exception} - Any other unexpected exceptions that may occur.
	 * 
	 * @return None
	 * @throws InterruptedException if the thread is interrupted while waiting for the page transition (if sleep is re-enabled).
	 */
	public void clickGoToCartPageIcon() throws InterruptedException {
	    try {
	    	//explicit wait for the cart icon to be visible
	    	WaitUtils.waitForVisibility(BaseTest.driver, ele.getWebElement("CLASSNAME", SwagLabsPageObject.addToCartPageIcon), 1000);
	        // Click on the "Go to Cart" icon to navigate to the cart page
	        ele.getWebElement("CLASSNAME", SwagLabsPageObject.addToCartPageIcon).click();
	        
	        // Optionally wait for 5 seconds to ensure the page transition completes (if sleep is re-enabled)
	        // Thread.sleep(5000);
	    } catch (NoSuchElementException e) {
	        // Log the exception if the cart icon element is not found
	        System.out.println("Error: Unable to find 'Go to Cart' icon - " + e.getMessage());
	    } catch (Exception e) {
	        // Catch any other unexpected exceptions
	        System.out.println("An unexpected error occurred: " + e.getMessage());
	    }
	}
	  /**
	   * Fetches product data from an Excel file based on a specified row number.
	   * 
	   * This method retrieves the product information from an Excel sheet using 
	   * the row number provided by the test class configuration. The row number 
	   * is parsed from a string to an integer, and the data from the first column 
	   * of the corresponding row is returned.
	   * 
	   * If the row number is invalid (cannot be parsed), a {@link NumberFormatException} 
	   * will be caught and an error message will be logged.
	   * If there is an issue reading the Excel file, an {@link IOException} will be caught 
	   * and an appropriate error message will be logged.
	   * 
	   * @return The product data from the Excel sheet. If an error occurs, 
	   *         the method returns an empty string.
	   * @throws NumberFormatException If the row number cannot be parsed as an integer.
	   * @throws IOException If there is an issue reading the Excel file.
	 * @ throws InterruptedException 
	   */
	  public String fetchProductData() throws IOException, NumberFormatException, InterruptedException{
		    
		    ExcelUtils excelutil = new ExcelUtils(BaseTest.excelFilePath);

		    try {
		        // Fetch product data from Excel sheet
		        searchProductData = excelutil.getCellData(sheetName, Integer.parseInt(BaseTest.rowNum), 0);
		        System.out.println("The product retrieved from Excel: " + searchProductData);
		        Thread.sleep(5000);
		    } catch (NumberFormatException e) {
		        // Log the exception for debugging purposes
		        System.err.println("Error: Invalid row number format. " + e.getMessage());
		    } catch (IOException e) {
		        // Log the exception for file-related issues
		        System.err.println("Error reading the Excel file: " + e.getMessage());
		    }

		    return searchProductData;
		}
	  /**
	   * Searches for a product by its title in the product list and adds it to the cart if it matches
	   * the search criteria. The product title is compared with the `searchProductData` and if a match 
	   * is found, the product is added to the cart by clicking the corresponding button.
	   * 
	   * @return The title of the product that was found and added to the cart. If no match is found, 
	   *         the method returns null.
	   * 
	   * @throws InterruptedException if the thread is interrupted while waiting.
	   */
	  public String searchProductTitle() throws NoSuchElementException,InterruptedException {
		    String productTitle = null;

		    try {
		        // Get the list of products once
		        List<WebElement> prodList = ele.getWebElements("XPATH", SwagLabsPageObject.searchProductTitle);
		        
		        // Iterate over the list of products
		        for (WebElement product : prodList) {
		            WebElement prodTitleElement = product.findElement(By.xpath("div[2]/div[1]/a/div"));
		            productTitle = prodTitleElement.getText();
		           System.out.println("The product need to search in UI:" +searchProductData);
		            System.out.println("The product title in UI : " + productTitle);
		            
		            // Check if the product title matches the search term
		            if (productTitle.equalsIgnoreCase(searchProductData)) {
		                System.out.println("The product searched in UI : " + productTitle);
		                
		                // Add the product to the cart
		                product.findElement(By.xpath("div[2]/div[2]/button")).click();
		                System.out.println("Added To Cart");
		                break; // Exit the loop once the product is added to the cart
		            }
		        }
		        
		        // If no product matched the search term, log and return null or a suitable value
		        if (productTitle == null) {
		            System.out.println("Product not found: " + searchProductData);
		        }

		    } catch (NoSuchElementException e) {
		        // Log the exception and return a meaningful message
		        System.out.println("Error: Unable to locate the element - " + e.getMessage());
		    } catch (StaleElementReferenceException e) {
		        // Log the exception and return a meaningful message
		        System.out.println("Error: Stale element reference - " + e.getMessage());
		    } catch (Exception e) {
		        // Catch any other unexpected exceptions
		        System.out.println("An unexpected error occurred: " + e.getMessage());
		    }

		    return productTitle; // Return the last product title found or null if no match
		}
}
