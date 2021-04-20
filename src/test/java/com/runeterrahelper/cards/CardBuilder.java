package com.runeterrahelper.cards;

public class CardBuilder {
    private CardReleaseSet releaseSet = CardReleaseSet.FOUNDATION;
    private Region region = Region.DEMACIA;
    private int cardNumber = 1;

    public static CardBuilder createCard() {
        return new CardBuilder();
    }

    public CardBuilder withReleaseSet(CardReleaseSet cardReleaseSet) {
        this.releaseSet = cardReleaseSet;
        return this;
    }

    public CardBuilder withRegion(Region region) {
        this.region = region;
        return this;
    }

    public CardBuilder withCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }
    
    public Card build() {
        return new Card(releaseSet, region, cardNumber);
    }
}
