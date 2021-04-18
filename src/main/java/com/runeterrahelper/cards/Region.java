package com.runeterrahelper.cards;

public enum Region {
    DEMACIA("DE");

    private final String regionCode;

    Region(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionCode() {
        return regionCode;
    }
}
