package com.runeterrahelper.cards;

public class RegionParser {
    public static Region parse(String regionName) {
        if (regionName.equals("Demacia")) {
            return Region.DEMACIA;
        } else if (regionName.equals("Freljord")) {
            return Region.FRELJORD;
        }
        return Region.DEMACIA;
    }
}
