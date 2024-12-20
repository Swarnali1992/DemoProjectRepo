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

    public void captureMetrics() {
        devTools.send(Performance.enable(java.util.Optional.empty()));
        List<Metric> metrics = devTools.send(Performance.getMetrics());
        metrics.forEach(metric -> System.out.println(metric.getName() + ": " + metric.getValue()));
    }
}
