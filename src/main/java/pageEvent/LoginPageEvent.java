package pageEvent;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import utils.ConfigReaderUtils;
import utils.ExcelUtils;
import utils.IdentifyWebElement;

public class LoginPageEvent {
	//public static String rowNum;
	IdentifyWebElement ele = new IdentifyWebElement();
	public static String sheetName = "LoginData";
	String username;
	String password;
	
	public void fetchLoginData() {
//		rowNum = ConfigReaderUtils.getProperty("excelrownum");
//		System.out.println("Test Data should be fetched from row number : " + rowNum);
		ExcelUtils excelutil = new ExcelUtils(BaseTest.excelFilePath);
		username = excelutil.getCellData(sheetName,Integer.parseInt(BaseTest.rowNum), 0);
		password = excelutil.getCellData(sheetName,Integer.parseInt(BaseTest.rowNum), 1);
		System.out.println("Username : " + username);
		System.out.println("Password : " + password);
	}
	
	  public void enterLoginInfo(){
		//  int rowCount = excelutil.getRowCount(sheetName);
//		  for(int rowNum = 1; rowNum <= rowCount; rowNum++)
//		  {
//			  String password = excelutil.getCellDataByColName(sheetName, rowNum, "Password");
//			  System.out.println("Password: " + password);
//		  }
	  ele.getWebElement("ID", LoginPageObject.username).click();
	  ele.getWebElement("ID", LoginPageObject.username).sendKeys(username); 
	  ele.getWebElement("ID", LoginPageObject.password).click();
	  ele.getWebElement("ID", LoginPageObject.password).sendKeys("password");
	  ele.getWebElement("ID",LoginPageObject.loginBtn).click(); 
	  }

	
	
		
	
	public void verifyLoginPageIsDisplayed()
	{
		Assert.assertTrue(ele.getWebElements("ID", LoginPageObject.loginBtn).size()>0, " Login Page not displayed. ");
	}
	
	public void loginBtn() {
		ele.getWebElement("ID", LoginPageObject.loginBtn).click();
	}
	
	
	
	}

