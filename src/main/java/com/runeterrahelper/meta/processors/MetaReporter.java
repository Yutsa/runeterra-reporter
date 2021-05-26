package com.runeterrahelper.meta.processors;

import java.util.*;

import com.runeterrahelper.meta.datasources.MetaDataSource;
import com.runeterrahelper.meta.processors.model.*;

public class MetaReporter {

  private final MetaDataSource metaDataSource;

  public MetaReporter(MetaDataSource metaDataSource) {
    this.metaDataSource = metaDataSource;
  }

  public MetaReport generateReport() {
    Set<DeckMetaStat> deckMetaStats = metaDataSource.retrieveDecks();
    var archetype = new Archetype("foobar");

    deckMetaStats.forEach(archetype::addDeckStats);
    return new MetaReport(List.of(archetype));
  }
}
