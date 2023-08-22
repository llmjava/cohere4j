package com.github.llmjava.cohere4j.response;

import java.util.List;

public class DetectLanguageResponse {
    private String id;
    private List<Result> results;
    private Meta meta;

    public String getLanguageCode(int index) {
        return results.get(index).language_code;
    }
    public String getLanguageName(int index) {
        return results.get(index).language_name;
    }

    public static class Result {
        String language_code;
        String language_name;
    }
}
