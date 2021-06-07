package com.runeterrareporter.meta.processors;

import com.runeterrareporter.meta.datasources.MetaDatasource;
import com.runeterrareporter.meta.processors.model.MetaReport;

public class MetaReporter {

  private final MetaDatasource metaDataSource;
  private final ArchetypeStatGenerator archetypeStatGenerator;

  public MetaReporter(MetaDatasource metaDatasource, ArchetypeStatGenerator archetypeStatGenerator) {
    this.metaDataSource = metaDatasource;
    this.archetypeStatGenerator = archetypeStatGenerator;
  }

  public MetaReport generateReport() {
    return new MetaReport(archetypeStatGenerator.generateArchetypes(metaDataSource.retrieveDecks()));
  }

}
