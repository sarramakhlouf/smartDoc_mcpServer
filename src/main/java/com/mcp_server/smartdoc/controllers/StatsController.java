package com.mcp_server.smartdoc.controllers;

import com.mcp_server.smartdoc.tools.StatsToolMcp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// ---------------- Controller pour exposer les outils MCP ----------------
@RestController
@RequestMapping("/tools")
public class StatsController {

    @Autowired
    private StatsToolMcp statsToolMcp;

    // ---------------- Endpoint pour calculer les statistiques ----------------
    @PostMapping("/calculateStats")
    public Map<String, Double> calculateStats(@RequestBody List<Double> numbers) {
        return statsToolMcp.calculateStats(numbers);
    }

    // ---------------- Endpoint pour prédiction par régression linéaire ----------------
    @PostMapping("/linearRegressionPredict")
    public double linearRegressionPredict(
            @RequestBody LinearRegressionRequest request) {
        return statsToolMcp.linearRegressionPredict(
                request.getxValues(),
                request.getyValues(),
                request.getxToPredict()
        );
    }

    // ---------------- Classe interne pour la requête de régression ----------------
    public static class LinearRegressionRequest {
        private List<Double> xValues;
        private List<Double> yValues;
        private double xToPredict;

        public List<Double> getxValues() {
            return xValues;
        }

        public void setxValues(List<Double> xValues) {
            this.xValues = xValues;
        }

        public List<Double> getyValues() {
            return yValues;
        }

        public void setyValues(List<Double> yValues) {
            this.yValues = yValues;
        }

        public double getxToPredict() {
            return xToPredict;
        }

        public void setxToPredict(double xToPredict) {
            this.xToPredict = xToPredict;
        }
    }
}

