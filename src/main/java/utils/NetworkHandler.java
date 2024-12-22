package utils;

import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v128.network.Network;
import org.testng.Assert;

public class NetworkHandler {
    private final DevTools devTools;

    public NetworkHandler(DevTools devTools) {
        this.devTools = devTools;
    }

    public void captureNetworkRequest() {
        devTools.addListener(Network.requestWillBeSent(), request -> {
        	
//            System.out.println("Request URL: " + request.getRequest().getUrl());
           // System.out.println("Request Method: " + request.getRequest().getMethod());
        });
    }

    public void captureNetworkResponse() {
        devTools.addListener(Network.responseReceived(), response -> {
//            System.out.println("Response URL: " + response.getResponse().getUrl());
//            System.out.println("Status Code: " + response.getResponse().getStatus());
        });
    }
    
    public void validateAPIResponse() {
    	 devTools.addListener(Network.responseReceived(), response -> {
    	 int statusCode = response.getResponse().getStatus();
         System.out.println("Status Code: " + response.getResponse().getStatus());
         Assert.assertEquals(statusCode,200, "API status code validation failed.");
         });
    }
}
