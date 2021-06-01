package com.runeterrahelper.meta.datasources;

import java.util.Set;

import com.runeterrahelper.meta.processors.model.DeckMetaStat;

public interface MetaDatasource {

  Set<DeckMetaStat> retrieveDecks();
}