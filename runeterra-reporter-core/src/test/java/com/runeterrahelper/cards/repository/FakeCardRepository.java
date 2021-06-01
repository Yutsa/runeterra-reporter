package com.runeterrahelper.cards.repository;

import com.runeterrahelper.cards.Card;
import com.runeterrahelper.cards.CardType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeCardRepository implements CardRepository {

    private final Map<String, CardWithData> cards = new HashMap<>();

    @Override
    public CardWithData getCardFromCode(String cardCode) {
        Card card = Card.fromCode(cardCode);
        return Optional.ofNullable(cards.get(cardCode))
                .orElse(new CardWithData(card.getReleaseSet(), card.getRegion(), card.getCardNumber(), CardType.SPELL, card.getCode()));
    }

    public void addCard(CardWithData cardWithData) {
        cards.put(cardWithData.getCode(), cardWithData);
    }
}
