package com.github.llmjava.cohere4j;

import com.github.llmjava.cohere4j.callback.AsyncCallback;
import com.github.llmjava.cohere4j.request.EmbedRequest;
import com.github.llmjava.cohere4j.response.EmbedResponse;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class EmbeddingsExample {

    public static void main(String[] args) throws ConfigurationException {
        CohereConfig config = CohereConfig.fromProperties("cohere.properties");
        CohereClient client = new CohereClient.Builder().withConfig(config).build();

        String text = "tell a joke";
        EmbedRequest request = new EmbedRequest.Builder()
                .withText(text)
                .build();

        System.out.println("--- Sync example");
        EmbedResponse response = client.embed(request);
        System.out.println("Texts: " + response.getTexts()[0]);
        System.out.println("Embeddings: " + response.getEmbeddings(0));
        client.embedAsync(request, new AsyncCallback<EmbedResponse>() {
            @Override
            public void onSuccess(EmbedResponse response) {
                System.out.println("--- Async example - onSuccess");
                System.out.println("Texts: " + response.getTexts()[0]);
                System.out.println("Embeddings: " + response.getEmbeddings(0));
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("--- Async example - onFailure");
                throwable.printStackTrace();
            }
        });
    }
}
