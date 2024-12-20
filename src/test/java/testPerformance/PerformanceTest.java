package testPerformance;

import org.testng.annotations.Test;
import utils.CaptureRequest;
import utils.PerformanceTracker;

public class PerformanceTest extends CaptureRequest {
  @Test
  public void testPerformanceMetrics() {
	  PerformanceTracker performanceTracker = new PerformanceTracker(devTools);
      performanceTracker.captureMetrics();

  }
}
