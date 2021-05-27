package com.runeterrahelper.archetypes;

import java.util.*;

import com.runeterrahelper.decks.Deck;

public class Archetype {

  private final String name;
  private final Set<Deck> decks = new HashSet<>();

  public Archetype(final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Set<Deck> getDecks() {
    return decks;
  }

  public void addToDecks(Deck... decks) {
    this.decks.addAll(Arrays.asList(decks));
  }
}
