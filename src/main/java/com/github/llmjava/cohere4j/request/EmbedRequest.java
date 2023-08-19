package com.github.llmjava.cohere4j.request;

import java.util.ArrayList;
import java.util.List;

public class EmbedRequest {
    /**
     * An array of strings for the model to embed. Maximum number of texts per call is 96. We recommend reducing the length of each text to be under 512 tokens for optimal quality.
     */
    private String[] texts;
    /**
     * The identifier of the model. Smaller "light" models are faster, while larger models will perform better. Custom models can also be supplied with their full ID.
     *
     * Available models and corresponding embedding dimensions:
     *
     * embed-english-v2.0 (default) 4096
     * embed-english-light-v2.0 1024
     * embed-multilingual-v2.0 768
     */
    private String model;
    /**
     * One of NONE|START|END to specify how the API will handle inputs longer than the maximum token length.
     *
     * Passing START will discard the start of the input. END will discard the end of the input. In both cases, input is discarded until the remaining input is exactly the maximum input token length for the model.
     *
     * If NONE is selected, when the input exceeds the maximum input token length an error will be returned.
     *
     * Default: END
     */
    private String truncate;

    EmbedRequest(Builder builder) {
        this.texts = builder.texts.toArray(new String[builder.texts.size()]);
        this.model = builder.model;
        this.truncate = builder.truncate;
    }

    public static class Builder {

        private List<String> texts = new ArrayList<>();
        private String model;
        private String truncate;

        public Builder withText(String text) {
            this.texts.add(text);
            return this;
        }

        public Builder withModel(String model) {
            this.model = model;
            return this;
        }

        public Builder withTruncate(String truncate) {
            this.truncate = truncate;
            return this;
        }

        public EmbedRequest build() {
            return new EmbedRequest(this);
        }
    }
}
