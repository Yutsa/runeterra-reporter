package com.runeterrahelper.cards;

public class Card {
    private final CardReleaseSet releaseSet;
    private final Region region;

    public Card(CardReleaseSet releaseSet, Region region) {
        this.releaseSet = releaseSet;
        this.region = region;
    }

    public String getCode() {
        return releaseSet.getReleaseSetCode() + region.getRegionCode();
    }

    public Region getRegion() {
        return region;
    }
}
