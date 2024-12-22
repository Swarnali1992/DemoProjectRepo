package pageEvent;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import utils.ConfigReaderUtils;
import utils.ExcelUtils;
import utils.IdentifyWebElement;

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
//		rowNum = ConfigReaderUtils.getProperty("excelrownum");
//		System.out.println("Test Data should be fetched from row number : " + rowNum);
		ExcelUtils excelutil = new ExcelUtils(BaseTest.excelFilePath);
		username = excelutil.getCellData(sheetName,Integer.parseInt(BaseTest.rowNum), 0);
		password = excelutil.getCellData(sheetName,Integer.parseInt(BaseTest.rowNum), 1);
		System.out.println("Username : " + username);
		System.out.println("Password : " + password);
	}
	
	/**
	 * Enters the username and password into the respective fields on the Login Page and clicks the login button to submit the login form.
	 * 
	 * This method performs the following actions:
	 * 
	 *   Locates the "username" field by its ID and enters the specified username.
	 *   Locates the "password" field by its ID and enters the specified password.
	 *   Clicks the "login" button to submit the login form.</li>
	 * 
	 * 
	 * @throws WebDriverException If an issue occurs during interaction with the web elements (e.g., element not found).
	 * @throws NoSuchElementException If any of the specified elements cannot be found in the DOM.
	 * 
	 * @return None
	 */
	
	
	
	public void enterLoginInfo() throws WebDriverException, NoSuchElementException {
	  ele.getWebElement("ID", LoginPageObject.username).click();
	  ele.getWebElement("ID", LoginPageObject.username).sendKeys(username); 
	  ele.getWebElement("ID", LoginPageObject.password).click();
	  ele.getWebElement("ID", LoginPageObject.password).sendKeys(password);
	  ele.getWebElement("ID",LoginPageObject.loginBtn).click(); 
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
	
	public void verifyLoginPageIsDisplayed() throws WebDriverException, NoSuchElementException
	{
		Assert.assertTrue(ele.getWebElements("ID", LoginPageObject.loginBtn).size()>0, " Login Page not displayed. ");
	}
	
	
	/**
	 * Clicks the "Login" button on the Login Page.
	 * 
	 * This method locates the "Login" button using its ID, and simulates a click on the button to initiate the login process.
	 * 
	 * The following actions are performed:
	 * 
	 *   Locates the "Login" button using the element locator defined in the `LoginPageObject.loginBtn`.
	 *   Clicks on the button to initiate the login action.
	 * 
	 * 
	 * @throws WebDriverException If there is an issue interacting with the WebDriver during the click action.
	 * @throws NoSuchElementException If the login button cannot be found on the page.
	 * 
	 * @return None
	 */
	
	
	
	public void clickLoginBtn()throws WebDriverException, NoSuchElementException  {
		ele.getWebElement("ID", LoginPageObject.loginBtn).click();
	}
	
	
	
	}

