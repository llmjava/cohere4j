package com.github.llmjava.cohere4j;

import com.github.llmjava.cohere4j.callback.AsyncCallback;
import com.github.llmjava.cohere4j.request.RerankRequest;
import com.github.llmjava.cohere4j.response.RerankResponse;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class RerankExample {

    public static void main(String[] args) throws ConfigurationException {
        CohereConfig config = CohereConfig.fromProperties("cohere.properties");
        CohereClient client = new CohereClient.Builder().withConfig(config).build();

        RerankRequest request = new RerankRequest.Builder()
                .withReturnDocuments(false)
                .withMaxChunksPerDoc(10)
                .withModel("rerank-english-v2.0")
                .withQuery("What is the capital of the United States?")
                .withDocument("Carson City is the capital city of the American state of Nevada.")
                .withDocument("The Commonwealth of the Northern Mariana Islands is a group of islands in the Pacific Ocean. Its capital is Saipan.")
                .withDocument("Washington, D.C. (also known as simply Washington or D.C., and officially as the District of Columbia) is the capital of the United States. It is a federal district.")
                .withDocument("Capital punishment (the death penalty) has existed in the United States since beforethe United States was a country. As of 2017, capital punishment is legal in 30 of the 50 states.")
                .build();

        System.out.println("--- Sync example");
        RerankResponse response = client.rerank(request);
        for(int i=0; i<4; i++) {
            System.out.println("Score Document "+i+": " + response.getScoreByIndex(i));
        }

        client.rerankAsync(request, new AsyncCallback<RerankResponse>() {
            @Override
            public void onSuccess(RerankResponse response) {
                System.out.println("--- Async example - onSuccess");
                for(int i=0; i<4; i++) {
                    System.out.println("Score Document "+i+": " + response.getScoreByIndex(i));
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("--- Async example - onFailure");
                throwable.printStackTrace();
            }
        });
    }
}
