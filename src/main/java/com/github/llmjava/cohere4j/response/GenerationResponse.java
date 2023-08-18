package com.github.llmjava.cohere4j.response;

import java.util.ArrayList;
import java.util.List;

public class GenerationResponse {
    private String id;
    private String prompt;
    private List<Generation> generations;

    public String getPrompt() {
        return prompt;
    }

    public List<String> getTexts() {
        List<String> texts = new ArrayList<>();
        for(Generation generation: generations) {
            texts.add(generation.getText());
        }
        return texts;
    }
}
