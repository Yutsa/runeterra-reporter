package com.runeterrahelper.decks;

import java.util.*;

import com.runeterrahelper.cards.Card;

public class Deck {
  private final List<CardCopies> cards = new ArrayList<>();

  public void addCardCopies(final List<CardCopies> cardCopies) {
    cards.addAll(cardCopies);
  }

  public List<CardCopies> getCards() {
    return cards;
  }
}
