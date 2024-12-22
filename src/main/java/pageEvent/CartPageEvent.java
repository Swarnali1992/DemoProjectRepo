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
	 * Retrieves the product title displayed in the Cart page.
	 * 
	 * This method locates the product title in the Cart page using its XPath (specified in the 
	 * {@link CartPageObject} class), extracts the text of the product, and returns it. The text 
	 * returned represents the title of the product that has been added to the shopping cart.
	 * 
	 * The method also logs the product title to the console for debugging purposes.
	 * 
	 * @return The title of the product displayed in the Cart.
	 * @throws NoSuchElementException If the product title element is not found on the Cart page.
	 * @throws WebDriverException If the WebDriver is unable to interact with the product title element.
	 * @return None
	 * 
	 */
	public String productInCart() throws NoSuchElementException, WebDriverException{
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
