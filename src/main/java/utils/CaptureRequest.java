package utils;

import java.util.Optional;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v128.network.Network;
import org.openqa.selenium.edge.EdgeDriver;

public class CaptureRequest {
	public DevTools devTools;
	
public void captureHttpRequest(WebDriver driver, String browser) {
	
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
