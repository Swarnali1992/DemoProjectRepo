package pageEvent;

import pageObject.CartPageObject;
import pageObject.CheckoutOverviewPageObject;
import pageObject.CheckoutPageObject;
import utils.IdentifyWebElement;

public class CheckoutOverviewPageEvent {
	IdentifyWebElement ele = new IdentifyWebElement();
	 public void clickFinishBtn() throws InterruptedException {
		 ele.getWebElement("ID", CheckoutOverviewPageObject.finishBtn).click();
		 System.out.println("Clicked on Finish Button.");
	}
	 
	 public String getPageTitle() throws InterruptedException {
		 String pageTitle = ele.getWebElement("XPATH", CheckoutOverviewPageObject.checkoutOverviewPageTitle).getText();
		 return pageTitle;
	}
	 
	 public String productInCart() throws InterruptedException{
			String productCart = ele.getWebElement("XPATH",CheckoutOverviewPageObject.productTitle).getText();
			System.out.println(productCart);
			return productCart;
		}
		
}
