package com.github.llmjava.cohere4j.callback;

public interface AsyncCallback<T> {

    void onSuccess(T response);
    void onFailure(Throwable throwable);
}
