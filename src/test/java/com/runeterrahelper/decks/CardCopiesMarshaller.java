package com.runeterrahelper.decks;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.runeterrahelper.cards.Card;

public class CardCopiesMarshaller {

  public static List<CardCopies> unmarshall(final String cards) {
    return Arrays.stream(cards.split(","))
                 .map(cardCopies -> cardCopies.split(":"))
                 .map(createCardCopiesFromSplittedString())
                 .collect(Collectors.toList());
  }

  private static Function<String[], CardCopies> createCardCopiesFromSplittedString() {
    return cardCopyArray -> new CardCopies(Integer.parseInt(cardCopyArray[0]), Card.fromCode(cardCopyArray[1]));
  }

  public static String marshall(final List<CardCopies> listOfCardCopies) {
    return listOfCardCopies.stream()
      .map(CardCopiesMarshaller::marshall)
      .collect(Collectors.joining(","));
  }

  private static String marshall(final CardCopies cardCopies) {
    return "" + cardCopies.getNumberOfCopies() + ":" + cardCopies.cardCode();
  }
}
