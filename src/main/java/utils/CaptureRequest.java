package utils;

import java.util.Optional;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v128.network.Network;
import org.openqa.selenium.edge.EdgeDriver;

public class CaptureRequest {
	public DevTools devTools;
	
	
	/**
	 * Captures HTTP requests made by the WebDriver while browsing with a specified browser.
	 * 
	 * This method enables network tracking for a given browser (Chrome, Firefox, or Edge). It retrieves the appropriate
	 * `DevTools` instance for the specified browser and starts capturing HTTP network requests made during the session.
	 * 
	 * It uses the Selenium DevTools API to listen for and capture network events such as requests being sent from the browser.
	 * This is useful for monitoring and debugging HTTP requests while performing automated tests.
	 * 
	 * @param driver The WebDriver instance controlling the browser. This should be a valid driver object (e.g., ChromeDriver, EdgeDriver).
	 * @param browser The name of the browser for which network requests need to be captured. 
	 *                It can be "CHROME", "FIREFOX", or "EDGE".
	 *                It can be one of the following:
	 *                - "CHROME" for Google Chrome.
	 *                - "FIREFOX" (Currently not supported, as it requires additional setup).
	 *                - "EDGE" for Microsoft Edge.
	 *                The browser name is used to determine the appropriate WebDriver and DevTools session to use.
	 * @throws WebDriverException If the WebDriver is not able to communicate with the browser or DevTools, or if there is an issue initializing the DevTools session.
	 * @return None
	 */
	
public void captureHttpRequest(WebDriver driver, String browser) throws WebDriverException {
	
	if(browser.equalsIgnoreCase("CHROME")){
		devTools = ((ChromeDriver)driver).getDevTools();
	}
	else if(browser.equalsIgnoreCase("FIREFOX")) {
		
	}
	else if(browser.equalsIgnoreCase("EDGE")) {
		devTools = ((EdgeDriver)driver).getDevTools();
	}
	NetworkHandler nh = new NetworkHandler(devTools);
	devTools.createSession();
	nh.captureNetworkRequest();
	// Enable Network Tracking
    devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
	
	devTools.addListener(Network.requestWillBeSent(), request -> {
//	System.out.println("Request URL: " + request.getRequest().getUrl());
//	System.out.println("Request Method: " + request.getRequest().getMethod());
	});
	
}
}
