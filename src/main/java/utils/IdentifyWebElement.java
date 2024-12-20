package utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BaseTest;

public class IdentifyWebElement {
	public WebElement getWebElement(String identifierType, String identifierValue)
	{
		switch(identifierType.toUpperCase()) {
		case "XPATH":
			return BaseTest.driver.findElement(By.xpath(identifierValue));
		case "CSS":
			return BaseTest.driver.findElement(By.cssSelector(identifierValue));
		case "NAME":
			return BaseTest.driver.findElement(By.name(identifierValue));
		case "ID":
			return BaseTest.driver.findElement(By.id(identifierValue));
		case "TAGNAME":
			return BaseTest.driver.findElement(By.tagName(identifierValue));
		case "LINKTEXT":
			return BaseTest.driver.findElement(By.linkText(identifierValue));
		case "PARTIALLINKTEXT":
			return BaseTest.driver.findElement(By.partialLinkText(identifierValue));
		default:
			return null;
	}	
	}
	public List<WebElement> getWebElements(String identifierType, String identifierValue)
	{
		switch(identifierType.toUpperCase()) {
		case "XPATH":
			return BaseTest.driver.findElements(By.xpath(identifierValue));
		case "CSS":
			return BaseTest.driver.findElements(By.cssSelector(identifierValue));
		case "NAME":
			return BaseTest.driver.findElements(By.name(identifierValue));
		case "ID":
			return BaseTest.driver.findElements(By.id(identifierValue));
		case "TAGNAME":
			return BaseTest.driver.findElements(By.tagName(identifierValue));
		case "LINKTEXT":
			return BaseTest.driver.findElements(By.linkText(identifierValue));
		case "PARTIALLINKTEXT":
			return BaseTest.driver.findElements(By.partialLinkText(identifierValue));
		default:
			return null;
	}	
	}
	

}
