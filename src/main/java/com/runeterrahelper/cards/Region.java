package com.runeterrahelper.cards;

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

    public String getRegionCode() {
        return regionCode;
    }
}
