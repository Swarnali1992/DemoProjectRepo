package utils;

import java.util.List;

import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v128.performance.Performance;
import org.openqa.selenium.devtools.v128.performance.model.Metric;

public class PerformanceTracker {
    private final DevTools devTools;

    public PerformanceTracker(DevTools devTools) {
        this.devTools = devTools;
    }
    /**
     * Captures and prints the performance metrics of the browser using Chrome DevTools Protocol.
     * 
     * This method enables the performance metrics collection through the DevTools API and retrieves the metrics.
     * It then prints the name and value of each metric to the standard output (console).
     * 
     * The method interacts with the Chrome DevTools Protocol (CDP) via the {@link DevTools} object to enable and retrieve performance data.
     * @return None
     */
    public void captureMetrics() {
        devTools.send(Performance.enable(java.util.Optional.empty()));
        List<Metric> metrics = devTools.send(Performance.getMetrics());
        metrics.forEach(metric -> System.out.println(metric.getName() + ": " + metric.getValue()));
    }
}
