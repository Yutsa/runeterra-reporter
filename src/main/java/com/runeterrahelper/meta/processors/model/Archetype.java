package com.runeterrahelper.meta.processors.model;

import java.util.*;
import java.util.stream.Collectors;

import com.runeterrahelper.cards.Region;
import com.runeterrahelper.decks.Deck;
import com.runeterrahelper.utils.MathUtils;

public class Archetype {

  private final String name;
  private final Set<Region> regions;
  private final Set<Deck> decks = new HashSet<>();
  private double winrate;
  private int numberOfMatches;

  public Archetype(String name, Set<Region> regions, double winrate, int numberOfMatches) {
    this.name = name;
    this.regions = regions;
    this.winrate = winrate;
    this.numberOfMatches = numberOfMatches;
  }

  public Archetype(String name) {
    this.name = name;
    regions = new HashSet<>();
    winrate = 0;
    numberOfMatches = 0;
  }

  public String getName() {
    return name;
  }

  public Set<Region> getRegions() {
    return regions;
  }

  public double getWinrate() {
    return winrate;
  }

  public int getNumberOfMatches() {
    return numberOfMatches;
  }

  public Set<Deck> getDecks() {
    return decks;
  }

  public void addToDecks(Deck... decks) {
    this.decks.addAll(Arrays.asList(decks));
  }

  public void addDeckStats(final DeckMetaStat deckMetaStat) {
    addToDecks(deckMetaStat.getDeck());
    double currentPonderation = (double) numberOfMatches / (numberOfMatches + deckMetaStat.getNumberOfGamesPlayed());
    double newDeckPonderation = 1 - currentPonderation;
    this.winrate = MathUtils.roundToTwoDigits(
      winrate * currentPonderation + deckMetaStat.getWinrate() * newDeckPonderation);
    numberOfMatches += deckMetaStat.getNumberOfGamesPlayed();
  }

  @Override
  public String toString() {
    return name + " [ " + numberOfMatches + " games / "
           + winrate + "% winrate / "
           + "decks : "
           + decks.stream().map(Deck::toDeckCode).collect(Collectors.joining(", "));
  }
}
