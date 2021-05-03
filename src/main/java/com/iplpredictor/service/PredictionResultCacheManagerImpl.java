package com.iplpredictor.service;

import com.iplpredictor.model.PredictionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class PredictionResultCacheManagerImpl implements PredictionResultCacheManager {

    @Autowired
    private RedisCommonService<PredictionResult> redisCommonService;

    @Override
    public void cachePredictionResult(String key, PredictionResult predictionResult) {
        redisCommonService.putValue(key, predictionResult);
        log.info("Successfully cached the predictionResult for key: " + key);
    }

    @Override
    public PredictionResult getCachedPredictionResult(String key) {
        PredictionResult result =  redisCommonService.getValue(key);
        if(Objects.nonNull(result)) {
            log.info("Successfully retrieved prediction result from cache for key : " + key);
        }
        return result;
    }
}
