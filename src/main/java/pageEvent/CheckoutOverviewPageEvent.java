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
	 * This method clicks the Finish button on the Checkout Overview page.
	 * It handles potential exceptions that might occur while interacting with the WebDriver.
	 *
	 * @throws InterruptedException If the thread is interrupted during the process.
	 * @throws NoSuchElementException If the Finish button element cannot be found.
	 * @throws WebDriverException If there is an issue while interacting with the WebDriver.
	 */
	public void clickFinishBtn() throws InterruptedException, NoSuchElementException, WebDriverException {
	    try {
	        // Attempt to click the Finish button
	        ele.getWebElement("ID", CheckoutOverviewPageObject.finishBtn).click();
	        System.out.println("Clicked on Finish Button.");

	    } catch (NoSuchElementException e) {
	        // Log the error and rethrow the exception if the element is not found
	        System.out.println("Error: Finish button element not found.");
	        e.printStackTrace();
	        throw new NoSuchElementException("Finish button element not found.", e);

	    } catch (WebDriverException e) {
	        // Log the error and rethrow the exception if there is a WebDriver interaction issue
	        System.out.println("Error: WebDriver exception occurred while clicking Finish button.");
	        e.printStackTrace();
	        throw new WebDriverException("WebDriver issue while clicking Finish button.", e);

	    } catch (Exception e) {
	        // Catch any other unforeseen exceptions
	        System.out.println("An unexpected error occurred while clicking Finish button: " + e.getMessage());
	        e.printStackTrace();
	        throw new RuntimeException("Unexpected error while clicking Finish button.", e);
	    }
	}
	 
	/**
	 * This method retrieves the page title from the Checkout Overview page.
	 * It handles potential exceptions that may occur while interacting with the WebDriver and fetching the page title.
	 *
	 * @return The title of the Checkout Overview page.
	 * @throws InterruptedException If the thread is interrupted during the process.
	 * @throws WebDriverException If there is an issue with the WebDriver during the interaction.
	 * @throws NoSuchElementException If the page title element cannot be found.
	 */
	public String getPageTitle() throws InterruptedException, WebDriverException, NoSuchElementException {
	    String pageTitle = null;
	    try {
	        // Attempt to get the page title by fetching the text from the element
	        pageTitle = ele.getWebElement("XPATH", CheckoutOverviewPageObject.checkoutOverviewPageTitle).getText();
	        System.out.println("Page Title: " + pageTitle);
	    } catch (NoSuchElementException e) {
	        // Log the error and rethrow the exception if the page title element cannot be found
	        System.out.println("Error: Page title element not found.");
	        e.printStackTrace();
	        throw new NoSuchElementException("Checkout Overview page title element not found.", e);
	    } catch (WebDriverException e) {
	        // Log the error and rethrow the exception if there is a WebDriver interaction issue
	        System.out.println("Error: WebDriver exception occurred while retrieving the page title.");
	        e.printStackTrace();
	        throw new WebDriverException("WebDriver issue while retrieving the page title.", e);
	    } catch (Exception e) {
	        // Catch any other unforeseen exceptions
	        System.out.println("An unexpected error occurred while retrieving the page title: " + e.getMessage());
	        e.printStackTrace();
	        throw new RuntimeException("Unexpected error while retrieving the page title.", e);
	    }
	    return pageTitle;
	}
	 
	 
	/**
	 * This method retrieves the product title displayed in the cart from the Checkout Overview page.
	 * It handles potential exceptions that may occur while interacting with the WebDriver to fetch the product title.
	 *
	 * @return The title of the product in the cart.
	 * @throws InterruptedException If the thread is interrupted during the process.
	 * @throws NoSuchElementException If the product title element cannot be found on the page.
	 * @throws WebDriverException If there is an issue with the WebDriver during the interaction.
	 */
	public String productInCart() throws InterruptedException, NoSuchElementException, WebDriverException {
	    String productCart = null;
	    try {
	        // Attempt to retrieve the product title from the cart
	        productCart = ele.getWebElement("XPATH", CheckoutOverviewPageObject.productTitle).getText();
	        System.out.println("Product in Cart: " + productCart);
	    } catch (NoSuchElementException e) {
	        // Log the error and rethrow the exception if the product title element cannot be found
	        System.out.println("Error: Product title element not found in cart.");
	        e.printStackTrace();
	        throw new NoSuchElementException("Product title element not found in cart.", e);
	    } catch (WebDriverException e) {
	        // Log the error and rethrow the exception if there is a WebDriver interaction issue
	        System.out.println("Error: WebDriver exception occurred while retrieving product title in cart.");
	        e.printStackTrace();
	        throw new WebDriverException("WebDriver issue while retrieving product title in cart.", e);
	     } catch (Exception e) {
	        // Catch any other unforeseen exceptions
	        System.out.println("An unexpected error occurred while retrieving product title in cart: " + e.getMessage());
	        e.printStackTrace();
	        throw new RuntimeException("Unexpected error while retrieving product title in cart.", e);
	    }
	    return productCart;
	}
}
