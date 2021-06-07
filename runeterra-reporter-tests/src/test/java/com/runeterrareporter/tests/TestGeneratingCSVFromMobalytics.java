package com.runeterrareporter.tests;

import com.runeterrareporter.archetypes.ArchetypeCompatibilityChecker;

import com.runeterrareporter.cardrepository.mongodb.MongoCardRepository;
import com.runeterrareporter.meta.outputs.CSVMetaReportWriter;
import com.runeterrareporter.meta.processors.ArchetypeStatGenerator;
import com.runeterrareporter.meta.processors.MetaReporter;
import com.runeterrareporter.meta.processors.model.MetaReport;
import com.runeterrareporter.mobalytics.datasource.MobalyticsDataSource;

import org.junit.jupiter.api.Test;

public class TestGeneratingCSVFromMobalytics {
    @Test
    void name() {
        MetaReporter metaReporter = new MetaReporter(new MobalyticsDataSource(new MongoCardRepository("runeterra")), new ArchetypeStatGenerator(new ArchetypeCompatibilityChecker()));
        MetaReport metaReport = metaReporter.generateReport();
        new CSVMetaReportWriter("/home/edouard/Téléchargements/report-plat-plus.csv").writeReport(metaReport);
    }
}
