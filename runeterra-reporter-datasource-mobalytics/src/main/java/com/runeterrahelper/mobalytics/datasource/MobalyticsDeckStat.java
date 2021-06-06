package com.runeterrahelper.mobalytics.datasource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.runeterrahelper.meta.processors.model.DeckMetaStat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MobalyticsDeckStat {
    private String cardsCode;
    private int matchesCollected;
    private int wins;
    private int losses;
    private double playRate;

    public String getCardsCode() {
        return cardsCode;
    }

    public int getMatchesCollected() {
        return matchesCollected;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public double getPlayRate() {
        return playRate;
    }

    @Override
    public String toString() {
        return "MobalyticsDeckStat{" +
                "cardsCode='" + cardsCode + '\'' +
                ", matchesCollected='" + matchesCollected + '\'' +
                ", wins=" + wins +
                ", losses=" + losses +
                ", playRate=" + playRate +
                '}';
    }
}
