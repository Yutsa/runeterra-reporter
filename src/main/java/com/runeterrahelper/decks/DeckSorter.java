package com.runeterrahelper.decks;

import java.util.stream.Collectors;

public class DeckSorter {

  public String sort(final Deck deck) {
    return deck.getCards().stream()
      .map(CardCopies::cardCode)
      .collect(Collectors.joining(","));
  }
}
