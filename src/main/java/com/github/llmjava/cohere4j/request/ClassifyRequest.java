package com.github.llmjava.cohere4j.request;

import java.util.ArrayList;
import java.util.List;

public class ClassifyRequest {
    /**
     * Represents a list of queries to be classified, each entry must not be empty. The maximum is 96 inputs.
     */
    private List<String> inputs;
    /**
     * An array of examples to provide context to the model. Each example is a text string and its associated label/class. Each unique label requires at least 2 examples associated with it; the maximum number of examples is 2500, and each example has a maximum length of 512 tokens. The values should be structured as {text: "...",label: "..."}.
     *
     * Note: Custom Models trained on classification examples don't require the examples parameter to be passed in explicitly.
     */
    private List<Example> examples;
    /**
     * The identifier of the model. Currently available models are embed-multilingual-v2.0, embed-english-light-v2.0, and embed-english-v2.0 (default). Smaller "light" models are faster, while larger models will perform better. Custom models can also be supplied with their full ID.
     */
    private String model;
    /**
     * The ID of a custom playground preset. You can create presets in the playground. If you use a preset, all other parameters become optional, and any included parameters will override the preset's parameters.
     */
    private String preset;
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

    ClassifyRequest(Builder builder) {
        this.inputs = builder.inputs;
        this.examples = builder.examples;
        this.model = builder.model;
        this.preset = builder.preset;
        this.truncate = builder.truncate;
    }

    public static class Example {
        private String text;
        private String label;

        public Example(String text, String label) {
            this.text = text;
            this.label = label;
        }
    }

    public static class Builder {
        private List<String> inputs = new ArrayList<>();
        private List<Example> examples = new ArrayList<>();
        private String model;
        private String preset;
        private String truncate;

        public Builder withInput(String text) {
            this.inputs.add(text);
            return this;
        }

        public Builder withExample(String text, String label) {
            this.examples.add(new Example(text, label));
            return this;
        }

        public Builder withModel(String model) {
            this.model = model;
            return this;
        }

        public Builder withPreset(String preset) {
            this.preset = preset;
            return this;
        }

        public Builder withTruncate(String truncate) {
            this.truncate = truncate;
            return this;
        }

        public ClassifyRequest build() {
            return new ClassifyRequest(this);
        }
    }
}
