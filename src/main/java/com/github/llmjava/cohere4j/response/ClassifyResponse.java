package com.github.llmjava.cohere4j.response;

import java.util.Map;

public class ClassifyResponse {
    private String id;

    private Classification[] classifications;

    public static class Classification {
        private String id;
        private String input;
        private String prediction;
        private Float confidence;
        private Map<String, ClassificationDetail> labels;

        public String getInput() {
            return input;
        }

        public String getPrediction() {
            return prediction;
        }

        public Float getConfidence() {
            return confidence;
        }

        public Float getConfidence(String label) {
            return labels.get(label).confidence;
        }
    }

    public static class ClassificationDetail {
        private Float confidence;
    }

    private Meta meta;

    public Classification[] getClassifications() {
        return classifications;
    }
    public Classification getClassification(int index) {
        return classifications[index];
    }
}
