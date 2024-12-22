package pageEvent;

import org.openqa.selenium.WebDriver;

import pageObject.CartPageObject;
import pageObject.SwagLabsPageObject;
import utils.IdentifyWebElement;
import utils.WaitUtils;
public class CartPageEvent {
	IdentifyWebElement ele = new IdentifyWebElement();
	
	
	public String productInCart() throws InterruptedException{
		String productCart = ele.getWebElement("XPATH", CartPageObject.productTitle).getText();
		System.out.println(productCart);
		return productCart;
	}
	
	public void clickCheckOut() {
		ele.getWebElement("ID", CartPageObject.btnCheckOut).click();
	}
	
	

}
