package com.github.llmjava.cohere4j;

import com.github.llmjava.cohere4j.callback.AsyncCallback;
import com.github.llmjava.cohere4j.request.DetokenizeRequest;
import com.github.llmjava.cohere4j.response.DetokenizeResponse;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.util.Arrays;

public class DetokenizationExample {

    public static void main(String[] args) throws ConfigurationException {
        CohereConfig config = CohereConfig.fromProperties("cohere.properties");
        CohereClient client = new CohereClient.Builder().withConfig(config).build();

        DetokenizeRequest request = new DetokenizeRequest.Builder()
                .withTokens(Arrays.asList(10104, 12221, 1315, 34, 1420, 69))
                .withModel("command")
                .build();

        System.out.println("--- Sync example");
        DetokenizeResponse response = client.detokenize(request);
        System.out.println("Text: " + response.getText());

        client.detokenizeAsync(request, new AsyncCallback<DetokenizeResponse>() {
            @Override
            public void onSuccess(DetokenizeResponse response) {
                System.out.println("--- Async example - onSuccess");
                System.out.println("Text: " + response.getText());
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("--- Async example - onFailure");
                throwable.printStackTrace();
            }
        });
    }
}
