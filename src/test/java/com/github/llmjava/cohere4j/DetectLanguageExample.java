package com.github.llmjava.cohere4j;

import com.github.llmjava.cohere4j.callback.AsyncCallback;
import com.github.llmjava.cohere4j.request.DetectLanguageRequest;
import com.github.llmjava.cohere4j.request.DetokenizeRequest;
import com.github.llmjava.cohere4j.response.DetectLanguageResponse;
import com.github.llmjava.cohere4j.response.DetokenizeResponse;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.util.Arrays;

public class DetectLanguageExample {

    public static void main(String[] args) throws ConfigurationException {
        CohereConfig config = CohereConfig.fromProperties("cohere.properties");
        CohereClient client = new CohereClient.Builder().withConfig(config).build();

        DetectLanguageRequest request = new DetectLanguageRequest.Builder()
                .withText("Hello world")
                .withText("مرحبا بالعالم")
                .build();

        System.out.println("--- Sync example");
        DetectLanguageResponse response = client.detectLanguage(request);
        System.out.println("Language 1: " + response.getLanguageName(0));
        System.out.println("Language 2: " + response.getLanguageName(1));

        client.detectLanguageAsync(request, new AsyncCallback<DetectLanguageResponse>() {
            @Override
            public void onSuccess(DetectLanguageResponse response) {
                System.out.println("--- Async example - onSuccess");
                System.out.println("Language 1: " + response.getLanguageName(0));
                System.out.println("Language 2: " + response.getLanguageName(1));
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("--- Async example - onFailure");
                throwable.printStackTrace();
            }
        });
    }
}
