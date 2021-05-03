package com.iplpredictor.util;

import com.iplpredictor.model.Match;
import com.iplpredictor.model.PointsTable;
import com.iplpredictor.model.PredictionResult;
import com.iplpredictor.model.TeamStat;

public class PredictionUtil {

    private static final int TOTAL_NO_OF_MATCHES = 56;
    private static final int TOTAL_NO_OF_TEAMS = 8;

    private static Match[] matches, predictedMatchResults;
    private static TeamStat[] teamStats, predictedTeamStats;
    private static PointsTable pointsTable, predictedPointsTable;
    private static int ELAPSED_MATCHES;

    public PredictionUtil(Match[] matches)
    {
        this.matches = matches;
        teamStats = this.buildTeamStat(matches);
        pointsTable = this.buildPointsTable(teamStats);
    }

    private boolean predict(int homeTeam, TeamStat[] teamStats, Match[] matches, int currentMatch) {
        if(currentMatch == TOTAL_NO_OF_MATCHES) {
            //check possibility with points table
            PointsTable pointsTable = new PointsTable(teamStats);
            for(int i = 0 ; i < 8; i++) {
                pointsTable.getTeamStats()[i].updatePoints();
            }
            return pointsTable.canHomeTeamReachPlayoffs(homeTeam);
        }

        Match thisMatch = matches[currentMatch];
        teamStats[thisMatch.oppositionA].setNoOfMatches(teamStats[thisMatch.oppositionA].getNoOfMatches() + 1);
        teamStats[thisMatch.oppositionB].setNoOfMatches(teamStats[thisMatch.oppositionB].getNoOfMatches() + 1);

        if(thisMatch.oppositionA == homeTeam || thisMatch.oppositionB == homeTeam) {
            if(thisMatch.oppositionA == homeTeam) {
                thisMatch.winnerId = thisMatch.oppositionA;
                teamStats[thisMatch.oppositionA].setNoOfWins(teamStats[thisMatch.oppositionA].getNoOfWins() + 1);
                teamStats[thisMatch.oppositionB].setNoOfDefeats(teamStats[thisMatch.oppositionB].getNoOfDefeats() + 1);
            } else {
                thisMatch.winnerId = thisMatch.oppositionB;
                teamStats[thisMatch.oppositionB].setNoOfWins(teamStats[thisMatch.oppositionB].getNoOfWins() + 1);
                teamStats[thisMatch.oppositionA].setNoOfDefeats(teamStats[thisMatch.oppositionA].getNoOfDefeats() + 1);
            }
            return predict(homeTeam, teamStats, matches, currentMatch+1);
        } else {
            //Making first team win
            thisMatch.winnerId = thisMatch.oppositionA;
            teamStats[thisMatch.oppositionA].setNoOfWins(teamStats[thisMatch.oppositionA].getNoOfWins() + 1);
            teamStats[thisMatch.oppositionB].setNoOfDefeats(teamStats[thisMatch.oppositionB].getNoOfDefeats() + 1);

            boolean result = predict(homeTeam, teamStats, matches,currentMatch+1);
            if(result) {
                return true;
            }
            teamStats[thisMatch.oppositionA].setNoOfWins(teamStats[thisMatch.oppositionA].getNoOfWins() - 1);
            teamStats[thisMatch.oppositionB].setNoOfDefeats(teamStats[thisMatch.oppositionB].getNoOfDefeats() - 1);

            //Making second team win
            thisMatch.winnerId = thisMatch.oppositionB;
            teamStats[thisMatch.oppositionB].setNoOfWins(teamStats[thisMatch.oppositionB].getNoOfWins() + 1);
            teamStats[thisMatch.oppositionA].setNoOfDefeats(teamStats[thisMatch.oppositionA].getNoOfDefeats() + 1);
            result = predict(homeTeam, teamStats, matches, currentMatch+1);
            if(result) {
                return true;
            }
        }
        teamStats[thisMatch.oppositionA].setNoOfMatches(teamStats[thisMatch.oppositionA].getNoOfMatches() - 1);
        teamStats[thisMatch.oppositionB].setNoOfMatches(teamStats[thisMatch.oppositionB].getNoOfMatches() - 1);
        return false;
    }

    public PredictionResult predict(int homeTeam) {
        predictedTeamStats = new TeamStat[TOTAL_NO_OF_TEAMS];
        for(int i=0; i<TOTAL_NO_OF_TEAMS; i++) {
            predictedTeamStats[i] = new TeamStat(teamStats[i]);
            predictedTeamStats[i].reset();
        }

        predictedMatchResults = new Match[TOTAL_NO_OF_MATCHES];
        for(int i=0; i<TOTAL_NO_OF_MATCHES; i++) {
            predictedMatchResults[i] = new Match(matches[i]);
        }

        boolean result = predict(homeTeam, predictedTeamStats, predictedMatchResults, ELAPSED_MATCHES+1);
        PointsTable predictedPointsTable =  this.buildPointsTable(predictedTeamStats);
        return new PredictionResult(predictedMatchResults, predictedPointsTable);
    }

    private TeamStat[] buildTeamStat(Match[] matches) {
        TeamStat[] teamStats = new TeamStat[TOTAL_NO_OF_TEAMS];
        for(int i=0; i<TOTAL_NO_OF_TEAMS; i++) {
            teamStats[i] = new TeamStat(i);
        }

        for(int i=0; i<TOTAL_NO_OF_MATCHES; i++) {
            Match match = matches[i];
            if(match.matchOver) {
                teamStats[match.oppositionA].setNoOfMatches(teamStats[match.oppositionA].getNoOfMatches() + 1);
                teamStats[match.oppositionB].setNoOfMatches(teamStats[match.oppositionB].getNoOfMatches() + 1);

                if(!match.endResultAvailable) {
                    teamStats[match.oppositionA].setNoOfMatchesWithNR(teamStats[match.oppositionA].getNoOfMatchesWithNR() + 1);
                    teamStats[match.oppositionB].setNoOfMatchesWithNR(teamStats[match.oppositionB].getNoOfMatchesWithNR() + 1);
                } else {
                    if(match.winnerId == match.oppositionA) {
                        teamStats[match.oppositionA].setNoOfWins(teamStats[match.oppositionA].getNoOfWins() + 1);
                        teamStats[match.oppositionB].setNoOfDefeats(teamStats[match.oppositionB].getNoOfDefeats() + 1);
                    } else {
                        teamStats[match.oppositionB].setNoOfWins(teamStats[match.oppositionB].getNoOfWins() + 1);
                        teamStats[match.oppositionA].setNoOfDefeats(teamStats[match.oppositionA].getNoOfDefeats() + 1);
                    }
                }

            } else {
                ELAPSED_MATCHES = i-1;
                break;
            }
        }
        return teamStats;
    }

    private PointsTable buildPointsTable(TeamStat[] teamStats) {
        return new PointsTable(teamStats);
    }
}
