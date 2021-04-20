package com.runeterrahelper.cards;

import java.util.Arrays;

public enum Region {
    DEMACIA("DE"),
    FRELJORD("FR"),
    IONIA("IO"),
    NOXUS("NX"),
    PILTOVER_AND_ZAUN("PZ"),
    SHADOW_ISLES("SI"),
    BILGEWATER("BW"),
    MOUNT_TARGON("MT"),
    SHURIMA("SH");

    private final String regionCode;

    Region(String regionCode) {
        this.regionCode = regionCode;
    }

    public static Region fromString(final String regionCode) {
        return Arrays.stream(values())
              .filter(region -> region.getRegionCode().equals(regionCode))
              .findFirst()
              .orElse(DEMACIA);
    }

    public String getRegionCode() {
        return regionCode;
    }
}
