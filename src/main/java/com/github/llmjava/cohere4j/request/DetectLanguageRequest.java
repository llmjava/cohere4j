package com.github.llmjava.cohere4j.request;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DetectLanguageRequest {
    /**
     * List of strings to run the detection on.
     */
    private List<String> texts;


    DetectLanguageRequest(Builder builder) {
        this.texts = builder.texts;
    }

    public static class Builder {

        private List<String> texts = new ArrayList<>();

        public Builder withText(String text) {
            this.texts.add(text);
            return this;
        }

        public Builder withTexts(Collection<String> texts) {
            this.texts.addAll(texts);
            return this;
        }

        public DetectLanguageRequest build() {
            return new DetectLanguageRequest(this);
        }
    }
}
