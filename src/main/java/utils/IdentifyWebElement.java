package utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BaseTest;

public class IdentifyWebElement {
	
	/**
	 * Retrieves a single web element based on the provided identifier type and value.
	 * 
	 * This method allows you to find a web element on a web page using different locator strategies
	 * such as XPath, CSS, Name, ID, Tag Name, Link Text, Partial Link Text, and Class Name. 
	 * It supports dynamic identification of elements based on the provided type of locator.
	 * 
	 * @param identifierType The type of locator to use for identifying the element. Valid options are:
	 *                       "XPATH", "CSS", "NAME", "ID", "TAGNAME", "LINKTEXT", "PARTIALLINKTEXT", "CLASSNAME".
	 * @param identifierValue The value of the locator (e.g., the actual XPath expression or CSS selector).
	 * @return The matching WebElement, or {@code null} if no matching element is found or if an invalid locator type is provided.
	 * 
	 * @throws IllegalArgumentException if an invalid identifier type is provided (i.e., not one of the valid options).
	 */
	
	
	public WebElement getWebElement(String identifierType, String identifierValue) throws IllegalArgumentException
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
		case "CLASSNAME":
			return BaseTest.driver.findElement(By.className(identifierValue));
		default:
			return null;
	}	
	}

	/**
	 * Retrieves a list of web elements based on the provided identifier type and value.
	 * 
	 * This method allows you to find multiple web elements on a web page using different locator strategies
	 * such as XPath, CSS Selector, Name, ID, Tag Name, Link Text, or Partial Link Text. 
	 * It supports dynamic identification of elements based on the provided type of locator.
	 * 
	 * @param identifierType The type of locator to use for identifying the elements. Valid options are:
	 *                       "XPATH", "CSS", "NAME", "ID", "TAGNAME", "LINKTEXT", "PARTIALLINKTEXT".
	 * @param identifierValue The value of the locator (e.g., the actual XPath expression or CSS selector).
	 * @return A list of matching WebElements, or {@code null} if no matching elements are found or if an invalid locator type is provided.
	 * 
	 * @throws IllegalArgumentException if an invalid identifier type is provided (i.e., not one of the valid options).
	 */
	
	public List<WebElement> getWebElements(String identifierType, String identifierValue) throws IllegalArgumentException
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
