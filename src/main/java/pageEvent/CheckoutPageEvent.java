package pageEvent;

import base.BaseTest;
import pageObject.CheckoutPageObject;
import pageObject.LoginPageObject;
import utils.ExcelUtils;
import utils.IdentifyWebElement;

public class CheckoutPageEvent {
	IdentifyWebElement ele = new IdentifyWebElement();
	public static String sheetName = "CheckOut";
	String firstName;
	String lastName;
	String postalCode;
	
	 public void fetchContactInfo() throws NumberFormatException {
		  ExcelUtils excelutil = new ExcelUtils(BaseTest.excelFilePath);
			try {
				firstName = excelutil.getCellData(sheetName,Integer.parseInt(BaseTest.rowNum), 0);
				lastName = excelutil.getCellData(sheetName,Integer.parseInt(BaseTest.rowNum), 1);
				postalCode = excelutil.getCellData(sheetName,Integer.parseInt(BaseTest.rowNum), 2);
				System.out.println("The First Name retrieved from Excel : " + firstName);
				System.out.println("The Last Name retrieved from Excel : " + lastName);
				System.out.println("The Postal Code retrieved from Excel : " + postalCode);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 
	 public String getCheckoutPageTitle() throws InterruptedException{
		 return ele.getWebElement("XPATH", CheckoutPageObject.checkoutPageTitle).getText();
	 }
	 
	 public void enterContactDetails() throws InterruptedException{
		 ele.getWebElement("ID", CheckoutPageObject.firstName).click();
		 ele.getWebElement("ID", CheckoutPageObject.firstName).clear();
		 ele.getWebElement("ID", CheckoutPageObject.firstName).sendKeys(firstName);
		 ele.getWebElement("ID", CheckoutPageObject.lastName).click();
		 ele.getWebElement("ID", CheckoutPageObject.lastName).clear();
		 ele.getWebElement("ID", CheckoutPageObject.lastName).sendKeys(lastName);
		 ele.getWebElement("ID", CheckoutPageObject.postalCode).click();
		 ele.getWebElement("ID", CheckoutPageObject.postalCode).clear();
		 ele.getWebElement("ID", CheckoutPageObject.postalCode).sendKeys(postalCode);
		 Thread.sleep(5000);
	 }
	 public void clickContinueBtn() throws InterruptedException {
		 ele.getWebElement("ID", CheckoutPageObject.continueBtn).click();
		 System.out.println("Clicked on Continue Button.");
	}
}
