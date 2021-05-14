package com.runeterrahelper.decks;

import com.runeterrahelper.cards.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

  private final List<CardCopies> cards = new ArrayList<>();

  public void addCard(final List<CardCopies> cardCopies) {
    cards.addAll(cardCopies);
  }
  
  public void addCard(final CardCopies... cardCopies) {
    cards.addAll(Arrays.asList(cardCopies));
  }

  public List<CardCopies> getCards() {
    return cards;
  }

  public List<Card> oneOfs() {
    return retrieveCardsInXCopies(1);
  }

  public List<Card> twoOfs() {
    return retrieveCardsInXCopies(2);
  }

  public List<Card> threeOfs() {
    return retrieveCardsInXCopies(3);
  }

  private List<Card> retrieveCardsInXCopies(int copies) {
    return cards.stream()
                .filter(cardCopies -> cardCopies.getNumberOfCopies() == copies)
                .map(CardCopies::getCard)
                .collect(Collectors.toList());
  }
}
