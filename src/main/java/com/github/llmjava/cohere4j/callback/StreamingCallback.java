package com.github.llmjava.cohere4j.callback;

public interface StreamingCallback<S> {

    void onPart(S response);
    void onComplete(S response);
    void onFailure(Throwable throwable);
}
