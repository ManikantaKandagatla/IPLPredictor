package com.iplpredictor.mapper;

import com.iplpredictor.model.TeamStat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PointsTableMapper implements RowMapper<TeamStat>  {
    @Override
    public TeamStat mapRow(ResultSet resultSet, int i) throws SQLException {
        TeamStat teamStat = new TeamStat(resultSet.getInt("team"));
        teamStat.setNetRunRate(resultSet.getFloat("nrr"));
        teamStat.setNoOfDefeats(resultSet.getInt("loss"));
        teamStat.setNoOfMatches(resultSet.getInt("matches"));
        teamStat.setNoOfMatchesWithNR(resultSet.getInt("nr"));
        teamStat.setNoOfWins(resultSet.getInt("win"));
        teamStat.setPoints(resultSet.getInt("points"));
        return teamStat;
    }
}
