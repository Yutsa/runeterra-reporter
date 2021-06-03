import com.runeterrahelper.archetypes.ArchetypeCompatibilityChecker;
import com.runeterrahelper.carddatamongodb.MongoCardRepository;
import com.runeterrahelper.meta.outputs.CSVMetaReportWriter;
import com.runeterrahelper.meta.processors.ArchetypeStatGenerator;
import com.runeterrahelper.meta.processors.MetaReporter;
import com.runeterrahelper.meta.processors.model.MetaReport;
import com.runeterrahelper.mobalytics.datasource.MobalyticsDataSource;
import org.junit.jupiter.api.Test;

public class TestGeneratingCSVFromMobalytics {
    @Test
    void name() {
        MetaReporter metaReporter = new MetaReporter(new MobalyticsDataSource(new MongoCardRepository("runeterra")), new ArchetypeStatGenerator(new ArchetypeCompatibilityChecker()));
        MetaReport metaReport = metaReporter.generateReport();
        new CSVMetaReportWriter("/home/edouard/Téléchargements/report-plat-plus.csv").writeReport(metaReport);
    }
}
