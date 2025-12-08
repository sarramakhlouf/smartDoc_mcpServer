package com.mcp_server.smartdoc.tools;

import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Component
public class StatsToolMcp {

    @McpTool(name = "calculateStats", description = "Calcul des statistiques de base : moyenne, médiane, écart-type")
    public Map<String, Double> calculateStats(
            @McpArg(description = "Liste de nombres") List<Double> numbers) {

        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("La liste de nombres ne peut pas être vide.");
        }

        DescriptiveStatistics stats = new DescriptiveStatistics();
        numbers.forEach(stats::addValue);

        Map<String, Double> result = new HashMap<>();
        result.put("mean", stats.getMean());
        result.put("median", stats.getPercentile(50));
        result.put("stdDev", stats.getStandardDeviation());
        result.put("min", stats.getMin());
        result.put("max", stats.getMax());

        return result;
    }

    @McpTool(name = "linearRegressionPredict", description = "Prédiction simple avec régression linéaire")
    public double linearRegressionPredict(
            @McpArg(description = "Valeurs X") List<Double> xValues,
            @McpArg(description = "Valeurs Y") List<Double> yValues,
            @McpArg(description = "Valeur X à prédire") double xToPredict) {

        if (xValues.size() != yValues.size() || xValues.isEmpty()) {
            throw new IllegalArgumentException("Les listes X et Y doivent être de même taille et non vides.");
        }

        double sumX = xValues.stream().mapToDouble(Double::doubleValue).sum();
        double sumY = yValues.stream().mapToDouble(Double::doubleValue).sum();
        double sumXY = 0;
        double sumX2 = 0;
        int n = xValues.size();

        for (int i = 0; i < n; i++) {
            sumXY += xValues.get(i) * yValues.get(i);
            sumX2 += xValues.get(i) * xValues.get(i);
        }

        double slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        double intercept = (sumY - slope * sumX) / n;

        return slope * xToPredict + intercept;
    }
}
