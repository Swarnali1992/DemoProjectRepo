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
	 * Retrieves the title of the product displayed in the cart.
	 * This method waits for the visibility of the product element in the cart 
	 * and then extracts its text. The product title is returned as a string.
	 * 
	 * @param driver The WebDriver instance used to interact with the browser.
	 * @return A string representing the product title displayed in the cart.
	 * @throws NoSuchElementException if the product element cannot be found on the page.
	 * @throws WebDriverException if an error occurs while interacting with the WebDriver.
	 * 
	 */
	public String productInCart(WebDriver driver) throws NoSuchElementException, WebDriverException{
		WaitUtils.waitForVisibility(driver, ele.getWebElement("XPATH", CartPageObject.productTitle), 5000);
		String productCart = ele.getWebElement("XPATH", CartPageObject.productTitle).getText();
		System.out.println(productCart);
		return productCart;
	}
	
	/**
	 * Clicks the "Checkout" button on the Cart page.
	 * 
	 * This method retrieves the "Checkout" button element by its ID (specified in the 
	 * CartPageObject class) and performs a click action on it using the {@link WebElement}.
	 * 
	 * The button is expected to navigate the user to the checkout page where they can review 
	 * their order and enter payment details.
	 * 
	 * @throws NoSuchElementException If the "Checkout" button is not found on the Cart page.
	 * @throws WebDriverException If the WebDriver is unable to perform the click action on the button.
	 * @return None
	 */
	
	
	public void clickCheckOut() throws NoSuchElementException, WebDriverException {
		ele.getWebElement("ID", CartPageObject.btnCheckOut).click();
	}
	
	

}
