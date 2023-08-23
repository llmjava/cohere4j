package com.github.llmjava.cohere4j.request;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RerankRequest {
    /**
     * The identifier of the model to use, one of : rerank-english-v2.0, rerank-multilingual-v2.0
     */
    private String model;
    /**
     * The search query.
     */
    private String query;
    /**
     * A list of document objects or strings to rerank.
     * If a document is provided the text fields is required and all other fields will be preserved in the response.
     * The total max chunks (length of documents * max_chunks_per_doc) must be less than 10000.
     */
    private List<String> documents;
    /**
     * The number of most relevant documents or indices to return, defaults to the length of the documents
     */
    private Integer top_n;
    /**
     * If false, returns results without the doc text - the api will return a list of {index, relevance score} where index is inferred from the list passed into the request.
     * If true, returns results with the doc text passed in - the api will return an ordered list of {index, text, relevance score} where index + text refers to the list passed into the request.
     */
    private Boolean return_documents;
    /**
     * The maximum number of chunks to produce internally from a document
     */
    private Integer max_chunks_per_doc;

    RerankRequest(Builder builder) {
        this.model = builder.model;
        this.query = builder.query;
        this.documents = builder.documents;
        this.top_n = builder.top_n;
        this.return_documents = builder.return_documents;
        this.max_chunks_per_doc = builder.max_chunks_per_doc;
    }

    public static class Builder {
        private String model;

        private String query;
        private List<String> documents = new ArrayList<>();
        private Integer top_n;
        private Boolean return_documents;
        private Integer max_chunks_per_doc;

        public Builder withModel(String model) {
            this.model = model;
            return this;
        }

        public Builder withQuery(String query) {
            this.query = query;
            return this;
        }

        public Builder withDocument(String document) {
            this.documents.add(document);
            return this;
        }

        public Builder withTopN(Integer top_n) {
            this.top_n = top_n;
            return this;
        }

        public Builder withReturnDocuments(Boolean return_documents) {
            this.return_documents = return_documents;
            return this;
        }

        public Builder withMaxChunksPerDoc(Integer max_chunks_per_doc) {
            this.max_chunks_per_doc = max_chunks_per_doc;
            return this;
        }

        public RerankRequest build() {
            return new RerankRequest(this);
        }
    }
}
