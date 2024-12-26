package pageEvent;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import pageObject.CartPageObject;
import pageObject.SwagLabsPageObject;
import utils.IdentifyWebElement;
import utils.WaitUtils;
public class CartPageEvent {
	IdentifyWebElement ele = new IdentifyWebElement();
	
	/**
	 * This method retrieves the product title displayed in the cart.
	 * It waits for the visibility of the product title element, fetches its text, 
	 * and returns the product title as a string.
	 *
	 * @param driver The WebDriver instance used to interact with the browser.
	 * @return The product title displayed in the cart.
	 * @throws NoSuchElementException If the product title element cannot be found.
	 * @throws WebDriverException If an issue occurs while interacting with the WebDriver.
	 * 
	 */
	public String productInCart(WebDriver driver) throws NoSuchElementException, WebDriverException {
	    try {
	        // Wait for the visibility of the product title element
	        WaitUtils.waitForVisibility(driver, ele.getWebElement("XPATH", CartPageObject.productTitle), 5000);

	        // Fetch the product title text from the cart
	        String productCart = ele.getWebElement("XPATH", CartPageObject.productTitle).getText();

	        // Log the product title
	        System.out.println("Product in cart: " + productCart);

	        // Return the product title
	        return productCart;

	    } catch (NoSuchElementException e) {
	        // Log the error and rethrow the exception if the element is not found
	        System.out.println("Error: Product title element not found in the cart.");
	        e.printStackTrace();
	        throw new NoSuchElementException("Product title element not found in the cart.", e);

	    } catch (WebDriverException e) {
	        // Log the error and rethrow the exception if there is a WebDriver interaction issue
	        System.out.println("Error: WebDriver exception occurred while fetching product title.");
	        e.printStackTrace();
	        throw new WebDriverException("WebDriver issue while fetching product title from the cart.", e);

	    } catch (Exception e) {
	        // Catch any other unforeseen exceptions
	        System.out.println("An unexpected error occurred while retrieving product title: " + e.getMessage());
	        e.printStackTrace();
	        throw new RuntimeException("Unexpected error while retrieving product title from the cart.", e);
	    }
	}
	
	/**
	 * This method clicks on the checkout button on the cart page.
	 * 
	 * @throws NoSuchElementException If the element to click is not found.
	 * @throws WebDriverException If there's an issue interacting with the WebDriver during the click.
	 */
	public void clickCheckOut() throws NoSuchElementException, WebDriverException {
	    try {
	        // Attempt to click the checkout button
	        ele.getWebElement("ID", CartPageObject.btnCheckOut).click();
	        System.out.println("Clicked on the checkout button.");
	    } catch (NoSuchElementException e) {
	        // Handle case where the element is not found
	        System.err.println("Checkout button not found: " + e.getMessage());
	        throw e; // Re-throw the exception to inform the caller that the element could not be found
	    } catch (WebDriverException e) {
	        // Handle other WebDriver related issues
	        System.err.println("WebDriver issue while clicking checkout button: " + e.getMessage());
	        throw e; // Re-throw the exception to indicate a WebDriver issue occurred
	    } catch (Exception e) {
	        // Handle any unexpected exceptions
	        System.err.println("An unexpected error occurred while clicking checkout: " + e.getMessage());
	        e.printStackTrace();
	        throw new RuntimeException("Unexpected error occurred during checkout button click", e);
	    }
	}

}
