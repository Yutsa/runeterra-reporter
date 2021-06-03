package com.runeterrahelper.cards.repository;

import java.util.*;

import com.runeterrahelper.cards.*;

public class FakeCardRepository implements CardRepository {

  private final Map<String, CardWithData> cards = new HashMap<>();

  @Override
  public Optional<CardWithData> getCardWithDataFromCard(Card card) {
    CardWithData res = cards.get(card.getCode());
    if (res == null) {
      res = new CardWithData(card.getReleaseSet(), card.getRegion(), card.getCardNumber(), CardType.SPELL, card.getCode());
    }
    return Optional.of(res);
  }

  public void addCard(CardWithData cardWithData) {
    cards.put(cardWithData.getCode(), cardWithData);
  }
}
