package com.github.llmjava.cohere4j;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.time.Duration;

public class CohereApiFactory {

    Gson gson;
    OkHttpClient okHttpClient;

    public CohereApiFactory createHttpClient(CohereConfig config) {
        String apiKey = config.getApiKey();
        Duration timeout = config.getTimeout();
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new AuthorizationInterceptor(apiKey))
                .callTimeout(timeout)
                .connectTimeout(timeout)
                .readTimeout(timeout)
                .writeTimeout(timeout)
                .build();
        return this;
    }

    CohereApiFactory createGson() {
        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setLenient()
                .create();
        return this;
    }

    CohereApi build() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CohereConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(CohereApi.class);
    }

}
