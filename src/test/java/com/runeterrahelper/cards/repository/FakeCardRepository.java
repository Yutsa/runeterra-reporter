package com.runeterrahelper.cards.repository;

import com.runeterrahelper.cards.Card;
import com.runeterrahelper.cards.CardType;

public class FakeCardRepository implements CardRepository {
    @Override
    public CardWithData getCardFromCode(String cardCode) {
        Card card = Card.fromCode(cardCode);
        return new CardWithData(card.getReleaseSet(), card.getRegion(), card.getCardNumber(), CardType.CHAMPION, card.getCode());
    }
}
