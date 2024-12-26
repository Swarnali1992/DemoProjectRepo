package pageEvent;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;

import base.BaseTest;
import pageObject.CheckoutPageObject;
import pageObject.LoginPageObject;
import utils.ExcelUtils;
import utils.IdentifyWebElement;

public class CheckoutPageEvent {
	IdentifyWebElement ele = new IdentifyWebElement();
	public static String sheetName = "CheckOut";
	String firstName;
	String lastName;
	String postalCode;
	
	
	/**
	 * Fetches the contact information (First Name, Last Name, and Postal Code) from an Excel file
	 * and prints the retrieved values.
	 * 
	 * This method reads contact information from the Excel file and stores it in instance variables
	 * for further use. It handles potential issues related to reading data from the Excel file,
	 * and logs the appropriate error messages in case of failure.
	 * 
	 * @throws NumberFormatException If the row number is not a valid integer.
	 * @throws IOException If there is an issue reading the Excel file.
	 * @return None
	 */
	public void fetchContactInfo() throws NumberFormatException,IOException{
	    ExcelUtils excelutil = null;
	    try {
	        excelutil = new ExcelUtils(BaseTest.excelFilePath);
	        firstName = excelutil.getCellData(sheetName, Integer.parseInt(BaseTest.rowNum), 0);
	        lastName = excelutil.getCellData(sheetName, Integer.parseInt(BaseTest.rowNum), 1);
	        postalCode = excelutil.getCellData(sheetName, Integer.parseInt(BaseTest.rowNum), 2);

	        // Log the retrieved data for debugging
	        System.out.println("The First Name retrieved from Excel : " + firstName);
	        System.out.println("The Last Name retrieved from Excel : " + lastName);
	        System.out.println("The Postal Code retrieved from Excel : " + postalCode);

	    } catch (NumberFormatException e) {
	        // Log and handle NumberFormatException if the row number is not a valid integer
	        System.out.println("Error: Invalid row number format. Please check the row number value.");
	        e.printStackTrace();  // Print stack trace for debugging

	    } catch (IOException e) {
	        // Log and handle IOException if there are issues reading the Excel file
	        System.out.println("Error: Unable to read the Excel file. Please check the file path.");
	        e.printStackTrace();  // Print stack trace for debugging

	    } catch (Exception e) {
	        // Catch any other unexpected exceptions
	        System.out.println("An unexpected error occurred while fetching contact info: " + e.getMessage());
	        e.printStackTrace();  // Print stack trace for debugging
	    }
	}
	
	
	/**
	 * Fetches the title of the Checkout page from the UI.
	 * 
	 * This method attempts to retrieve the title of the checkout page using the provided 
	 * XPath to locate the element. It handles potential errors related to locating the element
	 * or interacting with the web driver.
	 * 
	 * @return The title of the Checkout page as a String.
	 * @throws NoSuchElementException If the checkout page title element is not found.
	 * @throws WebDriverException If there are any issues interacting with the WebDriver.
	 */
	public String getCheckoutPageTitle() {
	    try {
	        // Attempt to fetch the checkout page title
	        return ele.getWebElement("XPATH", CheckoutPageObject.checkoutPageTitle).getText();
	    } catch (NoSuchElementException e) {
	        // Log and handle the case where the element is not found
	        System.out.println("Error: Checkout page title element not found.");
	        e.printStackTrace();  // Print the stack trace for debugging
	        throw new NoSuchElementException("Checkout page title element not found.", e);
	    } catch (WebDriverException e) {
	        // Log and handle the case where there's a WebDriver interaction issue
	        System.out.println("Error: WebDriver exception occurred while fetching checkout page title.");
	        e.printStackTrace();  // Print the stack trace for debugging
	        throw new WebDriverException("WebDriver issue while fetching checkout page title.", e);
	    } catch (Exception e) {
	        // Catch any other unexpected exceptions
	        System.out.println("An unexpected error occurred while getting the checkout page title: " + e.getMessage());
	        e.printStackTrace();  // Print the stack trace for debugging
	        throw new RuntimeException("Unexpected error while fetching checkout page title.", e);
	    }
	}
	/**
	 * This method is used to enter the contact details (first name, last name, and postal code) 
	 * in the respective fields on the checkout page.
	 * It clears any existing values and enters new values provided by the test data.
	 * The method handles potential exceptions that might occur during the interaction with the web elements.
	 * 
	 * @throws WebDriverException If an issue occurs while interacting with the WebDriver.
	 * @throws NoSuchElementException If any of the web elements cannot be found on the page.
	 * @throws InterruptedException If the thread is interrupted during the sleep operation.
	 * return None
	 */
	public void enterContactDetails() throws WebDriverException, NoSuchElementException, InterruptedException {
	    try {
	        // Enter First Name
	        ele.getWebElement("ID", CheckoutPageObject.firstName).click();
	        ele.getWebElement("ID", CheckoutPageObject.firstName).clear();
	        ele.getWebElement("ID", CheckoutPageObject.firstName).sendKeys(firstName);

	        // Enter Last Name
	        ele.getWebElement("ID", CheckoutPageObject.lastName).click();
	        ele.getWebElement("ID", CheckoutPageObject.lastName).clear();
	        ele.getWebElement("ID", CheckoutPageObject.lastName).sendKeys(lastName);

	        // Enter Postal Code
	        ele.getWebElement("ID", CheckoutPageObject.postalCode).click();
	        ele.getWebElement("ID", CheckoutPageObject.postalCode).clear();
	        ele.getWebElement("ID", CheckoutPageObject.postalCode).sendKeys(postalCode);

	        // Pause for 5 seconds to simulate wait time (if needed for form processing)
	        Thread.sleep(5000);
	    } catch (WebDriverException e) {
	        // Log WebDriver exceptions and rethrow them
	        System.out.println("WebDriverException occurred while interacting with the web elements.");
	        e.printStackTrace();
	        throw new WebDriverException("WebDriver issue encountered while entering contact details.", e);
	    } catch (InterruptedException e) {
	        // Log InterruptedException and rethrow it
	        System.out.println("Thread was interrupted while waiting.");
	        e.printStackTrace();
	        throw new InterruptedException();
	    } catch (Exception e) {
	        // Catch any other unforeseen exceptions
	        System.out.println("An unexpected error occurred while entering contact details: " + e.getMessage());
	        e.printStackTrace();
	        throw new RuntimeException("Unexpected error while entering contact details.", e);
	    }
	}
	 
	 /**
	  * This method attempts to click the "Continue" button on the checkout page.
	  * It uses the provided locator strategy to find the button element by its ID and performs a click action.
	  * The method handles various exceptions related to element interactions.
	  * 
	  * @throws NoSuchElementException If the "Continue" button element cannot be found on the page.
	  * @throws WebDriverException If there is an issue interacting with the WebDriver while attempting to click the button.
	  * @throws RuntimeException If any other unforeseen error occurs during the execution of the method.
	  * @return None
	  */
	 public void clickContinueBtn() throws NoSuchElementException, WebDriverException{
	     try {
	         // Attempt to click the Continue button
	         ele.getWebElement("ID", CheckoutPageObject.continueBtn).click();
	         System.out.println("Clicked on Continue Button.");
	     } catch (NoSuchElementException e) {
	         // Log the error and rethrow the exception if the element is not found
	         System.out.println("Error: Continue button element not found.");
	         e.printStackTrace();
	         throw new NoSuchElementException("Continue button element not found.", e);
	     } catch (WebDriverException e) {
	         // Log the error and rethrow the exception if there is a WebDriver interaction issue
	         System.out.println("Error: WebDriver exception occurred while clicking Continue button.");
	         e.printStackTrace();
	         throw new WebDriverException("WebDriver issue while clicking Continue button.", e);
	     } catch (Exception e) {
	         // Catch any other unforeseen exceptions
	         System.out.println("An unexpected error occurred while clicking Continue button: " + e.getMessage());
	         e.printStackTrace();
	         throw new RuntimeException("Unexpected error while clicking Continue button.", e);
	     }
	 }
}
