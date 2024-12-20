package testUI;

import java.io.IOException;
import java.util.List;
import org.testng.annotations.Test;
import pageEvent.SwagLabsPageEvent;
import utils.ExcelUtils;
import utils.IdentifyWebElement;
import utils.WaitUtils;

public class OpenMenuTest {
	IdentifyWebElement ele = new IdentifyWebElement();
	SwagLabsPageEvent swagLabsPage = new SwagLabsPageEvent();
	
  @Test
  public void openMenu() throws IOException {
	  swagLabsPage.verifyLoginSuccessful();
	  System.out.println("Verified Login Successful");
	  swagLabsPage.clickOpenMenuIcon();
	  System.out.println("First Test case ended 3");
	 // swagLabsPage.clickCloseMenuIcon();
	 // WaitUtils.waitForVisibility();
		
  }
}
