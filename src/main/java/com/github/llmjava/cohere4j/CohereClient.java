package com.github.llmjava.cohere4j;

import com.github.llmjava.cohere4j.callback.AsyncCallback;
import com.github.llmjava.cohere4j.callback.StreamingCallback;
import com.github.llmjava.cohere4j.request.GenerationRequest;
import com.github.llmjava.cohere4j.response.GenerationResponse;
import com.github.llmjava.cohere4j.response.streaming.StreamingGenerationResponse;
import com.github.llmjava.cohere4j.response.streaming.ResponseConverter;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class CohereClient {
    private final CohereApi api;
    private final CohereConfig config;
    private final Gson gson;

    CohereClient(Builder builder) {
        this.api = builder.api;
        this.config = builder.config;
        this.gson = builder.gson;
    }

    public GenerationResponse generate(GenerationRequest request) {
        try {
            Response<GenerationResponse> response = api.generate(request).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else  {
                throw newException(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void generateAsync(GenerationRequest request, AsyncCallback<GenerationResponse> callback) {
        api.generate(request).enqueue(new retrofit2.Callback<GenerationResponse>() {
            @Override
            public void onResponse(Call<GenerationResponse> call, Response<GenerationResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else  {
                    callback.onFailure(newException(response));
                }
            }

            @Override
            public void onFailure(Call<GenerationResponse> call, Throwable throwable) {
                callback.onFailure(throwable);
            }
        });
    }

    public void generateStream(GenerationRequest request, StreamingCallback<StreamingGenerationResponse> callback) {
        if(!request.isStreaming()) {
            throw new IllegalArgumentException("Expected a streaming request");
        }
        ResponseConverter converter = new ResponseConverter(gson);
        api.generateStream(request).enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    for(StreamingGenerationResponse resp: converter.toStreamingGenerationResponse(response.body())) {
                        if(resp.isFinished()) {
                            callback.onComplete(resp);
                        } else {
                            callback.onPart(resp);
                        }
                    }

                } else  {
                    callback.onFailure(newException(response));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                callback.onFailure(throwable);
            }
        });
    }

    /**
     * Parse exceptions:
     * status code: 429; body: {"message":"You are using a Trial key, which is limited to 5 API calls / minute. You can continue to use the Trial key for free or upgrade to a Production key with higher rate limits at 'https://dashboard.cohere.ai/api-keys'. Contact us on 'https://discord.gg/XW44jPfYJu' or email us at support@cohere.com with any questions"}
     */
    private static RuntimeException newException(retrofit2.Response<?> response) {
        try {
            int code = response.code();
            String body = response.errorBody().string();
            String errorMessage = String.format("status code: %s; body: %s", code, body);
            return new RuntimeException(errorMessage);
        } catch (IOException e) {
            return new RuntimeException(e);
        }
    }


    public static class Builder {
        private CohereApi api;
        private CohereConfig config;
        private Gson gson;

        public Builder withConfig(CohereConfig config) {
            this.config = config;
            CohereApiFactory factory = new CohereApiFactory();
            this.api = factory.createGson().createHttpClient(config).build();
            this.gson = factory.gson;
            return this;
        }

        public CohereClient build() {
            return new CohereClient(this);
        }
    }
}
