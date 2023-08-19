package com.github.llmjava.cohere4j.response;

public class TokenizeResponse {
    private Integer[] tokens;
    private String[] token_strings;
    private Meta meta;

    public Integer[] getTokens() {
        return tokens;
    }

    public Integer getToken(int index) {
        return tokens[index];
    }

    public String[] getTokenStrings() {
        return token_strings;
    }

    public String getTokenString(int index) {
        return token_strings[index];
    }
}
