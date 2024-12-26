package utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import base.BaseTest;

public class IdentifyWebElement {
	
	   /**
     * Finds and returns a single WebElement based on the specified identifier type and value.
     * The method supports various locator strategies such as XPath, CSS, Name, ID, TagName, LinkText, PartialLinkText, and ClassName.
     *
     * @param identifierType The type of the identifier used to locate the WebElement.
     *                       Possible values are "XPATH", "CSS", "NAME", "ID", "TAGNAME", "LINKTEXT", "PARTIALLINKTEXT", "CLASSNAME".
     * @param identifierValue The value of the identifier to locate the WebElement.
     * 
     * @return The WebElement found using the specified locator strategy. 
     *         Returns null if no element is found or if an invalid identifier type is provided.
     * @throws IllegalArgumentException if the identifier type is invalid or if identifierType or identifierValue is null.
     */
    public WebElement getWebElement(String identifierType, String identifierValue) throws IllegalArgumentException {
        if (identifierType == null || identifierValue == null) {
            throw new IllegalArgumentException("Identifier type or value cannot be null");
        }

        WebElement element = null;

        try {
            switch (identifierType.toUpperCase()) {
                case "XPATH":
                    element = BaseTest.driver.findElement(By.xpath(identifierValue));
                    break;
                case "CSS":
                    element = BaseTest.driver.findElement(By.cssSelector(identifierValue));
                    break;
                case "NAME":
                    element = BaseTest.driver.findElement(By.name(identifierValue));
                    break;
                case "ID":
                    element = BaseTest.driver.findElement(By.id(identifierValue));
                    break;
                case "TAGNAME":
                    element = BaseTest.driver.findElement(By.tagName(identifierValue));
                    break;
                case "LINKTEXT":
                    element = BaseTest.driver.findElement(By.linkText(identifierValue));
                    break;
                case "PARTIALLINKTEXT":
                    element = BaseTest.driver.findElement(By.partialLinkText(identifierValue));
                    break;
                case "CLASSNAME":
                    element = BaseTest.driver.findElement(By.className(identifierValue));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid identifier type: " + identifierType);
            }
        } catch (NoSuchElementException e) {
            System.err.println("Error: Element not found for " + identifierType + " with value: " + identifierValue);
            // Optionally, log the stack trace or rethrow the exception
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: Invalid identifier type: " + identifierType);
            // Optionally, log the stack trace or rethrow the exception
            e.printStackTrace();
            throw e;  // Rethrow the IllegalArgumentException
        } catch (Exception e) {
            System.err.println("Unexpected error occurred while finding the element: " + e.getMessage());
            e.printStackTrace();
        }

        // Return the found element or null if not found or an error occurred
        return element;
    }

	

	 /**
     * Finds and returns a list of WebElements based on the specified identifier type and value.
     * The method supports various locator strategies such as XPath, CSS, Name, ID, TagName, LinkText, and PartialLinkText.
     * 
     * @param identifierType The type of the identifier used to locate the WebElements. 
     *                       Possible values are "XPATH", "CSS", "NAME", "ID", "TAGNAME", "LINKTEXT", "PARTIALLINKTEXT".
     * @param identifierValue The value of the identifier to locate the WebElements.
     * 
     * @return A list of WebElements found using the specified locator strategy. Returns an empty list if no elements are found or if an error occurs.
     *         If the identifier type is invalid, throws an IllegalArgumentException.
     * @throws IllegalArgumentException if the identifier type is invalid or if identifierType or identifierValue is null.
     * @throws Exception if any unexpected error occurs while attempting to find the WebElements.
     */
    // Assuming BaseTest.driver is the WebDriver instance
    public List<WebElement> getWebElements(String identifierType, String identifierValue) throws IllegalArgumentException {
        if (identifierType == null || identifierValue == null) {
            throw new IllegalArgumentException("Identifier type or value cannot be null");
        }

        List<WebElement> elements = new ArrayList<>();
        try {
            switch (identifierType.toUpperCase()) {
                case "XPATH":
                    elements = BaseTest.driver.findElements(By.xpath(identifierValue));
                    break;
                case "CSS":
                    elements = BaseTest.driver.findElements(By.cssSelector(identifierValue));
                    break;
                case "NAME":
                    elements = BaseTest.driver.findElements(By.name(identifierValue));
                    break;
                case "ID":
                    elements = BaseTest.driver.findElements(By.id(identifierValue));
                    break;
                case "TAGNAME":
                    elements = BaseTest.driver.findElements(By.tagName(identifierValue));
                    break;
                case "LINKTEXT":
                    elements = BaseTest.driver.findElements(By.linkText(identifierValue));
                    break;
                case "PARTIALLINKTEXT":
                    elements = BaseTest.driver.findElements(By.partialLinkText(identifierValue));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid identifier type: " + identifierType);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;  // Rethrow the exception after logging
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            // Return empty list if any error occurs
            return elements;
        }

        // Return the found elements (could be empty if no elements match)
        return elements;
    }
}


