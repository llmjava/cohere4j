package com.github.llmjava.cohere4j.response.streaming;

public class StreamingCompletionResponse {

    private String text;
    private Integer index;
    private Boolean is_finished;
    private String finish_reason;
    private Response response;

    public Boolean isFinished() {
        return is_finished;
    }

    public String getFinishReason() {
        return finish_reason;
    }

    public String getText() {
        String text = this.text;
        if(text == null && this.response!=null && this.response.generations!=null) {
            text = this.response.generations.text;
        }
        return text;
    }

    static class Response {
        private String id;
        private Generation generations;
    }

    static class Generation {
        private String id;
        private String text;
        private Integer index;
        private String finish_reason;
    }
}
