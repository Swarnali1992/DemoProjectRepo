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
	 * Fetches the contact information (first name, last name, and postal code) from an Excel file.
	 * 
	 * This method uses the {@link ExcelUtils} class to read contact information from an Excel file. The data is 
	 * retrieved from the specified row number and stored in the respective fields: first name, last name, and postal 
	 * code. The row number and sheet name are defined in the test setup, and the Excel file is used as the source for 
	 * the contact information.
	 * 
	 * The retrieved contact details are printed to the console for debugging purposes.
	 * 
	 * @throws NumberFormatException If there is an issue parsing the row number to an integer or fetching data from the specified row.
	 * @throws IOException If there is an issue reading the Excel file (e.g., file not found or access issues).
	 * @return None
	 *
	 */
	
	public void fetchContactInfo() throws NumberFormatException, IOException {
		  ExcelUtils excelutil = new ExcelUtils(BaseTest.excelFilePath);
			try {
				firstName = excelutil.getCellData(sheetName,Integer.parseInt(BaseTest.rowNum), 0);
				lastName = excelutil.getCellData(sheetName,Integer.parseInt(BaseTest.rowNum), 1);
				postalCode = excelutil.getCellData(sheetName,Integer.parseInt(BaseTest.rowNum), 2);
				System.out.println("The First Name retrieved from Excel : " + firstName);
				System.out.println("The Last Name retrieved from Excel : " + lastName);
				System.out.println("The Postal Code retrieved from Excel : " + postalCode);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	/**
	 * Retrieves the title of the Checkout page.
	 * 
	 * This method uses the {@link WebElement} locator to find the element representing the title of the Checkout page. 
	 * It locates the element via XPath (defined in the {@link CheckoutPageObject} class) and returns the text of the element.
	 * 
	 * The returned string is typically the title or heading of the Checkout page, such as "Checkout: Information".
	 * 
	 * @return The title of the Checkout page as a {@link String}.
	 * @throws NoSuchElementException If the Checkout page title element cannot be found using the provided XPath.
	 * @throws WebDriverException If there is an issue interacting with the page title element (e.g., WebDriver is unable to retrieve the element's text).
	 * 
	 * 
	 */
	
	
	
	 
	 public String getCheckoutPageTitle() throws NoSuchElementException, WebDriverException{
		 return ele.getWebElement("XPATH", CheckoutPageObject.checkoutPageTitle).getText();
	 }
	 
	 
	 /**
	  * Enters the user's contact details (first name, last name, postal code) into the Checkout page.
	  * 
	  * This method interacts with the form fields on the Checkout page, specifically for entering the user's first name, last name,
	  * and postal code. It retrieves the contact details from the test data (typically from an Excel file or other data source), 
	  * clears the fields, and enters the appropriate values.
	  *
	  * The method performs the following steps:
	  * 
	  *   Clicks on and clears the first name input field, then enters the first name.
	  *   Clicks on and clears the last name input field, then enters the last name.
	  *   Clicks on and clears the postal code input field, then enters the postal code.
	  *   Waits for a brief period (5 seconds) after filling the form.
	  * 
	  * 
	  * @throws WebDriverException If there is a WebDriver-related issue, such as being unable to find an element.
	  * @throws NoSuchElementException If a web element could not be found on the page.
	  * @throws InterruptedException If the thread is interrupted while the method is sleeping for 5 seconds.
	  * 
	  * @return None
	  */
	 
	 
	 
	 
	 public void enterContactDetails() throws WebDriverException, NoSuchElementException, InterruptedException{
		 ele.getWebElement("ID", CheckoutPageObject.firstName).click();
		 ele.getWebElement("ID", CheckoutPageObject.firstName).clear();
		 ele.getWebElement("ID", CheckoutPageObject.firstName).sendKeys(firstName);
		 ele.getWebElement("ID", CheckoutPageObject.lastName).click();
		 ele.getWebElement("ID", CheckoutPageObject.lastName).clear();
		 ele.getWebElement("ID", CheckoutPageObject.lastName).sendKeys(lastName);
		 ele.getWebElement("ID", CheckoutPageObject.postalCode).click();
		 ele.getWebElement("ID", CheckoutPageObject.postalCode).clear();
		 ele.getWebElement("ID", CheckoutPageObject.postalCode).sendKeys(postalCode);
		 Thread.sleep(5000);
	 }
	 
	 
	 /**
	  * Clicks on the "Continue" button on the Checkout page.
	  * 
	  * This method locates the "Continue" button using its ID, and clicks on it to proceed to the next step in the checkout process.
	  * 
	  * The following actions are performed:
	  * 
	  *   Locates the "Continue" button using the element locator defined in the `CheckoutPageObject.continueBtn`.
	  *   Clicks on the button to proceed.
	  *   Prints a confirmation message to the console indicating that the button was clicked.
	  * 
	  * 
	  * @throws InterruptedException If the thread is interrupted while executing the method, such as during a sleep or wait.
	  * @return None
	  */
	 public void clickContinueBtn() throws InterruptedException {
		 ele.getWebElement("ID", CheckoutPageObject.continueBtn).click();
		 System.out.println("Clicked on Continue Button.");
	}
}
