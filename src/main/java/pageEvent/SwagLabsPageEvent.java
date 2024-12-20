package pageEvent;

import org.testng.Assert;
import pageObject.SwagLabsPageObject;
import utils.IdentifyWebElement;

public class SwagLabsPageEvent {
	IdentifyWebElement ele = new IdentifyWebElement();
	
	public void verifyLoginSuccessful() {
	Assert.assertTrue(ele.getWebElements("XPATH", SwagLabsPageObject.swagLabsHdr).size()>0, " Login not successful. Swag Labs Page not displayed. ");
	System.out.println("Yes");
	}
	
	public void clickOpenMenuIcon() {
		ele.getWebElement("ID", SwagLabsPageObject.openMenuIcon).click();
	}
	public void clickCloseMenuIcon() {
		ele.getWebElement("ID", SwagLabsPageObject.closeMenuIcon).click();
	}
	public void clickLogout() {
		ele.getWebElement("ID", SwagLabsPageObject.logOutSubMenu).click();
	}



}
