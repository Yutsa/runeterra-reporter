package com.runeterrahelper.meta.datasources;

import com.runeterrahelper.meta.processors.model.Archetype;
import com.runeterrahelper.meta.processors.model.DeckMetaStat;

import java.util.Set;

public interface MetaDataSource {
    Set<Archetype> retrieveArchetypes();
    Set<DeckMetaStat> retrieveDecks();
}