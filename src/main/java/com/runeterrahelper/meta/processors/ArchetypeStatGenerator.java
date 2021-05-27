package com.runeterrahelper.meta.processors;

import java.util.*;

import com.runeterrahelper.archetypes.*;
import com.runeterrahelper.meta.processors.model.*;

class ArchetypeStatGenerator {

  private final ArchetypeCompatibilityChecker archetypeCompatibilityChecker;
  List<ArchetypeStat> archetypeStats = new ArrayList<>();

  public ArchetypeStatGenerator(final ArchetypeCompatibilityChecker archetypeCompatibilityChecker) {
    this.archetypeCompatibilityChecker = archetypeCompatibilityChecker;
  }

  List<ArchetypeStat> generateArchetypes(final Set<DeckMetaStat> deckMetaStats) {
    for (final DeckMetaStat deckMetaStat : deckMetaStats) {
      archetypeStats.stream()
                    .filter(archetypeStat -> archetypeCompatibilityChecker.isArchetypeCompatibleWithDeck(archetypeStat.getArchetype(), deckMetaStat.getDeck()))
                    .findFirst()
                    .orElseGet(this::createNewArchetypeStatistics)
                    .addDeckStats(deckMetaStat);
    }
    return archetypeStats;
  }

  private ArchetypeStat createNewArchetypeStatistics() {
    var archetype = new ArchetypeStat(new Archetype("foobar"));
    archetypeStats.add(archetype);
    return archetype;
  }
}