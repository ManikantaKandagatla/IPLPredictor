package com.iplpredictor.service;

import com.iplpredictor.model.*;

import java.util.List;
import java.util.Map;

public interface PredictionService {
    PredictionResult predictResult(int teamId);
    Schedule getSchedule(long startTime);
    Map<Integer,Object> getPredictionCounts();
    List<Match> getNextMatch();
    boolean poll(int matchId, int teamId);
    MatchPoll getPollData(int matchId);
    List<Match> getAllMatches();
    PointsTable getPointsTable();
    List<MatchPoll> getNextMatchPollData();
}
