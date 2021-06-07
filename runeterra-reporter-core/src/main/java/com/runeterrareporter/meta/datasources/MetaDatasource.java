package com.runeterrareporter.meta.datasources;

import java.util.Set;

import com.runeterrareporter.meta.processors.model.DeckMetaStat;

public interface MetaDatasource {

  Set<DeckMetaStat> retrieveDecks();
}