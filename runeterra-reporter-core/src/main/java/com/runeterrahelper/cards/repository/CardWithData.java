package com.runeterrahelper.cards.repository;

import com.runeterrahelper.cards.Card;
import com.runeterrahelper.cards.CardType;
import com.runeterrahelper.cards.Region;
import com.runeterrahelper.cards.ReleaseSet;

import java.util.Objects;

import static com.runeterrahelper.cards.CardType.CHAMPION;

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
