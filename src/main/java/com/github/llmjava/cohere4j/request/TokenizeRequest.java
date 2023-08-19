package com.github.llmjava.cohere4j.request;

public class TokenizeRequest {
    /**
     * The string to be tokenized, the minimum text length is 1 character, and the maximum text length is 65536 characters.
     */
    private String text;
    /**
     * An optional parameter to provide the model name. This will ensure that the tokenization uses the tokenizer used by that model.
     */
    private String model;


    TokenizeRequest(Builder builder) {
        this.text = builder.text;
        this.model = builder.model;
    }

    public static class Builder {

        private String text;
        private String model;

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Builder withModel(String model) {
            this.model = model;
            return this;
        }

        public TokenizeRequest build() {
            return new TokenizeRequest(this);
        }
    }
}
