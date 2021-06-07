package com.runeterrareporter.cards.repository;

import com.runeterrareporter.cards.Card;
import com.runeterrareporter.cards.CardType;
import com.runeterrareporter.cards.Region;
import com.runeterrareporter.cards.ReleaseSet;

import java.util.Objects;

import static com.runeterrareporter.cards.CardType.CHAMPION;

public class CardWithData extends Card {
    private final CardType cardType;
    private final String name;

    public CardWithData(ReleaseSet releaseSet, Region region, int cardNumber, CardType cardType, String name) {
        super(releaseSet, region, cardNumber);
        this.cardType = cardType;
        this.name = name;
    }

    public CardWithData(Card card, CardType cardType, String name) {
        super(card.getReleaseSet(), card.getRegion(), card.getCardNumber());
        this.cardType = cardType;
        this.name = name;
    }

    public boolean isChampion() {
        return Objects.equals(CHAMPION, cardType);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CardWithData{" +
               "cardType=" + cardType +
               ", name='" + name + '\'' +
               '}';
    }
}
