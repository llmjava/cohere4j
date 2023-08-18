package com.github.llmjava.cohere4j;

import com.github.llmjava.cohere4j.callback.AsyncCallback;
import com.github.llmjava.cohere4j.callback.StreamingCallback;
import com.github.llmjava.cohere4j.request.GenerationRequest;
import com.github.llmjava.cohere4j.response.GenerationResponse;
import com.github.llmjava.cohere4j.response.streaming.StreamingGenerationResponse;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class GenerationExample {

    public static void main(String[] args) throws ConfigurationException {
        CohereConfig config = CohereConfig.fromProperties("cohere.properties");
        CohereClient client = new CohereClient.Builder().withConfig(config).build();

        String text = "tell a joke";
        GenerationRequest request1 = new GenerationRequest.Builder()
                .withPrompt(text)
                .withConfig(config)
                .build();

        System.out.println("--- Sync example");
        System.out.println(client.generate(request1));
        client.generateAsync(request1, new AsyncCallback<GenerationResponse>() {
            @Override
            public void onSuccess(GenerationResponse completion) {
                System.out.println("--- Async example - onSuccess");
                System.out.println(completion.getTexts().get(0));
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("--- Async example - onFailure");
                throwable.printStackTrace();
            }
        });

        GenerationRequest request2 = new GenerationRequest.Builder()
                .withPrompt(text)
                .withConfig(config)
                .withStream(true)
                .build();
        client.generateStream(request2, new StreamingCallback<StreamingGenerationResponse>() {
            @Override
            public void onPart(StreamingGenerationResponse response) {
                System.out.println("--- Streaming example - part");
                System.out.println(response.getText());
            }

            @Override
            public void onComplete(StreamingGenerationResponse response) {
                System.out.println("--- Streaming example - complete");
                System.out.println(response.getText());
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("--- Streaming example - failure");
                throwable.printStackTrace();
            }
        });
    }
}
