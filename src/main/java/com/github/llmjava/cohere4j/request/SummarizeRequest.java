package com.github.llmjava.cohere4j.request;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This request generates a summary in English for a given text.
 */
public class SummarizeRequest {
    /**
     * The text to generate a summary for. Can be up to 100,000 characters long. Currently the only supported language is English.
     */
    String text;

    /**
     * One of short, medium, long, or auto defaults to auto. Indicates the approximate length of the summary. If auto is selected, the best option will be picked based on the input text.
     *
     * Default: medium
     */
    String length;

    /**
     * One of paragraph, bullets, or auto, defaults to auto. Indicates the style in which the summary will be delivered - in a free form paragraph or in bullet points. If auto is selected, the best option will be picked based on the input text.
     *
     * Default: paragraph
     */
    String format;

    /**
     * The identifier of the model to generate the summary with. Currently available models are command (default), command-nightly (experimental), command-light, and command-light-nightly (experimental). Smaller, "light" models are faster, while larger models will perform better.
     *
     * Default: command
     */
    String model;

    /**
     * One of low, medium, high, or auto, defaults to auto. Controls how close to the original text the summary is. high extractiveness summaries will lean towards reusing sentences verbatim, while low extractiveness summaries will tend to paraphrase more. If auto is selected, the best option will be picked based on the input text.
     *
     * Default: low
     */
    String extractiveness;

    /**
     * Ranges from 0 to 5. Controls the randomness of the output. Lower values tend to generate more “predictable” output, while higher values tend to generate more “creative” output. The sweet spot is typically between 0 and 1.
     */
    Float temperature;

    /**
     * A free-form instruction for modifying how the summaries get generated. Should complete the sentence "Generate a summary _". Eg. "focusing on the next steps" or "written by Yoda"
     */
    String additional_command;
    SummarizeRequest(Builder builder) {
        this.text = builder.text;
        this.length = builder.length;
        this.format = builder.format;
        this.model = builder.model;
        this.extractiveness = builder.extractiveness;
        this.temperature = builder.temperature;
        this.additional_command = builder.additional_command;
    }

    public static class Builder {

        private String text;
        private String length;
        private String format;
        private String model;
        private String extractiveness;
        private Float temperature;
        private String additional_command;

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Builder withLength(String length) {
            this.length = length;
            return this;
        }

        public Builder withFormat(String format) {
            this.format = format;
            return this;
        }

        public Builder withModel(String model) {
            this.model = model;
            return this;
        }

        public Builder withExtractiveness(String extractiveness) {
            this.extractiveness = extractiveness;
            return this;
        }

        public Builder withTemperature(Float temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder withAdditionalCommand(String additional_command) {
            this.additional_command = additional_command;
            return this;
        }

        public SummarizeRequest build() {
            return new SummarizeRequest(this);
        }
    }
}
