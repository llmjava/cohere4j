package com.github.llmjava.cohere4j;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.time.Duration;
import java.util.*;

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
    public Integer getTopK() {
        return config.getInteger("topK", 0);
    }
    public Double geTopP() {
        return config.getDouble("topP", 0.0);
    }
    public Double getTemperature() {
        return config.getDouble("temperature", 0.75);
    }

    public String getModel() {
        return config.getString("cohere.model", "command");
    }
    public Integer getNumGenerations() {
        return config.getInteger("num_generations", 1);
    }
    public Boolean isStream() {
        return config.getBoolean("stream", false);
    }
    public Integer getMaxTokens() {
        return config.getInteger("max_tokens", 1024);
    }
    public String getTruncate() {
        return config.getString("truncate", "END");
    }
    public String getPreset() {
        return config.getString("preset");
    }
    public List<String> getEndSequences() {
        List<String> result = new ArrayList<>();
        String sequences = config.getString("end_sequences");
        if(sequences!=null) {
            for(String seq: sequences.split(",")) result.add(seq);
        }
        return result;
    }
    public List<String> getStopSequences() {
        List<String> result = new ArrayList<>();
        String sequences = config.getString("stop_sequences");
        if(sequences!=null) {
            for(String seq: sequences.split(",")) result.add(seq);
        }
        return result;
    }
    public Double getFrequencyPenalty() {
        return config.getDouble("frequency_penalty", 0.0);
    }
    public Double getPresencePenalty() {
        return config.getDouble("presence_penalty", 0.0);
    }
    public String getReturnLikelihoods() {
        return config.getString("return_likelihoods");
    }
    public Map<String, Double> getLogitBias() {
        Map<String, Double> result = new HashMap<>();
        String sequences = config.getString("logit_bias");
        if(sequences!=null) {
            for(String pair: sequences.split(",")) {
                String[] kv = pair.split(":");
                if(kv.length==2) {
                    result.put(kv[0], Double.valueOf(kv[1]));
                }
            }
        }
        return result;
    }

    public static CohereConfig fromProperties(String path) throws ConfigurationException {
        Configuration baseConfig = new Configurations().properties(path);
        return new CohereConfig(baseConfig);
    }
}
