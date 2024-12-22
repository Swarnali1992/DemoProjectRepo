package pageEvent;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;

import pageObject.CartPageObject;
import pageObject.CheckoutOverviewPageObject;
import pageObject.CheckoutPageObject;
import utils.IdentifyWebElement;

public class CheckoutOverviewPageEvent {
	IdentifyWebElement ele = new IdentifyWebElement();
	
	/**
	 * Clicks the "Finish" button on the Checkout Overview page.
	 * 
	 * This method retrieves the "Finish" button element on the Checkout Overview page using its ID (specified in 
	 * the {@link CheckoutOverviewPageObject} class) and performs a click action on it using the {@link WebElement}.
	 * 
	 * Once clicked, the user is expected to complete the checkout process (e.g., order confirmation, success screen, etc.).
	 * 
	 * @throws InterruptedException If the thread is interrupted while sleeping or performing actions in the method.
	 * @throws NoSuchElementException If the "Finish" button element is not found on the Checkout Overview page.
	 * @throws WebDriverException If there is a failure while interacting with the WebDriver (e.g., element not clickable).
	 * @return None
	 */
	
	 public void clickFinishBtn() throws InterruptedException, NoSuchElementException, WebDriverException {
		 ele.getWebElement("ID", CheckoutOverviewPageObject.finishBtn).click();
		 System.out.println("Clicked on Finish Button.");
	}
	 
	 /**
	  * Retrieves the title of the Checkout Overview page.
	  * 
	  * This method locates the page title element on the Checkout Overview page using its XPath (specified in 
	  * the {@link CheckoutOverviewPageObject} class), extracts the text of the title, and returns it. This title 
	  * typically indicates the page's purpose or heading (e.g., "Checkout: Overview").
	  * 
	  * @return The title of the Checkout Overview page as a {@link String}.
	  * @throws InterruptedException If the thread is interrupted while waiting or executing the method (e.g., during sleep).
	  * @throws NoSuchElementException If the page title element is not found on the Checkout Overview page.
	  * @throws WebDriverException If the WebDriver is unable to interact with the page title element (e.g., element not interactable).
	  */
	 
	 public String getPageTitle() throws InterruptedException , WebDriverException, NoSuchElementException{
		 String pageTitle = ele.getWebElement("XPATH", CheckoutOverviewPageObject.checkoutOverviewPageTitle).getText();
		 return pageTitle;
	}
	 
	 
	 
	 /**
	  * Retrieves the product title displayed in the Checkout Overview page.
	  * 
	  * This method locates the product title element on the Checkout Overview page using its XPath (specified in 
	  * the {@link CheckoutOverviewPageObject} class), extracts the text of the product, and returns it. The returned 
	  * text represents the title of the product that has been added to the cart and is being reviewed in the checkout process.
	  * 
	  * The method also logs the product title to the console for visibility or debugging purposes.
	  * 
	  * @return The title of the product displayed in the Checkout Overview page.
	  * @throws InterruptedException If the thread is interrupted while waiting or executing the method (e.g., during sleep).
	  * @throws NoSuchElementException If the product title element is not found on the Checkout Overview page.
	  * @throws WebDriverException If the WebDriver is unable to interact with the product title element (e.g., element not interactable).
	  */
	 
	 
	 
	 public String productInCart() throws InterruptedException,NoSuchElementException ,WebDriverException{
			String productCart = ele.getWebElement("XPATH",CheckoutOverviewPageObject.productTitle).getText();
			System.out.println(productCart);
			return productCart;
		}
		
}
