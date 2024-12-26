package utils;

import java.util.List;

import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v128.performance.Performance;
import org.openqa.selenium.devtools.v128.performance.model.Metric;

public class PerformanceTracker {
    private final DevTools devTools;

    public PerformanceTracker(DevTools devTools) {
        this.devTools = devTools;
        System.out.println("Inside Performance Tracker");
    }

    
    /**
     * Captures performance metrics from the browser using DevTools.
     * This method enables the performance domain and retrieves various metrics.
     * 
     * The method handles potential exceptions that may occur during the process.
     * @return None
     */
    public void captureMetrics() {
        try {
            // Log that we're inside the method
            System.out.println("Inside captureMetrics()");

            // Enable the Performance domain to capture performance metrics
            devTools.send(Performance.enable(java.util.Optional.empty()));

            // Retrieve the list of performance metrics
            List<Metric> metrics = devTools.send(Performance.getMetrics());

            // Output each metric's name and value
            metrics.forEach(metric -> System.out.println(metric.getName() + ": " + metric.getValue()));

        } catch (Exception e) {
            // Log any exceptions that may occur during the capture of metrics
            System.err.println("Error while capturing performance metrics: " + e.getMessage());
            e.printStackTrace(); // This will print the full stack trace of the error
        }
    }
}
