package com.runeterrahelper.decks;

import java.util.Objects;

import com.runeterrahelper.cards.Card;

public class CardCopies {

  private final int numberOfCopies;
  private final Card card;

  public CardCopies(final int numberOfCopies, final Card card) {
    this.numberOfCopies = numberOfCopies;
    this.card = card;
  }

  public int getNumberOfCopies() {
    return numberOfCopies;
  }

  public Card getCard() {
    return card;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final CardCopies that = (CardCopies) o;
    return numberOfCopies == that.numberOfCopies && card.equals(that.card);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numberOfCopies, card);
  }
}
