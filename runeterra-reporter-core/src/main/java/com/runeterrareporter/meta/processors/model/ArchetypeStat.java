package com.runeterrareporter.meta.processors.model;

import java.util.*;
import java.util.stream.Collectors;

import com.runeterrareporter.archetypes.Archetype;
import com.runeterrareporter.decks.Deck;
import com.runeterrareporter.utils.MathUtils;

public class ArchetypeStat implements Comparable<ArchetypeStat> {

  private final Archetype archetype;
  private final List<DeckMetaStat> deckMetaStats = new ArrayList<>();
  private double winrate;
  private int numberOfMatches;

  /**
   * Creates an {@link ArchetypeStat} from an {@link Archetype} with a given winrate and number of maches.
   * Using this constructor prevents getting detailed winrate and number of matches for each deck that was already in
   * the {@link Archetype archetype} instance.
   */
  public ArchetypeStat(Archetype archetype, double winrate, int numberOfMatches) {
    this.archetype = archetype;
    this.winrate = winrate;
    this.numberOfMatches = numberOfMatches;
    archetype.getDecks().forEach(deck -> deckMetaStats.add(new DeckMetaStat(deck, 0, 0)));
  }

  /**
   * Creates an empty {@link ArchetypeStat} that must be filled with {@link ArchetypeStat#addDeckStats(DeckMetaStat)}.
   */
  public ArchetypeStat() {
    this.archetype = new Archetype();
    winrate = 0;
    numberOfMatches = 0;
  }

  public String getName() {
    return archetype.getName();
  }

  public double getWinrate() {
    return winrate;
  }

  public int getNumberOfMatches() {
    return numberOfMatches;
  }

  public List<Deck> getDecks() {
    return deckMetaStats
      .stream()
      .sorted()
      .map(DeckMetaStat::getDeck)
      .map(Deck.class::cast)
      .collect(Collectors.toList());
  }

  public Archetype getArchetype() {
    return archetype;
  }

  public void addDeckStats(final DeckMetaStat deckMetaStat) {
    deckMetaStats.add(deckMetaStat);
    archetype.addToDecks(deckMetaStat.getDeck());
    double currentPonderation = (double) numberOfMatches / (numberOfMatches + deckMetaStat.getNumberOfGamesPlayed());
    double newDeckPonderation = 1 - currentPonderation;
    this.winrate = MathUtils.roundToTwoDigits(
      winrate * currentPonderation + deckMetaStat.getWinrate() * newDeckPonderation);
    numberOfMatches += deckMetaStat.getNumberOfGamesPlayed();
  }

  @Override
  public String toString() {
    return getName() + " [ " + numberOfMatches + " games / "
           + winrate + "% winrate / "
           + "decks : "
           + getDecks().stream().map(Deck::toDeckCode).collect(Collectors.joining(", "))
           + " ]";
  }

  @Override
  public int compareTo(ArchetypeStat archetypeStat) {
    return archetypeStat.getNumberOfMatches() - numberOfMatches;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final ArchetypeStat that = (ArchetypeStat) o;
    return Double.compare(that.winrate, winrate) == 0 && numberOfMatches == that.numberOfMatches &&
           archetype.equals(that.archetype) && deckMetaStats.equals(that.deckMetaStats);
  }

  @Override
  public int hashCode() {
    return Objects.hash(archetype, deckMetaStats, winrate, numberOfMatches);
  }
}
