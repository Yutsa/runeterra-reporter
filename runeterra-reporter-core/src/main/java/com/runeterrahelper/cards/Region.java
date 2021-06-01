package com.runeterrahelper.cards;

import java.util.Arrays;

public enum Region {
    DEMACIA("DE", 0, "Demacia"),
    FRELJORD("FR", 1, "Freljord"),
    IONIA("IO", 2, "Ionia"),
    NOXUS("NX", 3, "Noxus"),
    PILTOVER_AND_ZAUN("PZ", 4, "Piltover & Zaun"),
    SHADOW_ISLES("SI", 5, "Shadow Isles"),
    BILGEWATER("BW", 6, "Bilgewater"),
    MOUNT_TARGON("MT", 9, "Targon"),
    SHURIMA("SH", 7, "Shurima");

    private final String regionCode;
    private final int id;
    private final String name;

    Region(String regionCode, int id, String name) {
        this.regionCode = regionCode;
        this.id = id;
        this.name = name;
    }

    public static Region fromString(final String regionCode) {
        return Arrays.stream(values())
                .filter(region -> region.getRegionCode().equals(regionCode))
                .findFirst()
                .orElseThrow();
    }

    public static Region fromId(int id) {
        return Arrays.stream(values())
                .filter(region -> region.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public String getRegionCode() {
        return regionCode;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
