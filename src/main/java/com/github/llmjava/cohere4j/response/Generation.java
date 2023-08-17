package com.github.llmjava.cohere4j.response;

import java.util.List;

public class Generation {
    private String id;
    private String text;
    private Integer index;
    private Double likelihood;
    private List<Likelihood> token_likelihoods;

    public String getText() {
        return text;
    }
}
