package com.iplpredictor.model;

import lombok.Data;

@Data
public class MatchPoll {
    private int matchId;
    private TeamPollCount teamPollCountA;
    private TeamPollCount teamPollCountB;

    public MatchPoll(int matchId, int teamAId, long countA, int teamBId, long countB) {
        this.teamPollCountA = new TeamPollCount(teamAId, countA);
        this.teamPollCountB = new TeamPollCount(teamBId, countB);
        this.matchId = matchId;
    }
}

@Data
class TeamPollCount {
    private int teamId;
    private long count;

    public TeamPollCount(int teamId, long count) {
        this.teamId = teamId;
        this.count = count;
    }
}
