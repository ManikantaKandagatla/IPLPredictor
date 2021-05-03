package com.iplpredictor.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PredictionResult implements Serializable {
    Match[] predictedMatchResults;
    PointsTable pointsTable;
    TeamStat[] predictedTeamStats;

    public PredictionResult(Match[] predictedMatchResults, PointsTable predictedPointsTable) {
        this.pointsTable = predictedPointsTable;
        this.predictedMatchResults = predictedMatchResults;
        //this.predictedTeamStats = predictedTeamStats;
    }

    public PredictionResult() {

    }
}
