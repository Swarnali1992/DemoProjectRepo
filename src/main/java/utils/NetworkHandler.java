package utils;

import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v128.network.Network;
import org.testng.Assert;

public class NetworkHandler {
    private final DevTools devTools;

    public NetworkHandler(DevTools devTools) {
        this.devTools = devTools;
    }
    /**
     * Listens for and captures outgoing network requests sent by the browser during the test.
     * 
     * This method listens to the `Network.requestWillBeSent` event in Chrome DevTools Protocol using the DevTools instance.
     * It can be used for capturing network requests, such as logging request URLs, methods, headers, or other data.
     * 
     * The network request data can be used for performance monitoring, debugging network interactions, or ensuring 
     * the correct network calls are made during testing.
     *
     * return None 
     * @throws NullPointerException If the {@code devTools} instance is {@code null}.
     */
    public void captureNetworkRequest() throws NullPointerException{
        devTools.addListener(Network.requestWillBeSent(), request -> {
        	
//            System.out.println("Request URL: " + request.getRequest().getUrl());
           // System.out.println("Request Method: " + request.getRequest().getMethod());
        });
    }

    

/**
 * Listens for and captures incoming network responses received by the browser during the test.
 * 
 * This method subscribes to the `Network.responseReceived` event from the Chrome DevTools Protocol using the `DevTools` instance.
 * It captures details of the responses such as the URL and status code of the network responses.
 * 
 * This can be useful for verifying the correctness of the responses during automated tests, such as checking
 * whether the right status code (200, 404, etc.) is returned for specific requests.
 * @return None
 * 
 * @throws NullPointerException If the {@code devTools} instance is {@code null}.
 */
    
    public void captureNetworkResponse() throws NullPointerException{
        devTools.addListener(Network.responseReceived(), response -> {
//            System.out.println("Response URL: " + response.getResponse().getUrl());
//            System.out.println("Status Code: " + response.getResponse().getStatus());
        });
    }
    /**
     * Validates the API response status code received during network communication.
     * 
     * This method listens to the `Network.responseReceived` event from the Chrome DevTools Protocol
     * to capture the network responses. It checks the status code of the response and asserts that it is `200`.
     * If the status code is not `200`, the assertion will fail, and an error message will be displayed.
     * @return None
     * 
     * @throws NullPointerException If the {@code devTools} instance is {@code null}.
     */
    
    
    public void validateAPIResponse() throws NullPointerException{
    	 devTools.addListener(Network.responseReceived(), response -> {
    	 int statusCode = response.getResponse().getStatus();
         System.out.println("Status Code: " + response.getResponse().getStatus());
         Assert.assertEquals(statusCode,200, "API status code validation failed.");
         });
    }
}
