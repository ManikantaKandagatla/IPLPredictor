package com.iplpredictor.service;

import com.iplpredictor.model.PredictionResult;

public interface PredictionResultCacheManager {
    void cachePredictionResult(String key, PredictionResult predictionResult);
    PredictionResult getCachedPredictionResult(String key);
}
