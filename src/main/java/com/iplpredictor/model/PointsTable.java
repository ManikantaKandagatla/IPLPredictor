package com.iplpredictor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.sql.In;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Data
@ToString
public class PointsTable implements Serializable {
    TeamStat[] teamStats;

    public PointsTable() {
        teamStats = new TeamStat[8];
    }

    public PointsTable(TeamStat[] teamStats) {
        this.teamStats = teamStats;
    }

    public boolean canHomeTeamReachPlayoffs(int hometeam) {
        //Write a pair of team id and points, sort and send accordingly
        //Unable to import javafx
        log.info(Arrays.toString(this.teamStats));
        Arrays.sort(teamStats, new Comparator<TeamStat>() {
            @Override
            public int compare(TeamStat o1, TeamStat o2) {
                int c = 0;
                if( o2.points != o1.points)
                    c = o2.points - o1.points;
                else {
                    Map<Integer, Integer> map = getPriorityMap(hometeam);
                    c = map.get(o2.getId()) - map.get(o1.getId());
                }
                return c;
            }
        });

        int i = 0;
        for(; i < 8; i++) {
            if(teamStats[i].getId() == hometeam) {
                break;
            }
        }
        return i <= 3;
    }

    private Map<Integer, Integer> getPriorityMap(int homeTeamId)
    {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(homeTeamId, 9);
        for(int i = 0 ; i< 8; i++) {
            if(i != homeTeamId) {
                map.put(i, i);
            }
        }
        return map;
     }
}

