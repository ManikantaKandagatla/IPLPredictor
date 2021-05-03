package com.iplpredictor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@Table
@ToString
public class Match implements Serializable {
    public int id;
    public int oppositionA;
    public int oppositionB;
    public int dayNumber;
    public int winnerId;
    public boolean endResultAvailable;
    public boolean matchOver;

    public Match(Match match) {
        this.id = match.id;
        this.oppositionA = match.oppositionA;
        this.oppositionB = match.oppositionB;
        this.matchOver = match.matchOver;
        this.endResultAvailable = match.endResultAvailable;
        this.winnerId = match.winnerId;
    }

//    public Match(int id, int oppositionA, int oppositionB, boolean matchOver, boolean endResultAvailable, int winnerId) {
//        this.id = id;
//        this.oppositionA = oppositionA;
//        this.oppositionB = oppositionB;
//        this.matchOver = matchOver;
//        this.endResultAvailable = endResultAvailable;
//        this.winnerId = winnerId;
//    }

    public Match()
    {

    }
}
