package com.runeterrareporter.meta.processors;

import com.runeterrareporter.archetypes.ArchetypeCompatibilityChecker;
import com.runeterrareporter.meta.processors.model.ArchetypeStat;
import com.runeterrareporter.meta.processors.model.DeckMetaStat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ArchetypeStatGenerator {

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
    var archetypeStat = new ArchetypeStat();
    archetypeStats.add(archetypeStat);
    return archetypeStat;
  }
}