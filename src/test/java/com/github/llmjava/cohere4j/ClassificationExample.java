package com.github.llmjava.cohere4j;

import com.github.llmjava.cohere4j.callback.AsyncCallback;
import com.github.llmjava.cohere4j.request.ClassifyRequest;
import com.github.llmjava.cohere4j.request.EmbedRequest;
import com.github.llmjava.cohere4j.response.ClassifyResponse;
import com.github.llmjava.cohere4j.response.EmbedResponse;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class ClassificationExample {

    public static void main(String[] args) throws ConfigurationException {
        CohereConfig config = CohereConfig.fromProperties("cohere.properties");
        CohereClient client = new CohereClient.Builder().withConfig(config).build();

        ClassifyRequest request = new ClassifyRequest.Builder()
                .withExample("Dermatologists don't like her!", "Spam")
                .withExample("Hello, open to this?", "Spam")
                .withExample("I need help please wire me $1000 right now", "Spam")
                .withExample("Nice to know you ;)", "Spam")
                .withExample("Please help me?", "Spam")
                .withExample("Your parcel will be delivered today", "Not spam")
                .withExample("Review changes to our Terms and Conditions", "Not spam")
                .withExample("Weekly sync notes", "Not spam")
                .withExample("Re: Follow up from today’s meeting", "Not spam")
                .withExample("Pre-read for tomorrow", "Not spam")
                .withInput("Confirm your email address")
                .withInput("hey i need u to send some $")
                .withTruncate("END")
                .build();

        System.out.println("--- Sync example");
        ClassifyResponse response = client.classify(request);
        System.out.println("Input: " + response.getClassification(0).getInput());
        System.out.println("Prediction: " + response.getClassification(0).getPrediction());
        System.out.println("Confidence: " + response.getClassification(0).getConfidence());

        client.classifyAsync(request, new AsyncCallback<ClassifyResponse>() {
            @Override
            public void onSuccess(ClassifyResponse response) {
                System.out.println("--- Async example - onSuccess");
                System.out.println("Input: " + response.getClassification(0).getInput());
                System.out.println("Prediction: " + response.getClassification(0).getPrediction());
                System.out.println("Confidence: " + response.getClassification(0).getConfidence());            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("--- Async example - onFailure");
                throwable.printStackTrace();
            }
        });
    }
}
