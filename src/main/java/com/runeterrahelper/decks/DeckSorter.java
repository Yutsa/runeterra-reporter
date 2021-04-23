package com.runeterrahelper.decks;

import java.util.*;
import java.util.stream.*;

import com.runeterrahelper.cards.*;

public class DeckSorter {

  public String sort(final Deck deck) {
    String threeOfs = sortCardsGroupedByCopies(deck.threeOfs());
    String twoOfs = sortCardsGroupedByCopies(deck.twoOfs());
    String oneOfs = sortCardsGroupedByCopies(deck.oneOfs());
    return Stream.of(threeOfs, twoOfs, oneOfs)
                 .filter(group -> !group.isEmpty())
                 .collect(Collectors.joining(","));
  }

  private String sortCardsGroupedByCopies(final List<Card> cards) {
    return cards.stream()
                .map(Card::getCode)
                .collect(Collectors.joining(","));
  }
}
