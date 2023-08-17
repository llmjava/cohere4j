package com.github.llmjava.cohere4j.request;

public class CompletionRequest {

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
     * The input text that serves as the starting point for generating the response.
     * Note: The prompt will be pre-processed and modified before reaching the model.
     */
    private String prompt;

    CompletionRequest(Builder builder) {
        this.max_tokens = builder.max_tokens;
        this.truncate = builder.truncate;
        this.return_likelihoods = builder.return_likelihoods;
        this.prompt = builder.prompt;
    }

    public static class Builder {
        private Integer max_tokens;
        private String truncate;
        private String return_likelihoods;
        private String prompt;

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

        public Builder withPrompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        public CompletionRequest build() {
            return new CompletionRequest(this);
        }
    }
}
