package com.github.llmjava.cohere4j;

import com.github.llmjava.cohere4j.request.GenerationRequest;
import com.github.llmjava.cohere4j.response.GenerationResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Streaming;

public interface CohereApi {

    @POST("/v1/generate")
    @Headers({"accept: application/json", "content-type: application/json"})
    Call<GenerationResponse>
    generate(@Body GenerationRequest request);

    @Streaming
    @POST("/v1/generate")
    @Headers({"accept: application/stream+json", "content-type: application/json"})
    Call<String>
    generateStream(@Body GenerationRequest request);

}
