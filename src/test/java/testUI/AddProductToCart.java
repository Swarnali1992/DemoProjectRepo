package testUI;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pageEvent.CartPageEvent;
import pageEvent.CheckoutOverviewPageEvent;
import pageEvent.CheckoutPageEvent;
import pageEvent.SwagLabsPageEvent;
import pageObject.CartPageObject;
import utils.ElementUtils;
import utils.ExcelUtils;
import utils.IdentifyWebElement;
import utils.WaitUtils;
import org.openqa.selenium.WebDriver;
public class AddProductToCart {
	IdentifyWebElement ele = new IdentifyWebElement();
	SwagLabsPageEvent swagLabsPage = new SwagLabsPageEvent();
	ElementUtils eleUtil = new ElementUtils();
	CartPageEvent cartPage = new CartPageEvent();
	CheckoutPageEvent checkoutPage = new CheckoutPageEvent();
	CheckoutOverviewPageEvent checkoutOverviewPage = new CheckoutOverviewPageEvent();
	String productInCartTitle;
	
	
	@Test(priority=0)
	public void verifyAddProductToCart() throws IOException, InterruptedException {
	 //swagLabsPage.verifyLoginSuccessful();
	 //System.out.println("Verified Login Successful");
	 swagLabsPage.fetchProductData();
	 productInCartTitle = swagLabsPage.searchProductTitle();
	 swagLabsPage.clickGoToCartPageIcon();
	 //swagLabsPage.clickOpenMenuIcon();
	 //eleUtil.selectSubMenu("About"); 
	}
	
	@Test(priority=1)
	public void verifyProductAddedToCart() throws InterruptedException {
		
		String productInCartPageTitle = cartPage.productInCart();
		if(productInCartPageTitle.equalsIgnoreCase(productInCartTitle)) {
			System.out.println("Same Product Added in Cart");
			cartPage.clickCheckOut();
		}else {
			System.out.println("Same Product Not Added in Cart");
		}	
			
	}
	
	
	@Test(priority=2)
	public void verifyCheckOutPageDetails() throws InterruptedException {
		String pageTitle = checkoutPage.getCheckoutPageTitle();
		System.out.println("Page Displayed : " +pageTitle);
		checkoutPage.fetchContactInfo();
		checkoutPage.enterContactDetails();
		checkoutPage.clickContinueBtn();
		Thread.sleep(5000);
	}
	
	@Test(priority=3)
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
