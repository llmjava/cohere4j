package com.github.llmjava.cohere4j;

import com.github.llmjava.cohere4j.request.CompletionRequest;
import com.github.llmjava.cohere4j.response.CompletionResponse;
import com.github.llmjava.cohere4j.response.streaming.StreamingCompletionResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Streaming;

public interface CohereApi {

    @POST("/v1/generate")
    @Headers({"accept: application/json", "content-type: application/json"})
    Call<CompletionResponse>
    generate(@Body CompletionRequest request);

    @Streaming
    @POST("/v1/generate")
    @Headers({"accept: application/stream+json", "content-type: application/json"})
    Call<CompletionResponse>
    generateStream(@Body CompletionRequest request);

}
