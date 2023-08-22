package com.github.llmjava.cohere4j;

import com.github.llmjava.cohere4j.request.*;
import com.github.llmjava.cohere4j.response.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Streaming;

public interface CohereApi {

    /**
     * This endpoint generates realistic text conditioned on a given input.
     */
    @POST("/v1/generate")
    @Headers({"accept: application/json", "content-type: application/json"})
    Call<GenerateResponse>
    generate(@Body GenerateRequest request);

    /**
     * This endpoint generates realistic text conditioned on a given input.
     */
    @Streaming
    @POST("/v1/generate")
    @Headers({"accept: application/stream+json", "content-type: application/json"})
    Call<String>
    generateStream(@Body GenerateRequest request);

    /**
     * This endpoint returns text embeddings. An embedding is a list of floating point numbers that captures semantic information about the text that it represents.
     *
     * Embeddings can be used to create text classifiers as well as empower semantic search. To learn more about embeddings, see the embedding page.
     *
     * If you want to learn more how to use the embedding model, have a look at the Semantic Search Guide
     */
    @POST("/v1/embed")
    @Headers({"accept: application/json", "content-type: application/json"})
    Call<EmbedResponse>
    embed(@Body EmbedRequest request);

    /**
     * This endpoint makes a prediction about which label fits the specified text inputs best. To make a prediction, Classify uses the provided examples of text + label pairs as a reference.
     *
     * Note: Custom Models trained on classification examples don't require the examples parameter to be passed in explicitly.
     */
    @POST("/v1/classify")
    @Headers({"accept: application/json", "content-type: application/json"})
    Call<ClassifyResponse>
    classify(@Body ClassifyRequest request);

    /**
     * This endpoint splits input text into smaller units called tokens using byte-pair encoding (BPE). To learn more about tokenization and byte pair encoding, see the tokens page.
     */
    @POST("/v1/tokenize")
    @Headers({"accept: application/json", "content-type: application/json"})
    Call<TokenizeResponse>
    tokenize(@Body TokenizeRequest request);

    /**
     * This endpoint takes tokens using byte-pair encoding and returns their text representation. To learn more about tokenization and byte pair encoding, see the tokens page.
     */
    @POST("/v1/detokenize")
    @Headers({"accept: application/json", "content-type: application/json"})
    Call<DetokenizeResponse>
    detokenize(@Body DetokenizeRequest request);

    /**
     * This endpoint identifies which language each of the provided texts is written in.
     */
    @POST("/v1/detect-language")
    @Headers({"accept: application/json", "content-type: application/json"})
    Call<DetectLanguageResponse>
    detectLanguage(@Body DetectLanguageRequest request);
}
