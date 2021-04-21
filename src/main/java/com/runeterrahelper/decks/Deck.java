package com.runeterrahelper.decks;

import java.util.*;
import java.util.stream.Collectors;

public class Deck {

  private final List<CardCopies> cards = new ArrayList<>();

  public void addCardCopies(final List<CardCopies> cardCopies) {
    cards.addAll(cardCopies);
  }

  public List<CardCopies> getCards() {
    return cards;
  }

  public List<CardCopies> oneOfs() {
    return retrieveCardsInXCopies(1);
  }

  public List<CardCopies> twoOfs() {
    return retrieveCardsInXCopies(2);
  }

  public List<CardCopies> threeOfs() {
    return retrieveCardsInXCopies(3);
  }

  private List<CardCopies> retrieveCardsInXCopies(int copies) {
    return cards.stream()
                .filter(cardCopies -> cardCopies.getNumberOfCopies() == copies)
                .collect(Collectors.toList());
  }
}
