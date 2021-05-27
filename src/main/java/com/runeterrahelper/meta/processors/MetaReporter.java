package com.runeterrahelper.meta.processors;

import com.runeterrahelper.meta.datasources.MetaDatasource;
import com.runeterrahelper.meta.processors.model.MetaReport;

public class MetaReporter {

  private final MetaDatasource metaDataSource;
  private final ArchetypeGenerator archetypeGenerator;

  public MetaReporter(MetaDatasource metaDatasource, ArchetypeGenerator archetypeGenerator) {
    this.metaDataSource = metaDatasource;
    this.archetypeGenerator = archetypeGenerator;
  }

  public MetaReport generateReport() {
    return new MetaReport(archetypeGenerator.generateArchetypes(metaDataSource.retrieveDecks()));
  }

}
