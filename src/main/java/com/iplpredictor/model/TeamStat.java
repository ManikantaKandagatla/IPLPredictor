package com.iplpredictor.model;

import java.io.Serializable;

public class TeamStat implements Serializable {
    private final int id;
    public int noOfMatches;
    public int noOfWins;
    public int noOfDefeats;
    public int noOfMatchesWithNR;
    public int points;
    public float netRunRate;
    public TeamStat teamStat;

    public TeamStat() {
        this.id = -1;
    }

    public TeamStat(int id) {
        this.id = id;
        noOfMatches = 0;
        noOfWins = 0;
        noOfDefeats = 0;
        noOfMatchesWithNR = 0;
        points = 0;
        netRunRate = 0;
    }

    public TeamStat(TeamStat teamStat) {
        this.teamStat = teamStat;
        id = teamStat.id;
    }

    public void setTeamStat(TeamStat teamStat) {
        this.teamStat = teamStat;
    }

    public void reset() {
        noOfMatches = teamStat.noOfMatches;
        noOfWins = teamStat.noOfWins;
        noOfDefeats = teamStat.noOfDefeats;
        noOfMatchesWithNR = teamStat.noOfMatchesWithNR;
        points = teamStat.points;
        netRunRate = teamStat.netRunRate;
    }

    public int getId() {
        return id;
    }

    public int getNoOfMatches() {
        return noOfMatches;
    }

    public void setNoOfMatches(int noOfMatches) {
        this.noOfMatches = noOfMatches;
    }

    public int getNoOfWins() {
        return noOfWins;
    }

    public void setNoOfWins(int noOfWins) {
        this.noOfWins = noOfWins;
    }

    public int getNoOfDefeats() {
        return noOfDefeats;
    }

    public void setNoOfDefeats(int noOfDefeats) {
        this.noOfDefeats = noOfDefeats;
    }

    public int getNoOfMatchesWithNR() {
        return noOfMatchesWithNR;
    }

    public void setNoOfMatchesWithNR(int noOfMatchesWithNR) {
        this.noOfMatchesWithNR = noOfMatchesWithNR;
    }

    public int getPoints() {
        return points;
    }

    public void updatePoints() {
        this.points = 2 * noOfWins + noOfMatchesWithNR;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public float getNetRunRate() {
        return netRunRate;
    }

    public void setNetRunRate(float netRunRate) {
        this.netRunRate = netRunRate;
    }

    @Override
    public String toString() {
        updatePoints();
        return "TeamStat{" +
                "id=" + id +
                ", noOfMatches=" + noOfMatches +
                ", noOfWins=" + noOfWins +
                ", noOfDefeats=" + noOfDefeats +
                ", noOfMatchesWithNR=" + noOfMatchesWithNR +
                ", points=" + points +
                ", netRunRate=" + netRunRate +
                '}';
    }
}
