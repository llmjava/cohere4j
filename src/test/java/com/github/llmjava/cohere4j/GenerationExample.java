package com.github.llmjava.cohere4j;

import com.github.llmjava.cohere4j.callback.AsyncCallback;
import com.github.llmjava.cohere4j.callback.StreamingCallback;
import com.github.llmjava.cohere4j.request.GenerateRequest;
import com.github.llmjava.cohere4j.response.GenerateResponse;
import com.github.llmjava.cohere4j.response.streaming.StreamGenerateResponse;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class GenerationExample {

    public static void main(String[] args) throws ConfigurationException {
        CohereConfig config = CohereConfig.fromProperties("cohere.properties");
        CohereClient client = new CohereClient.Builder().withConfig(config).build();

        String text = "tell a joke";
        GenerateRequest request1 = new GenerateRequest.Builder()
                .withPrompt(text)
                .withMaxTokens(1024)
                .build();

        System.out.println("--- Sync example");
        System.out.println(client.generate(request1));
        client.generateAsync(request1, new AsyncCallback<GenerateResponse>() {
            @Override
            public void onSuccess(GenerateResponse completion) {
                System.out.println("--- Async example - onSuccess");
                System.out.println(completion.getTexts().get(0));
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("--- Async example - onFailure");
                throwable.printStackTrace();
            }
        });

        GenerateRequest request2 = new GenerateRequest.Builder()
                .withPrompt(text)
                .withStream(true)
                .withMaxTokens(1024)
                .build();
        client.generateStream(request2, new StreamingCallback<StreamGenerateResponse>() {
            @Override
            public void onPart(StreamGenerateResponse response) {
                System.out.println("--- Streaming example - part");
                System.out.println(response.getText());
            }

            @Override
            public void onComplete(StreamGenerateResponse response) {
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
