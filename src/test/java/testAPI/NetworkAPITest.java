package testAPI;

import org.testng.annotations.Test;

import utils.CaptureRequest;
import utils.NetworkHandler;

public class NetworkAPITest extends CaptureRequest{
NetworkHandler networkhandler = new NetworkHandler(devTools);

/**
 * Test method that captures network requests and responses for API testing or debugging.
 * 
 * This method performs the following actions:
 * 
 *   Captures the network request by calling {@link NetworkHandler#captureNetworkRequest()}.
 *   Captures the network response by calling {@link NetworkHandler#captureNetworkResponse()}.
 * 
 * 
 * The captured network request and response can be analyzed for validating API behavior, 
 * debugging issues, or logging details for further inspection.
 *
 */
  @Test
  public void testAPI() {
	  networkhandler.captureNetworkRequest();
	  networkhandler.captureNetworkResponse();
}
  
}
