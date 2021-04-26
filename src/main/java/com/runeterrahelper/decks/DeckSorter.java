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
    Map<ReleaseSet, List<Card>> setRegionGroup = new HashMap<>();
    for (final Card card : cards) {
      setRegionGroup.computeIfAbsent(card.getReleaseSet(), k -> new ArrayList<>()).add(card);
    }
    return setRegionGroup.values().stream()
                    .sorted(compareSetRegionGroups())
                    .flatMap(cardsInGroup -> cardsInGroup.stream().map(Card::getCode))
                    .collect(Collectors.joining(","));
  }

  private Comparator<List<Card>> compareSetRegionGroups() {
    return Comparator.comparing(List<Card>::size)
                     .thenComparing(cards -> cards.get(0).getCode());
  }
}
