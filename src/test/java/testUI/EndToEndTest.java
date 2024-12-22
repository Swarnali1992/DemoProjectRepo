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
	
	//IdentifyWebElement ele = new IdentifyWebElement();
	SwagLabsPageEvent swagLabsPage = new SwagLabsPageEvent();
	ElementUtils eleUtil = new ElementUtils();
	CartPageEvent cartPage = new CartPageEvent();
	CheckoutPageEvent checkoutPage = new CheckoutPageEvent();
	CheckoutOverviewPageEvent checkoutOverviewPage = new CheckoutOverviewPageEvent();
	String productInCartTitle;
	
	
	
  @Test(priority=0)
  public void verifyLogin() throws InterruptedException, IOException {
	// loginPage.verifyLoginPageIsDisplayed();
	  loginPage.fetchLoginData();
	  try {
		loginPage.enterLoginInfo();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  System.out.println("First Test case ended");
  }
  
  @Test(priority=1)
	public void verifyAddProductToCart() throws IOException, InterruptedException {
	 //swagLabsPage.verifyLoginSuccessful();
	 //System.out.println("Verified Login Successful");
	 swagLabsPage.fetchProductData();
	 productInCartTitle = swagLabsPage.searchProductTitle();
	 swagLabsPage.clickGoToCartPageIcon();
	 //swagLabsPage.clickOpenMenuIcon();
	 //eleUtil.selectSubMenu("About"); 
	}
	
	@Test(priority=2)
	public void verifyProductAddedToCart() throws InterruptedException {
		
		String productInCartPageTitle = cartPage.productInCart();
		if(productInCartPageTitle.equalsIgnoreCase(productInCartTitle)) {
			System.out.println("Same Product Added in Cart");
			cartPage.clickCheckOut();
		}else {
			System.out.println("Same Product Not Added in Cart");
		}	
			
	}
	
	
	@Test(priority=3)
	public void verifyCheckOutPageDetails() throws InterruptedException {
		String pageTitle = checkoutPage.getCheckoutPageTitle();
		System.out.println("Page Displayed : " +pageTitle);
		checkoutPage.fetchContactInfo();
		checkoutPage.enterContactDetails();
		checkoutPage.clickContinueBtn();
		Thread.sleep(5000);
	}
	
	@Test(priority=4)
	public void verifyCheckOutOverviewPageDetails() throws InterruptedException {
		String expectedPageTitle = "Checkout: Overview";
		String message = "Checkout Overview Page is not getting displayed.";
		String actualPageTitle = checkoutOverviewPage.getPageTitle();
		System.out.println("Page Displayed : " +actualPageTitle);
		String actualProductInCart = checkoutOverviewPage.productInCart();
		//System.out.println(actualPageTitle);
		if(actualProductInCart.equalsIgnoreCase(productInCartTitle)) {
			System.out.println("Same Product displayed in Checkout Overview page.");
			}else {
			System.out.println("Same Product not displayed in Checkout Overview page.");
		}
		Assert.assertEquals(actualPageTitle, expectedPageTitle, message);
		checkoutOverviewPage.clickFinishBtn();
		
	}
 
}
