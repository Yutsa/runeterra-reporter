package com.runeterrahelper.decks;

import java.util.*;

import com.runeterrahelper.cards.Card;

public class CardCopiesParser {

  public static List<CardCopies> parse(final String cards) {
    List<CardCopies> result = new ArrayList<>();
    String[] cardCopies = cards.split(",");
    for (final String cardCopy : cardCopies) {
      String[] split = cardCopy.split(":");
      result.add(new CardCopies(Integer.parseInt(split[0]), Card.fromCode(split[1])));
    }
    return result;
  }

  public static String marshall(final Deck sortedDeck) {
    return null;
  }
}
