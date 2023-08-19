package com.github.llmjava.cohere4j;

import com.github.llmjava.cohere4j.request.ClassifyRequest;
import com.github.llmjava.cohere4j.request.EmbedRequest;
import com.github.llmjava.cohere4j.request.GenerateRequest;
import com.github.llmjava.cohere4j.response.ClassifyResponse;
import com.github.llmjava.cohere4j.response.EmbedResponse;
import com.github.llmjava.cohere4j.response.GenerateResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Streaming;

public interface CohereApi {

    @POST("/v1/generate")
    @Headers({"accept: application/json", "content-type: application/json"})
    Call<GenerateResponse>
    generate(@Body GenerateRequest request);

    @Streaming
    @POST("/v1/generate")
    @Headers({"accept: application/stream+json", "content-type: application/json"})
    Call<String>
    generateStream(@Body GenerateRequest request);

    @POST("/v1/embed")
    @Headers({"accept: application/json", "content-type: application/json"})
    Call<EmbedResponse>
    embed(@Body EmbedRequest request);

    @POST("/v1/classify")
    @Headers({"accept: application/json", "content-type: application/json"})
    Call<ClassifyResponse>
    classify(@Body ClassifyRequest request);
}
