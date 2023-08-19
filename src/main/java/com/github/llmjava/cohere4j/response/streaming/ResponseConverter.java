package com.github.llmjava.cohere4j.response.streaming;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ResponseConverter {

    private final Gson gson;

    public ResponseConverter(Gson gson) {
        this.gson = gson;
    }

    /**
     * Example for generation type
     * Type elmType =  new TypeToken<T>(){}.getType();
     * Type listType = new TypeToken<ArrayList<T>>(){}.getType();
     */
    public List<StreamGenerateResponse> toStreamingGenerationResponse(String responseBody) {
        List<StreamGenerateResponse> responses = new ArrayList<>();
        Type elmType =  new TypeToken<StreamGenerateResponse>(){}.getType();
        Type listType = new TypeToken<ArrayList<StreamGenerateResponse>>(){}.getType();
        String[] lines = responseBody.split("\n");
        for(String line: lines) {
            if(line.charAt(0)=='[') {
                responses.addAll(gson.fromJson(line, listType));

            } else if(line.charAt(0)=='{') {
                responses.add(gson.fromJson(line, elmType));

            } else {
                System.err.println("Unexpected response: " + line);
            }

        }
        return responses;
    }
}
