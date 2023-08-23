package com.github.llmjava.cohere4j.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RerankResponse {
    private String id;
    private List<Result> results;
    private Map<Integer, Result> resultByIndex = new HashMap<>();
    private Meta meta;

    private void init() {
        if(resultByIndex.isEmpty() && !(results==null || results.isEmpty())) {
            for(Result result: results) {
                resultByIndex.put(result.index, result);
            }
        }
    }

    public Result getResultByIndex(int index) {
        init();
        return resultByIndex.get(index);
    }

    public Result getResultByRank(int rank) {
        return results.get(rank);
    }

    public Float getScoreByIndex(int index) {
        init();
        Result result = resultByIndex.get(index);
        return result.getRelevanceScore();
    }

    public static class Result {
        private Integer index;
        private Float relevance_score;

        public Integer getIndex() {
            return index;
        }

        public Float getRelevanceScore() {
            return relevance_score;
        }
    }
}
