package com.github.llmjava.cohere4j;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.time.Duration;

public class CohereConfig {
    private final Configuration config;

    public CohereConfig(Configuration config) {
        this.config = config;
    }

    public static final String BASE_URL = "https://api.cohere.ai/";

    public static final String API_KEY = "cohere.apiKey";

    public static final String TIMEOUT = "timeout";

    public static final Long DEFAULT_TIMEOUT_MILLIS = 10*1000l;

    public String getApiKey() {
        return config.getString(API_KEY);
    }

    public Duration getTimeout() {
        Long timeout = config.getLong(CohereConfig.TIMEOUT, CohereConfig.DEFAULT_TIMEOUT_MILLIS);
        return Duration.ofMillis(timeout);
    }

    public static CohereConfig fromProperties(String path) throws ConfigurationException {
        Configuration baseConfig = new Configurations().properties(path);
        return new CohereConfig(baseConfig);
    }

}
