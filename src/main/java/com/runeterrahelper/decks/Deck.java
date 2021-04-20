package com.runeterrahelper.decks;

import java.util.*;

public class Deck {
  private final List<CardCopies> cards = new ArrayList<>();

  public void addCardCopies(final List<CardCopies> cardCopies) {
    cards.addAll(cardCopies);
  }
}
