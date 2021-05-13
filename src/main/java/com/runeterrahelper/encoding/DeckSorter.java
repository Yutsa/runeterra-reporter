package com.runeterrahelper.encoding;

import java.util.List;

import com.runeterrahelper.cards.Card;
import com.runeterrahelper.decks.Deck;

class DeckSorter {

  public SortedDeck sort(final Deck deck) {
    CardsGroupedByCopies threeOfs = sortCardsGroupedByCopies(deck.threeOfs());
    CardsGroupedByCopies twoOfs = sortCardsGroupedByCopies(deck.twoOfs());
    CardsGroupedByCopies oneOfs = sortCardsGroupedByCopies(deck.oneOfs());
    return new SortedDeck(threeOfs, twoOfs, oneOfs);
  }

  private CardsGroupedByCopies sortCardsGroupedByCopies(final List<Card> cards) {
    CardsGroupedByCopies cardsGroupedByCopies = new CardsGroupedByCopies();
    cards.forEach(cardsGroupedByCopies::add);
    return cardsGroupedByCopies;
  }
}
