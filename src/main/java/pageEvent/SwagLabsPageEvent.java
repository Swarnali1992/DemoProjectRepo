package pageEvent;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import base.BaseTest;
import pageObject.LoginPageObject;
import pageObject.SwagLabsPageObject;
import utils.ExcelUtils;
import utils.IdentifyWebElement;
import utils.JavaScriptUtils;

public class SwagLabsPageEvent {
	IdentifyWebElement ele = new IdentifyWebElement();
	public static String sheetName = "SearchProduct";
	String searchProductData;
	
	public void verifyLoginSuccessful() {
	Assert.assertTrue(ele.getWebElements("XPATH", SwagLabsPageObject.swagLabsHdr).size()>0, " Login not successful. Swag Labs Page not displayed. ");
	System.out.println("Yes");
	}
	
	public void clickOpenMenuIcon() throws InterruptedException {
		ele.getWebElement("ID", SwagLabsPageObject.openMenuIcon).click();
		Thread.sleep(6000);
	}
	public void clickCloseMenuIcon() {
		ele.getWebElement("ID", SwagLabsPageObject.closeMenuIcon).click();
	}
	public void clickLogout() {
		ele.getWebElement("ID", SwagLabsPageObject.logOutSubMenu).click();
	}
	
	public List<WebElement> getSubMenus() {
	List<WebElement> subMenu = ele.getWebElements("XPATH", SwagLabsPageObject.subMenuOptions);
	return subMenu;
	}
	  public void clickAddCartProduct() throws InterruptedException{
		 ele.getWebElement("ID", SwagLabsPageObject.swagLabBagPack).click();
		 Thread.sleep(5000);
		  }
	  public void clickGoToCartPageIcon() throws InterruptedException {
		ele.getWebElement("CLASSNAME", SwagLabsPageObject.addToCartPageIcon).click();
		Thread.sleep(5000);
			  }

	  
	  public String fetchProductData() throws NumberFormatException {
		  ExcelUtils excelutil = new ExcelUtils(BaseTest.excelFilePath);
			try {
				searchProductData = excelutil.getCellData(sheetName,Integer.parseInt(BaseTest.rowNum), 0);
				System.out.println("The product retrieved from Excel : " +searchProductData);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return searchProductData;
		}
	  
	  public String searchProductTitle() throws InterruptedException{
		  String productTitle = null;
		  List<WebElement> prodList = ele.getWebElements("XPATH", SwagLabsPageObject.searchProductTitle);
		  int size = ele.getWebElements("XPATH", SwagLabsPageObject.searchProductTitle).size();
		  for(WebElement product : prodList) {
			  WebElement prodTitle = product.findElement(By.xpath("div[2]/div[1]/a/div"));
				productTitle = prodTitle.getText();
				if(productTitle.equalsIgnoreCase(searchProductData)) {
					System.out.println("The product searched in UI : " +productTitle);
					product.findElement(By.xpath("div[2]/div[2]/button")).click();
					System.out.println("Added To Cart");
					break;
				}
			}
		  Thread.sleep(5000);
		  return productTitle;
		 
	  }
}
