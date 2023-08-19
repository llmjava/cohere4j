package com.github.llmjava.cohere4j.response;

public class EmbedResponse {
    private String id;

    private String[] texts;

    private Float[][] embeddings;

    private Meta meta;

    public String[] getTexts() {
        return texts;
    }

    public Float[][] getEmbeddings() {
        return embeddings;
    }

    public Float[] getEmbeddings(int index) {
        return embeddings[index];
    }
}
