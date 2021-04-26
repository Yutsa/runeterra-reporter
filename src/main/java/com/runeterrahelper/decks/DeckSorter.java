package com.runeterrahelper.decks;

import java.util.*;
import java.util.function.Function;
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
    Map<SetRegionPair, List<Card>> setRegionGroup = new HashMap<>();
    for (final Card card : cards) {
      setRegionGroup.computeIfAbsent(SetRegionPair.fromCard(card), k -> new ArrayList<>()).add(card);
    }
    return setRegionGroup.values().stream()
                    .sorted(compareSetRegionGroups())
                    .flatMap(computeCodeFromGroup())
                    .collect(Collectors.joining(","));
  }

  private Function<List<Card>, Stream<? extends String>> computeCodeFromGroup() {
    return cardsInGroup -> cardsInGroup.stream()
                                       .sorted(Comparator.comparing(Card::getCode))
                                       .map(Card::getCode);
  }

  private Comparator<List<Card>> compareSetRegionGroups() {
    return Comparator.comparing(List<Card>::size)
                     .thenComparing(cards -> cards.get(0).getCode());
  }

  private static class SetRegionPair {
    private final ReleaseSet set;
    private final Region region;

    public SetRegionPair(final ReleaseSet set, final Region region) {
      this.set = set;
      this.region = region;
    }

    public ReleaseSet getSet() {
      return set;
    }

    public Region getRegion() {
      return region;
    }

    public static SetRegionPair fromCard(Card card) {
      return new SetRegionPair(card.getReleaseSet(), card.getRegion());
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      final SetRegionPair that = (SetRegionPair) o;
      return set == that.set && region == that.region;
    }

    @Override
    public int hashCode() {
      return Objects.hash(set, region);
    }
  }
}
