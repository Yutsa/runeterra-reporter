package com.runeterrahelper.cards.repository;

import com.runeterrahelper.cards.Card;
import com.runeterrahelper.cards.CardType;
import com.runeterrahelper.cards.Region;
import com.runeterrahelper.cards.ReleaseSet;

import java.util.Objects;

import static com.runeterrahelper.cards.CardType.CHAMPION;

public class CardWithData extends Card {
    private final CardType cardType;

    public CardWithData(ReleaseSet releaseSet, Region region, int cardNumber, CardType cardType) {
        super(releaseSet, region, cardNumber);
        this.cardType = cardType;
    }

    public boolean isChampion() {
        return Objects.equals(CHAMPION, cardType);
    }
}
