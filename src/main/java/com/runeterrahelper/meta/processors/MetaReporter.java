package com.runeterrahelper.meta.processors;

import com.runeterrahelper.meta.datasources.MetaDataSource;
import com.runeterrahelper.meta.processors.model.Archetype;
import com.runeterrahelper.meta.processors.model.DeckMetaStat;
import com.runeterrahelper.meta.processors.model.MetaReport;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MetaReporter {
    private final MetaDataSource metaDataSource;

    public MetaReporter(MetaDataSource metaDataSource) {
        this.metaDataSource = metaDataSource;
    }

    public MetaReport generateReport() {
        Set<DeckMetaStat> deckMetaStats = metaDataSource.retrieveDecks();
        List<Archetype> archetypes = deckMetaStats.stream()
                .map(deckStat -> createArchtype(deckStat))
                .collect(Collectors.toList());
        return new MetaReport(archetypes);
    }

    private Archetype createArchtype(DeckMetaStat deckStat) {
        Archetype archetype = new Archetype("foo", null, deckStat.getWinrate(), deckStat.getNumberOfGamesPlayed());
        archetype.addToDecks(deckStat.getDeck());
        return archetype;
    }
}
