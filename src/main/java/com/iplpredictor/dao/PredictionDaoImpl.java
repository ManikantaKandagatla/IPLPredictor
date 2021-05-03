package com.iplpredictor.dao;

import com.iplpredictor.mapper.MatchMapper;
import com.iplpredictor.mapper.MatchPollMapper;
import com.iplpredictor.mapper.PointsTableMapper;
import com.iplpredictor.model.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PredictionDaoImpl implements PredictionDao {

    private NamedParameterJdbcTemplate template;

    public PredictionDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public boolean updatePredictionCount(int teamId) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", teamId);
        int rowCount = this.template.update("update prediction_counts set count = count + 1 where id = :id", param);
        return rowCount > 0;
    }

    @Override
    public Schedule getSchedule(long startTime) {
        List<Match> matches = template.query("select * from matches order by id", new MatchMapper());
        Schedule sch = new Schedule();
        sch.setMatchList(matches);
        return sch;
    }

    @Override
    public List<Map<String, Object>> getPredictionCounts() {
        return template.queryForList("select * from prediction_counts", new HashMap<>());
    }

    @Override
    public List<Match> getNextMatch(long dayNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("dayNumber", dayNum);
        List<Match> matches = template.query("select * from matches where dayNumber >= :dayNumber limit 2", params, new MatchMapper());
        return matches;
    }

    @Override
    public boolean updatePoll(int matchId, int teamId) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", matchId);
        List<Match> matches = template.query("select * from matches where id = :id", params, new MatchMapper());
        Match match = matches.get(0);
        int opponentA = match.getOppositionA();
        int opponentB = match.getOppositionB();

        Map<String, Object> pollParams = new HashMap<>();
        pollParams.put("matchId", matchId);
        int count = 0;
        if (teamId == opponentA) {
            count = template.update("update matchPoll set opponentA_count = opponentA_count + 1 where matchId = :matchId", pollParams);
        }
        if (teamId == opponentB) {
            count = template.update("update matchPoll set opponentB_count = opponentB_count + 1 where matchId = :matchId", pollParams);
        }

        return count > 0;
    }

    @Override
    public MatchPoll getPollData(int matchId) {
        Map<String, Object> params = new HashMap<>();
        params.put("matchId", matchId);
        List<MatchPoll> matchPolls = this.template.query("select a.*, b.oppositiona, b.oppositionb from matchPoll a inner join matches b on a.matchId = b.id where a.matchId = :matchId", params, new MatchPollMapper());
        return matchPolls.get(0);
    }

    @Override
    public List<MatchPoll> getNextMatchPollData() {
        Map<String, Object> params = new HashMap<>();
        List<MatchPoll> matchPolls = this.template.query("select a.*, b.oppositiona, b.oppositionb from matchPoll a inner join matches b on a.matchId = b.id where b.matchover=false limit 2", params, new MatchPollMapper());
        return matchPolls;
    }

    @Override
    public List<Match> getAllMatches() {
        return template.query("select * from matches order by id", new HashMap<>(), new MatchMapper());
    }

    @Override
    public PointsTable getPointsTable() {
        List<TeamStat> teamStats = template.query("select * from points_table", new HashMap<>(), new PointsTableMapper());
        PointsTable pointsTable = new PointsTable();
        TeamStat[] teamStatsArr = Arrays.copyOf(teamStats.toArray(), 8, TeamStat[].class);
        pointsTable.setTeamStats(teamStatsArr);
        return pointsTable;
    }

}
