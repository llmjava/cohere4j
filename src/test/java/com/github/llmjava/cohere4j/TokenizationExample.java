package com.github.llmjava.cohere4j;

import com.github.llmjava.cohere4j.callback.AsyncCallback;
import com.github.llmjava.cohere4j.request.TokenizeRequest;
import com.github.llmjava.cohere4j.response.TokenizeResponse;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class TokenizationExample {

    public static void main(String[] args) throws ConfigurationException {
        CohereConfig config = CohereConfig.fromProperties("cohere.properties");
        CohereClient client = new CohereClient.Builder().withConfig(config).build();

        TokenizeRequest request = new TokenizeRequest.Builder()
                .withText("tokenize me! :D")
                .withModel("command")
                .build();

        System.out.println("--- Sync example");
        TokenizeResponse response = client.tokenize(request);
        System.out.println("Token: " + response.getTokenString(0));
        System.out.println("Token ID: " + response.getToken(0));

        client.tokenizeAsync(request, new AsyncCallback<TokenizeResponse>() {
            @Override
            public void onSuccess(TokenizeResponse response) {
                System.out.println("--- Async example - onSuccess");
                System.out.println("Token: " + response.getTokenString(0));
                System.out.println("Token ID: " + response.getToken(0));
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("--- Async example - onFailure");
                throwable.printStackTrace();
            }
        });
    }
}
