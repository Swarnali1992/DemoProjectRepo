package testUI;

import java.io.IOException;

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
import utils.ElementUtils;
import utils.ExcelUtils;
import utils.IdentifyWebElement;

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
	  loginPage.verifyLoginPageIsDisplayed();
	  logger.info("Login Page is displayed");
	  loginPage.fetchLoginData();
	  logger.info("Username and Password fetched from Test Data Excel.");
	  loginPage.enterLoginInfo();
	  logger.info("User provided login details.");
	  logger.info("User logged in.");
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
   * 
   * The method also handles potential interruptions during the process by catching the {@link InterruptedException}.
   * 
   * @throws IOException If there is an I/O error when fetching product data from the test data source (e.g., reading from Excel).
   * @throws InterruptedException If the thread is interrupted during the product addition process (e.g., while waiting for UI actions).
   * 
   * @return None
   */
  
  
  @Test(priority=1)
	public void verifyAddProductToCart() throws IOException, InterruptedException {
	 //swagLabsPage.verifyLoginSuccessful();
	 //System.out.println("Verified Login Successful");
	  try {
		  String searchProductInExcel = swagLabsPage.fetchProductData();
		  logger.info("Product retrieved from Test Data Excel is : " +searchProductInExcel);
		  productInCartTitle = swagLabsPage.searchProductTitle();
		  logger.info("Product searched in UI.");
		  swagLabsPage.clickGoToCartPageIcon();
		  logger.info("Product added to cart.");
		  logger.info("Navigate to Cart page.");
	  }
	  catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		String productInCartPageTitle = cartPage.productInCart();
		logger.info("Product displayed in cart : " +productInCartPageTitle);
		logger.info("Search for the added Product in the Cart page.");
		if(productInCartPageTitle.equalsIgnoreCase(productInCartTitle)) {
			System.out.println("Same Product Added in Cart");
			logger.info("Product verified. Same Product Added in Cart.");
			cartPage.clickCheckOut();
			logger.info("Go to Checkout Page.");
		}else {
			System.out.println("Same Product Not Added in Cart");
			logger.info("Same Product not added or present in Cart.");
		}
	}
	
	
	
	
	/**
	 * Verifies the details on the Checkout page, including retrieving and entering contact information.
	 * 
	 * This method performs the following steps:
	 * 
	 *   Retrieves and logs the title of the Checkout page using {@link CheckoutPage#getCheckoutPageTitle()}.
	 *   Fetches the contact information from the test data source (likely Excel) using {@link CheckoutPage#fetchContactInfo()}.
	 *   Enters the fetched contact information into the UI using {@link CheckoutPage#enterContactDetails()}.
	 *   Clicks the "Continue" button to proceed to the next page using {@link CheckoutPage#clickContinueBtn()}.
	 *   Logs each action performed during the checkout process.
	 * 
	 * 
	 * After clicking the continue button, the method waits for 5 seconds to simulate a delay in navigation before proceeding.
	 * 
	 * If the thread is interrupted during any of the actions, the method catches and logs the {@link InterruptedException.
	 * 
	 * @throws InterruptedException If the thread is interrupted during the execution, such as when waiting for page loads or UI interactions.
	 * @throws IOException If there is an issue retrieving contact information from the test data source (e.g., Excel file).
	 * @return None
	 *
	 */
	@Test(priority=3)
	public void verifyCheckOutPageDetails() throws InterruptedException, IOException {
		try {
		String pageTitle = checkoutPage.getCheckoutPageTitle();
		logger.info("Page displayed : " +pageTitle);
		System.out.println("Page Displayed : " +pageTitle);
		checkoutPage.fetchContactInfo();
		logger.info("Contact Details are fetched from Test Data Excel."); 
		checkoutPage.enterContactDetails();
		logger.info("Contact Details entered in UI."); 
		checkoutPage.clickContinueBtn();
		logger.info("Clicked on Continue button."); 
		logger.info("Navigate to Checkout Overview page."); 
		Thread.sleep(5000);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		String actualPageTitle = checkoutOverviewPage.getPageTitle();
		logger.info("Page Displayed : " +actualPageTitle);
		System.out.println("Page Displayed : " +actualPageTitle);
		String actualProductInCart = checkoutOverviewPage.productInCart();
		logger.info("Product verified in cart. : " +actualProductInCart);
		//System.out.println(actualPageTitle);
		if(actualProductInCart.equalsIgnoreCase(productInCartTitle)) {
			System.out.println("Same Product displayed in Checkout Overview page.");
			logger.info("Same Product displayed in cart in Checkout Overview page.");
			}else {
			System.out.println("Same Product not displayed in Checkout Overview page.");
			logger.info("Same Product not displayed in cart in Checkout Overview page.");
		}
		Assert.assertEquals(actualPageTitle, expectedPageTitle, message);
		checkoutOverviewPage.clickFinishBtn();
		logger.info("Clicked on Finish button.");
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
}
