package testAPI;

import org.testng.annotations.Test;

import utils.CaptureRequest;
import utils.NetworkHandler;

public class APITest extends CaptureRequest{
NetworkHandler networkhandler = new NetworkHandler(devTools);
  @Test
  public void testAPI() {
	  networkhandler.captureNetworkRequest();
	  networkhandler.captureNetworkResponse();
}
  
}
