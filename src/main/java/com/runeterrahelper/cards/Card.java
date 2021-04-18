package com.runeterrahelper.cards;

public class Card {
    private CardReleaseSet releaseSet;

    public Card(CardReleaseSet releaseSet) {
        this.releaseSet = releaseSet;
    }
    
    public String getCode() {
        return releaseSet.getReleaseSetCode();
    }
}
