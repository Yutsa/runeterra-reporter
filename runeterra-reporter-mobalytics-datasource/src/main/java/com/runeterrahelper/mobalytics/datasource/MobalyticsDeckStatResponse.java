package com.runeterrahelper.mobalytics.datasource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
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
