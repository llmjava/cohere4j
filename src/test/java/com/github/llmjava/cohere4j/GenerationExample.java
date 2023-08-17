package com.github.llmjava.cohere4j;

import com.github.llmjava.cohere4j.callback.AsyncCallback;
import com.github.llmjava.cohere4j.callback.StreamingCallback;
import com.github.llmjava.cohere4j.request.CompletionRequest;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class GenerationExample {

    public static void main(String[] args) throws ConfigurationException {
        CohereConfig config = CohereConfig.fromProperties("cohere.properties");
        CohereClient client = new CohereClient.Builder().withConfig(config).build();

        String text = "tell a joke";
        CompletionRequest request = new CompletionRequest.Builder()
                .withPrompt(text)
                .withMaxTokens(1024)
                .build();

        System.out.println("--- Sync example");
        System.out.println(client.generate(request));
        client.generateAsync(request, new AsyncCallback<String>() {
            @Override
            public void onSuccess(String completion) {
                System.out.println("--- Async example - onSuccess");
                System.out.println(completion);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("--- Async example - onFailure");
                throwable.printStackTrace();
            }
        });

        client.generateStream(request, new StreamingCallback<String>() {
            @Override
            public void onPart(String response) {
                System.out.println("--- Streaming example - part");
                System.out.println(response);
            }

            @Override
            public void onComplete() {
                System.out.println("--- Streaming example - complete");
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("--- Streaming example - failure");
                throwable.printStackTrace();
            }
        });
    }
}
