package com.runeterrareporter.mobalytics.datasource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MobalyticsDeckStatResponse {
    private String hasNext;
    private List<MobalyticsDeckStat> decksStats;

    public String getHasNext() {
        return hasNext;
    }

    public List<MobalyticsDeckStat> getDecksStats() {
        return decksStats;
    }
}
