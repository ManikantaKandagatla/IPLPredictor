package com.iplpredictor.dao;

import com.iplpredictor.model.Match;
import com.iplpredictor.model.MatchPoll;
import com.iplpredictor.model.PointsTable;
import com.iplpredictor.model.Schedule;

import java.util.List;
import java.util.Map;

public interface PredictionDao {
    boolean updatePredictionCount(int teamId);
    Schedule getSchedule(long startTime);
    List<Map<String,Object>> getPredictionCounts();
    List<Match> getNextMatch(long dayNum);
    boolean updatePoll(int matchId, int teamId);
    MatchPoll getPollData(int matchId);
    List<MatchPoll> getNextMatchPollData();
    List<Match> getAllMatches();
    PointsTable getPointsTable();
}
