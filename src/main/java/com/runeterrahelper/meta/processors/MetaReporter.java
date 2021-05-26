package com.runeterrahelper.meta.processors;

import java.util.*;

import com.runeterrahelper.meta.datasources.MetaDataSource;
import com.runeterrahelper.meta.processors.model.*;

public class MetaReporter {

  private final MetaDataSource metaDataSource;
  private final ArchetypeGenerator archetypeGenerator = new ArchetypeGenerator();

  public MetaReporter(MetaDataSource metaDataSource) {
    this.metaDataSource = metaDataSource;
  }

  public MetaReport generateReport() {
    return new MetaReport(archetypeGenerator.generateArchetypes(metaDataSource.retrieveDecks()));
  }

}
