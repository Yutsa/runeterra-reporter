package com.runeterrahelper.cards;

public class CardFactory {
    private CardReleaseSet releaseSet = CardReleaseSet.FOUNDATION;
    private Region region = Region.DEMACIA;

    public static CardFactory createCard() {
        return new CardFactory();
    }

    public CardFactory withReleaseSet(CardReleaseSet cardReleaseSet) {
        this.releaseSet = cardReleaseSet;
        return this;
    }

    public CardFactory withRegion(Region region) {
        this.region = region;
        return this;
    }
    
    public Card build() {
        return new Card(releaseSet, region);
    }
}
