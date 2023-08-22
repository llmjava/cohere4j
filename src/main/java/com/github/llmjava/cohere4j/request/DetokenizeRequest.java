package com.github.llmjava.cohere4j.request;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DetokenizeRequest {
    /**
     * The list of tokens to be detokenized.
     */
    private List<Integer> tokens;
    /**
     * An optional parameter to provide the model name. This will ensure that the detokenization is done by the tokenizer used by that model.
     */
    private String model;


    DetokenizeRequest(Builder builder) {
        this.tokens = builder.tokens;
        this.model = builder.model;
    }

    public static class Builder {

        private List<Integer> tokens = new ArrayList<>();
        private String model;

        public Builder withToken(Integer token) {
            this.tokens.add(token);
            return this;
        }

        public Builder withTokens(Collection<Integer> tokens) {
            this.tokens.addAll(tokens);
            return this;
        }

        public Builder withModel(String model) {
            this.model = model;
            return this;
        }

        public DetokenizeRequest build() {
            return new DetokenizeRequest(this);
        }
    }
}
