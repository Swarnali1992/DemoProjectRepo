package testUI;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.BaseTest;
import pageEvent.LoginPageEvent;
import utils.ExcelUtils;
import utils.IdentifyWebElement;

public class LoginPageTest extends BaseTest {
	IdentifyWebElement ele = new IdentifyWebElement();
	LoginPageEvent loginPage = new LoginPageEvent();
	
	
  @Test()
  public void loginTest() {
	// loginPage.verifyLoginPageIsDisplayed();
	loginPage.fetchLoginData();
	  loginPage.enterLoginInfo();
	  System.out.println("First Test case ended");
  }
 

	  
}
