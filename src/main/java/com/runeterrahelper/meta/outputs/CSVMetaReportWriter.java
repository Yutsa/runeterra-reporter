package com.runeterrahelper.meta.outputs;

import com.runeterrahelper.decks.Deck;
import com.runeterrahelper.meta.processors.model.ArchetypeStat;
import com.runeterrahelper.meta.processors.model.MetaReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class CSVMetaReportWriter implements MetaReportWriter {
    private final Logger logger = LoggerFactory.getLogger(CSVMetaReportWriter.class);
    private Writer writer;

    public CSVMetaReportWriter(String csvPath) {
        try {
            writer = new PrintWriter(csvPath);
        } catch (FileNotFoundException e) {
            logger.error("Error when trying to write the csv report to file {}", csvPath);
            logger.debug("", e);
        }
    }

    public CSVMetaReportWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void writeReport(MetaReport report) {
        try {
            writeHeaders();
            for (ArchetypeStat archetype : report.getArchetypes()) {
                writeArchetypeData(archetype);
            }
        } catch (IOException e) {
            logger.error("Error when trying to write the csv report to file.");
            logger.debug("", e);
        }
    }

    private void writeArchetypeData(ArchetypeStat archetype) throws IOException {
        StringJoiner sj = new StringJoiner(",");
        sj.add("\n" + archetype.getName())
                .add(String.valueOf(archetype.getNumberOfMatches()))
                .add(String.valueOf(archetype.getWinrate()))
                .add(formatDecksForCSV(archetype));
        writer.write(sj.toString());
    }

    private String formatDecksForCSV(ArchetypeStat archetype) {
        return archetype.getDecks().stream()
                .map(Deck::toDeckCode)
                .collect(Collectors.joining(";"));
    }

    private void writeHeaders() throws IOException {
        writer.write("archetype name,number of games played,winrate,deck codes");
    }

    @Override
    public String toString() {
        return writer.toString();
    }
}
