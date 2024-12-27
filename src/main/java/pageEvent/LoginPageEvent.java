package pageEvent;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import utils.ConfigReaderUtils;
import utils.ExcelUtils;
import utils.IdentifyWebElement;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import org.apache.poi.ss.usermodel.Row;

public class LoginPageEvent {
	//public static String rowNum;
	IdentifyWebElement ele = new IdentifyWebElement();
	public static String sheetName = "LoginData";
	String username;
	String password;
	
	/**
	 * Fetches the login data (username and password) from an Excel file based on the configured row number.
	 * 
	 * This method retrieves the login credentials for a user from the Excel file that contains test data. 
	 * It reads the data from the specified row and column of the Excel file, storing the values in the `username` 
	 * and `password` instance variables.
	 * 
	 * The row number from which the data is fetched is determined by the configuration settings.
	 * 
	 * @throws IOException If there is an issue reading the Excel file, such as if the file is not found or is not accessible.
	 * @return None
	 * 
	 */
	
	public void fetchLoginData() throws IOException {
		//rowNum = ConfigReaderUtils.getProperty("excelrownum");
	//	System.out.println("Test Data should be fetched from row number : " + rowNum);
		try
		{
		ExcelUtils excelutil = new ExcelUtils(BaseTest.excelFilePath);
		username = excelutil.getCellData(sheetName,Integer.parseInt(BaseTest.rowNum), 0);
		password = excelutil.getCellData(sheetName,Integer.parseInt(BaseTest.rowNum), 1);
		System.out.println("Username : " + username);
		System.out.println("Password : " + password);
	}
	catch(IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println(e.getMessage());
  }
	}
	
	/**
	 * Enters login information (username and password) into the login form and clicks the login button.
	 * 
	 * This method performs the following actions:
	 * - Clicks on the username field and enters the provided username.
	 * - Clicks on the password field and enters the provided password.
	 * - Clicks on the login button to submit the form.
	 * 
	 * If there are any issues interacting with the WebDriver or if the elements are not found, the appropriate
	 * exceptions are caught and logged for troubleshooting.
	 * 
	 * @throws WebDriverException If there is an issue with the WebDriver (e.g., element not interactable, failure to click).
	 * @throws NoSuchElementException If any of the required elements (username, password, login button) are not found.
	 */
	public void enterLoginInfo() throws NoSuchElementException, WebDriverException {
	    try {
	        // Enter username
	        WebElement usernameField = ele.getWebElement("ID", LoginPageObject.username);
	        usernameField.click();
	        usernameField.sendKeys(username);
	        
	        // Enter password
	        WebElement passwordField = ele.getWebElement("ID", LoginPageObject.password);
	        passwordField.click();
	        passwordField.sendKeys(password);
	        
	        // Click login button
	        WebElement loginButton = ele.getWebElement("ID", LoginPageObject.loginBtn);
	        loginButton.click();
	        
	        System.out.println("Login information entered and login button clicked successfully.");
	    } catch (NoSuchElementException e) {
	        // Handle case where one of the elements (username, password, login button) is not found
	        System.out.println("Error: One or more elements (username, password, or login button) not found. " + e.getMessage());
	        e.printStackTrace(); // Log stack trace for debugging
	    } catch (WebDriverException e) {
	        // Handle issues with WebDriver interaction (e.g., unable to click or enter text)
	        System.out.println("Error: There was an issue interacting with the WebDriver. " + e.getMessage());
	        e.printStackTrace(); // Log stack trace for debugging
	    } catch (Exception e) {
	        // Catch any other unexpected errors
	        System.out.println("An unexpected error occurred while entering login information: " + e.getMessage());
	        e.printStackTrace(); // Log stack trace for debugging
	    }
	}
	
	
	/**
	 * Verifies that the Login Page is displayed by checking the presence of the login button.
	 * 
	 * This method checks if the login button is visible on the Login Page, which is an indicator that the page has loaded correctly. 
	 * It locates the button using its ID from the `LoginPageObject.loginBtn` element locator.
	 * 
	 * If the login button is found, the test passes. If not, an assertion failure is thrown.
	 * 
	 * @throws WebDriverException If there is an issue interacting with the WebDriver during the execution of the method.
	 * @throws NoSuchElementException If the login button cannot be found on the page, implying the login page is not displayed correctly.
	 * @return None
	 *
	 */
	
	public void verifyLoginPageIsDisplayed() throws WebDriverException, NoSuchElementException{ 
		try {
		Assert.assertTrue(ele.getWebElements("ID", LoginPageObject.loginBtn).size()>0, " Login Page not displayed. ");
	}
	catch (NoSuchElementException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println(e.getMessage());
  }
	}
	
	
	/**
	 * Clicks on the Login button on the login page.
	 * 
	 * This method tries to locate the Login button using its ID and click it. If the element is not
	 * found or there is an issue interacting with the WebDriver, appropriate exceptions are caught and logged.
	 * 
	 * @throws WebDriverException If there is an issue with the WebDriver.
	 * @throws NoSuchElementException If the Login button element is not found.
	 */
	public void clickLoginBtn() {
	    try {
	        // Try to find the Login button using its ID and click it
	        ele.getWebElement("ID", LoginPageObject.loginBtn).click();
	        System.out.println("Login button clicked successfully.");
	    } catch (NoSuchElementException e) {
	        // Handle case where the Login button is not found
	        System.out.println("Error: Login button not found. " + e.getMessage());
	        e.printStackTrace(); // Log the stack trace for debugging
	    } catch (WebDriverException e) {
	        // Handle any WebDriver-related issues (e.g., browser issues, click failures)
	        System.out.println("Error: There was an issue with the WebDriver while clicking the Login button. " + e.getMessage());
	        e.printStackTrace(); // Log the stack trace for debugging
	    } catch (Exception e) {
	        // Catch any other unforeseen exceptions
	        System.out.println("An unexpected error occurred while trying to click the Login button: " + e.getMessage());
	        e.printStackTrace(); // Log the stack trace for debugging
	    }
	}
	
	/**
	 * This method verifies the update of a specific value in an Excel sheet.
	 * It writes a new value to a specified cell in an Excel sheet (row 2, column 3) 
	 * and ensures that the value is successfully updated.
	 * 
	 * 	The method interacts with an Excel file and updates a cell in a specific row and column.
	 * 
	 * The method uses the {@link ExcelUtils#writeToExcel(String, String, int, int, String)} 
	 * utility to perform the update operation on the Excel file.</p>
	 * 
	 * @throws InterruptedException If the thread is interrupted during execution.
	 * @throws IOException If an error occurs while reading or writing the Excel file.
	 * @return None
	 */
	
	public void updateValueInExcel() throws InterruptedException {
		
	        // Row 2 (0-based index, so it's row 1 in the Excel file) and Column 3 (which is column 2, 0-based index)
	        int rowNum = 1;  // Row 2 (0-based index)
	        int colNum = 3;  // Column 3 (0-based index)

	        // The value you want to write
	        String value = "Logged in as " + username;

	        try {
	            // Write value to the specified cell in Excel
	            ExcelUtils.writeToExcel(BaseTest.excelFilePath, sheetName, rowNum, colNum, value);
	            System.out.println("Value written to Excel successfully.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	}

