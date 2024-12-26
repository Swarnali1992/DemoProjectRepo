package testAPI;

import java.io.IOException;

import org.testng.annotations.Test;

import utils.CaptureRequest;
import utils.NetworkHandler;

public class NetworkAPITest extends CaptureRequest{
NetworkHandler networkhandler = new NetworkHandler(devTools);

/**
 * Test method to capture network requests and responses during an API test.
 * This method attempts to capture both network requests and responses. 
 * If any exception occurs during the process, it is caught and logged.
 */
@Test
public void testAPI() throws IOException{
    try {
        // Capture network request
        networkhandler.captureNetworkRequest();
        System.out.println("Network request captured successfully.");
        
        // Capture network response
        networkhandler.captureNetworkResponse();
        System.out.println("Network response captured successfully.");
        
    } catch (Exception e) {
        // Handle any other unexpected exceptions
        System.err.println("Unexpected error occurred during API test: " + e.getMessage());
        e.printStackTrace();
        throw new RuntimeException("Unexpected error during API test", e);
    }
}
}
