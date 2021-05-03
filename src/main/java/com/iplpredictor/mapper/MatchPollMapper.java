package com.iplpredictor.mapper;

import com.iplpredictor.model.MatchPoll;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchPollMapper implements RowMapper<MatchPoll> {

    @Override
    public MatchPoll mapRow(ResultSet rs, int arg1) throws SQLException {
        int matchId = rs.getInt("matchId");
        long countA = rs.getLong("opponentA_count");
        long countB = rs.getLong("opponentB_count");
        int teamAid = rs.getInt("oppositionA");
        int teamBid = rs.getInt("oppositionB");
        MatchPoll matchPoll = new MatchPoll(matchId, teamAid, countA, teamBid, countB);
        return matchPoll;
    }
}
