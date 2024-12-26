package testUI;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.BaseTest;
import pageEvent.CartPageEvent;
import pageEvent.CheckoutOverviewPageEvent;
import pageEvent.CheckoutPageEvent;
import pageEvent.LoginPageEvent;
import pageEvent.SwagLabsPageEvent;
import utils.CaptureRequest;
import utils.DatabaseUtils;
import utils.ElementUtils;
import utils.ExcelUtils;
import utils.IdentifyWebElement;
import utils.PerformanceTracker;
import utils.WaitUtils;

public class EndToEndTest extends BaseTest {
	IdentifyWebElement ele = new IdentifyWebElement();
	LoginPageEvent loginPage = new LoginPageEvent();
	SwagLabsPageEvent swagLabsPage = new SwagLabsPageEvent();
	ElementUtils eleUtil = new ElementUtils();
	CartPageEvent cartPage = new CartPageEvent();
	CheckoutPageEvent checkoutPage = new CheckoutPageEvent();
	CheckoutOverviewPageEvent checkoutOverviewPage = new CheckoutOverviewPageEvent();
	String productInCartTitle;
	
	/**
	 * Verifies the login functionality by interacting with the login page.
	 * 
	 * This method performs the following actions to verify the login process:
	 * 
	 *   Checks if the login page is displayed using {@link LoginPage#verifyLoginPageIsDisplayed()}.
	 *   Fetches login credentials (username and password) from a test data source (Excel) using {@link LoginPage#fetchLoginData()}.
	 *   Enters the fetched login credentials into the login form using {@link LoginPage#enterLoginInfo()}.
	 *   Logs the actions at each step for verification purposes.
	 * 
	 * 
	 * If any error occurs while entering login details, the method will catch the exception and print the stack trace.</p>
	 * 
	 * @throws InterruptedException If the thread is interrupted while performing actions on the login page (e.g., during wait times).
	 * @throws IOException If an I/O error occurs while fetching login data from the test data source (Excel).
	 *
	 * @return None
	 */
	
  @Test(priority=0)
  public void verifyLogin() throws InterruptedException, IOException {
	  try {
	  logger.info("Login Page is displayed");
	  // fetch login username and password from Test Data Excel
	  loginPage.fetchLoginData();
	  logger.info("Username and Password fetched from Test Data Excel.");
	  logger.info("Login Page is displayed");
	  loginPage.enterLoginInfo();
	  logger.info("User provided login details.");
	  logger.info("User logged in.");
	  
	  }
	  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
	  }
}
  
  
  /**
   * Verifies the process of adding a product to the shopping cart.
   * 
   * This method performs the following actions:
   * 
   *   Fetches the product data (e.g., product name or details) from the test data source (Excel) using {@link SwagLabsPage#fetchProductData()}.
   *   Searches for the product title in the UI using {@link SwagLabsPage#searchProductTitle()}.
   *   Clicks the "Go to Cart" icon to add the product to the cart using {@link SwagLabsPage#clickGoToCartPageIcon()}.
   *   Logs each of these actions to provide a detailed trace of the process.
   * 
   * The method also handles potential interruptions during the process by catching the {@link InterruptedException}.
   * 
   * @return None
 * @throws Exception 
   */
  
  
  @Test(priority=1)
	public void verifySearchProductAndAddProductToCart() throws Exception {
	  //Verify login success
	 swagLabsPage.verifyLoginSuccessful();
	 logger.info("User logged in successfully.");
	  try {
		  //fetch product data from Test Data Excel that user needs to search
		  String searchProductInExcel = swagLabsPage.fetchProductData();
		  Thread.sleep(5000);
		  logger.info("Product retrieved from Test Data Excel is : " +searchProductInExcel);
		  // search product in UI and add to cart
		  productInCartTitle = swagLabsPage.searchProductTitle();
		  logger.info("Product searched in UI.");
		  logger.info("Product added to cart.");
		  // Click to Go to Cart Page
		  swagLabsPage.clickGoToCartPageIcon();
		  logger.info("Navigate to Cart page.");
	  }
	  catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
	  }
	 //swagLabsPage.clickOpenMenuIcon();
	 //eleUtil.selectSubMenu("About"); 
	}
	
  
  /**
   * Verifies that the correct product has been added to the cart.
   * 
   * This method performs the following steps:
   * 
   *   Retrieves the product title displayed in the cart page using {@link CartPage#productInCart()}.
   *   Compares the retrieved product title with the previously stored product title {@code productInCartTitle}.
   *   If the product titles match, it logs the success, prints a message indicating the same product has been added, and navigates to the checkout page by clicking the checkout button ({@link CartPage#clickCheckOut()}).
   *   If the product titles do not match, it logs the failure, prints a message indicating the product was not added correctly to the cart.
   * 
   * 
   * The method also handles any interruptions during execution by catching the {@link InterruptedException}.
   * 
   * @throws InterruptedException If the thread is interrupted during the process (e.g., while waiting for elements to load or during UI actions).
   * @return None
   */
  
  
	@Test(priority=2)
	public void verifyProductAddedToCart() throws InterruptedException {
		String message = "Same Product not added to Cart.";
		try {
			logger.info("Search for the added Product in the Cart page.");
			//fetch the product title in cart in Cart page
			String productInCartPageTitle = cartPage.productInCart(driver);
			logger.info("Product displayed in cart : " +productInCartPageTitle);
		
			// Validate the product title of cart page with the product searched
			if(productInCartPageTitle.equalsIgnoreCase(productInCartTitle)) {
			System.out.println("Same Product Added in Cart");
			logger.info("Product verified. Same Product Added in Cart.");
			//Click on Checkout button
			cartPage.clickCheckOut();
			logger.info("Click Checkout button.");
			logger.info("Go to Checkout Page.");
		}else {
			Assert.assertEquals(productInCartPageTitle, productInCartPageTitle, message);
			System.out.println("Same Product Not Added in Cart");
			logger.info("Same Product not added or present in Cart.");
		}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
	  }
	}
	
	
	
	
	/**
	 * This method verifies the details of the Checkout page, including fetching the page title,
	 * verifying the title with the expected value, fetching contact information from an Excel file,
	 * entering the details into the UI, and clicking the continue button to proceed to the Checkout Overview page.
	 * It also includes proper logging for each step of the process.
	 * 
	 * @throws InterruptedException if the thread is interrupted while the test is running.
	 * @throws IOException if an I/O error occurs, particularly when fetching data from the Test Data Excel sheet.
	 * @return None
	 */
	@Test(priority=3)
	public void verifyCheckOutPageDetails() throws InterruptedException, IOException {
		String expectedPageTitle = "Checkout: Your Information";
		String message = "Checkout: Your Information Page is not getting displayed.";
		try {
			//fetch page title
		String actualPageTitle = checkoutPage.getCheckoutPageTitle();
		logger.info("Page displayed : " +actualPageTitle);
		System.out.println("Page Displayed : " +actualPageTitle);
		// verify page title
		Assert.assertEquals(actualPageTitle, expectedPageTitle, message);
		//fetch contact details from Test Data Excel sheet
		checkoutPage.fetchContactInfo();
		logger.info("Contact Details are fetched from Test Data Excel."); 
		//enter the details in UI
		checkoutPage.enterContactDetails();
		logger.info("Contact Details entered in UI."); 
		//Click on Continue button
		checkoutPage.clickContinueBtn();
		logger.info("Clicked on Continue button."); 
		logger.info("Navigate to Checkout Overview page."); 
		CaptureRequest cp = new CaptureRequest();
//		PerformanceTracker pT = new PerformanceTracker(cp.devTools);
//		pT.captureMetrics();
//		System.out.println();
		
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
	  }
	}
	
	/**
	 * Verifies the details on the Checkout Overview page.
	 * 
	 * This method performs the following steps:
	 * 
	 *   Verifies the page title of the Checkout Overview page and compares it with the expected title.
	 *   Logs the actual product displayed in the cart on the Checkout Overview page.
	 *   Compares the product displayed in the cart with the product added to the cart earlier.
	 *   Asserts that the page title matches the expected title, throwing an assertion error if they don't match.
	 *   Clicks the "Finish" button to complete the checkout process.
	 * 
	 * 
	 * If the product displayed in the cart matches the expected product, the test proceeds by clicking the "Finish" button.
	 * 
	 * If the product does not match, the test logs an error but still proceeds to click the "Finish" button.
	 * 
	 * The method also catches and handles the {@link InterruptedException} during execution.
	 * 
	 * @throws InterruptedException If the thread is interrupted during execution, such as while waiting for elements or during page load.
	 * @return None
	 */
	
	
	@Test(priority=4)
	public void verifyCheckOutOverviewPageDetails() throws InterruptedException {
		String expectedPageTitle = "Checkout: Overview";
		String message = "Checkout Overview Page is not getting displayed.";
		try {
			//fetch page title
		String actualPageTitle = checkoutOverviewPage.getPageTitle();
		logger.info("Page Displayed : " +actualPageTitle);
		System.out.println("Page Displayed : " +actualPageTitle);
		//fetch product title in cart
		String actualProductInCart = checkoutOverviewPage.productInCart();
		logger.info("Product verified in cart. : " +actualProductInCart);
		//System.out.println(actualPageTitle)he
		// Validate product title in cart with the actual product title added to cart
		if(actualProductInCart.equalsIgnoreCase(productInCartTitle)) {
			System.out.println("Same Product displayed in Checkout Overview page.");
			logger.info("Same Product displayed in cart in Checkout Overview page.");
			}else {
			System.out.println("Same Product not displayed in Checkout Overview page.");
			logger.info("Same Product not displayed in cart in Checkout Overview page.");
		}
		// verify page title
		Assert.assertEquals(actualPageTitle, expectedPageTitle, message);
		//Click on Finish button
		checkoutOverviewPage.clickFinishBtn();
		logger.info("Clicked on Finish button.");
		
	
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
	}
	}
	
	
	/**
	 * Verifies that a value is updated in an Excel file by calling the {@link LoginPage#updateValueInExcel()} method.
	 * This method logs the steps of the process, including information about updating the value in the Excel file.
	 * 
	 * 	The method catches and handles any {@link InterruptedException} that might be thrown during the update process.
	 * 
	 * Steps performed:
	 * 
	 *     Logs the start of the value update process.
	 *     Calls the LoginPage#updateValueInExcel()} method to update the value in the Excel file.
	 *     Logs a success message after the value is updated.
	 * 
	 *
	 * 
	 * @throws InterruptedException If the thread is interrupted while the value update process is running.
	 */
	@Test(priority=5)
	public void verifyUpdateValueInExcel() throws InterruptedException {
		try {
			//Update the value in Excel
			logger.info("Verify Value updated in Excel");
            loginPage.updateValueInExcel();
            logger.info("Value updated in Excel.");
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
     	
}
