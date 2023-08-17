package com.github.llmjava.cohere4j;

import com.github.llmjava.cohere4j.callback.AsyncCallback;
import com.github.llmjava.cohere4j.callback.StreamingCallback;
import com.github.llmjava.cohere4j.request.CompletionRequest;
import com.github.llmjava.cohere4j.response.CompletionResponse;
import com.github.llmjava.cohere4j.response.streaming.StreamingCompletionResponse;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class CohereClient {
    private final CohereApi api;
    private final CohereConfig config;

    CohereClient(Builder builder) {
        this.api = builder.api;
        this.config = builder.config;
    }

    public String generate(CompletionRequest request) {
        try {
            Response<CompletionResponse> response = api.generate(request).execute();
            if (response.isSuccessful()) {
                return response.body().getTexts().get(0);
            } else  {
                throw newException(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void generateAsync(CompletionRequest request, AsyncCallback<String> callback) {
        api.generate(request).enqueue(new retrofit2.Callback<CompletionResponse>() {
            @Override
            public void onResponse(Call<CompletionResponse> call, Response<CompletionResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body().getTexts().get(0));
                } else  {
                    callback.onFailure(newException(response));
                }
            }

            @Override
            public void onFailure(Call<CompletionResponse> call, Throwable throwable) {
                callback.onFailure(throwable);
            }
        });
    }

    public void generateStream(CompletionRequest request, StreamingCallback<String> callback) {
        api.generateStream(request).enqueue(new retrofit2.Callback<CompletionResponse>() {
            @Override
            public void onResponse(Call<CompletionResponse> call, Response<CompletionResponse> response) {
                if (response.isSuccessful()) {
                    CompletionResponse resp = response.body();
                    callback.onPart(resp.getTexts().get(0));
                    callback.onComplete();

                } else  {
                    callback.onFailure(newException(response));
                }
            }

            @Override
            public void onFailure(Call<CompletionResponse> call, Throwable throwable) {
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

        public Builder withConfig(CohereConfig config) {
            this.config = config;
            this.api = new CohereApiFactory().build(config);
            return this;
        }

        public CohereClient build() {
            return new CohereClient(this);
        }
    }
}
