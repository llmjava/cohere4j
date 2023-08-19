package com.github.llmjava.cohere4j.request;

import java.util.List;
import java.util.Map;

public class GenerateRequest {

    /**
     * The input text that serves as the starting point for generating the response.
     * Note: The prompt will be pre-processed and modified before reaching the model.
     */
    private String prompt;

    /**
     * The identifier of the model to generate with. Currently available models are command (default), command-nightly (experimental), command-light, and command-light-nightly (experimental).
     *
     * Smaller, "light" models are faster, while larger models will perform better. Custom models can also be supplied with their full ID.
     */
    private String model;

    /**
     * When true, the response will be a JSON stream of events. Streaming is beneficial for user interfaces that render the contents of the response piece by piece, as it gets generated.
     *
     * The final event will contain the complete response, and will contain an is_finished field set to true. The event will also contain a finish_reason, which can be one of the following:
     *
     *     COMPLETE - the model sent back a finished reply
     *     MAX_TOKENS - the reply was cut off because the model reached the maximum number of tokens for its context length
     *     ERROR - something went wrong when generating the reply
     *     ERROR_TOXIC - the model generated a reply that was deemed toxic
     */
    private Boolean stream;

    /**
     * max_tokens
     * integer
     * The maximum number of tokens the model will generate as part of the response. Note: Setting a low value may result in incomplete generations.
     * Defaults to 20. See BPE Tokens for more details.
     *
     * Can only be set to 0 if return_likelihoods is set to ALL to get the likelihood of the prompt.
     */
    private Integer max_tokens;

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

    /**
     * A non-negative float that tunes the degree of randomness in generation. Lower temperatures mean less random generations. See Temperature for more details.
     * Defaults to 0.75, min value of 0.0, max value of 5.0.
     */
    private Double temperature;

    /**
     * Identifier of a custom preset. A preset is a combination of parameters, such as prompt, temperature etc. You can create presets in the playground.
     * When a preset is specified, the prompt parameter becomes optional, and any included parameters will override the preset's parameters.
     */
    private String preset;

    /**
     * The generated text will be cut at the beginning of the earliest occurence of an end sequence. The sequence will be excluded from the text.
     */
    private List<String> end_sequences;

    /**
     * The generated text will be cut at the end of the earliest occurence of a stop sequence. The sequence will be included the text.
     */
    private List<String> stop_sequences;

    /**
     * Ensures only the top k most likely tokens are considered for generation at each step.
     * Defaults to 0, min value of 0, max value of 500.
     */
    private Integer k;

    /**
     * Ensures that only the most likely tokens, with total probability mass of p, are considered for generation at each step. If both k and p are enabled, p acts after k.
     * Defaults to 0. min value of 0.01, max value of 0.99.
     */
    private Double p;

    /**
     * Used to reduce repetitiveness of generated tokens. The higher the value, the stronger a penalty is applied to previously present tokens, proportional to how many times they have already appeared in the prompt or prior generation.'
     */
    private Double frequency_penalty;

    /**
     * Defaults to 0.0, min value of 0.0, max value of 1.0. Can be used to reduce repetitiveness of generated tokens. Similar to frequency_penalty, except that this penalty is applied equally to all tokens that have already appeared, regardless of their exact frequencies.
     */
    private Double presence_penalty;

    /**
     * One of GENERATION|ALL|NONE to specify how and if the token likelihoods are returned with the response. Defaults to NONE.
     *
     * If GENERATION is selected, the token likelihoods will only be provided for generated text.
     *
     * If ALL is selected, the token likelihoods will be provided both for the prompt and the generated text.
     *
     * Default: NONE
     */
    private String return_likelihoods;

    /**
     * Used to prevent the model from generating unwanted tokens or to incentivize it to include desired tokens. The format is {token_id: bias} where bias is a float between -10 and 10. Tokens can be obtained from text using Tokenize.
     *
     * For example, if the value {'11': -10} is provided, the model will be very unlikely to include the token 11 ("\n", the newline character) anywhere in the generated text. In contrast {'11': 10} will result in generations that nearly only contain that token. Values between -10 and 10 will proportionally affect the likelihood of the token appearing in the generated text.
     *
     * Note: logit bias may not be supported for all custom models.
     */
    private Map<String, Double> logit_bias;

    GenerateRequest(Builder builder) {
        this.prompt = builder.prompt;
        this.model = builder.model;
        this.stream = builder.stream;
        this.max_tokens = builder.max_tokens;
        this.truncate = builder.truncate;
        this.return_likelihoods = builder.return_likelihoods;
    }

    public Boolean isStreaming() {
        return stream!=null && stream;
    }

    public static class Builder {
        private String prompt;
        private String model;
        private Boolean stream;
        private Integer max_tokens;
        private String truncate;
        private String return_likelihoods;

        public Builder withPrompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        public Builder withModel(String model) {
            this.model = model;
            return this;
        }

        public Builder withStream(Boolean stream) {
            this.stream = stream;
            return this;
        }

        public Builder withMaxTokens(Integer maxTokens) {
            this.max_tokens = maxTokens;
            return this;
        }

        public Builder withTruncate(String truncate) {
            this.truncate = truncate;
            return this;
        }

        public Builder withLikelihoods(String likelihoods) {
            this.return_likelihoods = likelihoods;
            return this;
        }

        public GenerateRequest build() {
            return new GenerateRequest(this);
        }
    }
}
