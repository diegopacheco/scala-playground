package com.app;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PrometheusCardinalityJavaTest {

    private final String prometheusUrl = "http://localhost:8081/prometheus";
    private final int maxCardinality = 99;

    @TestFactory
    public Collection<DynamicTest> testAllMetricsCardinality() throws Exception {
        String metricsData = fetchPrometheusMetrics();
        Map<String, Integer> metricCardinalities = parseMetricsCardinality(metricsData);

        return metricCardinalities.entrySet().stream()
            .map(entry -> {
                String metricName = entry.getKey();
                int cardinality = entry.getValue();
                return DynamicTest.dynamicTest(
                    String.format("Metric '%s' cardinality should be below %d (actual: %d)",
                        metricName, maxCardinality, cardinality),
                    () -> {
                        assertTrue(cardinality < maxCardinality,
                            String.format("Metric '%s' has cardinality of %d which exceeds the maximum of %d",
                                metricName, cardinality, maxCardinality));
                    }
                );
            })
            .collect(Collectors.toList());
    }

    private String fetchPrometheusMetrics() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(prometheusUrl))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode(),
            String.format("Failed to fetch metrics from %s", prometheusUrl));
        return response.body();
    }

    private Map<String, Integer> parseMetricsCardinality(String metricsData) {
        Pattern metricPattern = Pattern.compile("^([a-zA-Z_:][a-zA-Z0-9_:]*)(?:\\{.*\\})?\\s+.*$");

        Map<String, Map<String, Boolean>> metricLabels = new HashMap<>();

        for (String line : metricsData.split("\n")) {
            if (line.trim().isEmpty() || line.startsWith("#")) {
                continue;
            }

            Matcher matcher = metricPattern.matcher(line);
            if (matcher.matches()) {
                String metricName = matcher.group(1);
                int labelStart = line.indexOf("{");
                int labelEnd = line.indexOf("}");

                String labels = "";
                if (labelStart != -1 && labelEnd != -1) {
                    labels = line.substring(labelStart, labelEnd + 1);
                }

                metricLabels.putIfAbsent(metricName, new HashMap<>());
                metricLabels.get(metricName).put(labels, true);
            }
        }

        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<String, Map<String, Boolean>> entry : metricLabels.entrySet()) {
            result.put(entry.getKey(), entry.getValue().size());
        }

        return result;
    }
}
