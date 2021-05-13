package com.runeterrahelper.cards;

import java.util.Arrays;

public enum Region {
    DEMACIA("DE", 0),
    FRELJORD("FR", 1),
    IONIA("IO", 2),
    NOXUS("NX", 3),
    PILTOVER_AND_ZAUN("PZ", 4),
    SHADOW_ISLES("SI", 5),
    BILGEWATER("BW", 6),
    MOUNT_TARGON("MT", 9),
    SHURIMA("SH", 7);

    private final String regionCode;
    private final int id;

    Region(String regionCode, int id) {
        this.regionCode = regionCode;
        this.id = id;
    }

    public static Region fromString(final String regionCode) {
        return Arrays.stream(values())
                .filter(region -> region.getRegionCode().equals(regionCode))
                .findFirst()
                .orElseThrow();
    }

    public String getRegionCode() {
        return regionCode;
    }

    public int getId() {
        return id;
    }
}
