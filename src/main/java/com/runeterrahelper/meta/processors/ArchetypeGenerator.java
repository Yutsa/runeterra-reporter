package com.runeterrahelper.meta.processors;

import java.util.*;

import com.runeterrahelper.meta.processors.model.*;

public class ArchetypeGenerator {

  List<Archetype> archetypes = new ArrayList<>();

  List<Archetype> generateArchetypes(final Set<DeckMetaStat> deckMetaStats) {
    for (final DeckMetaStat deckMetaStat : deckMetaStats) {
      archetypes.stream()
                .filter(archetype -> archetype.isComptabileWithDeck(deckMetaStat.getDeck()))
                .findFirst()
                .orElseGet(this::createNewArchetype)
                .addDeckStats(deckMetaStat);
    }
    return archetypes;
  }

  private Archetype createNewArchetype() {
    var archetype = new Archetype("foobar");
    archetypes.add(archetype);
    return archetype;
  }
}