package com.runeterrareporter.meta.outputs;

import com.runeterrareporter.archetypes.Archetype;
import com.runeterrareporter.cards.CardWithDataFactory;
import com.runeterrareporter.cards.repository.FakeCardRepository;
import com.runeterrareporter.decks.DeckWithData;
import com.runeterrareporter.decks.DeckWithDataFactory;
import com.runeterrareporter.meta.processors.model.ArchetypeStat;
import com.runeterrareporter.meta.processors.model.DeckMetaStat;
import com.runeterrareporter.meta.processors.model.MetaReport;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CSVMetaReportWriterStepDefs {
    private final DeckWithDataFactory deckWithDataFactory = new DeckWithDataFactory(new CardWithDataFactory(new FakeCardRepository()));
    private final CSVMetaReportWriter csvMetaReportWriter = new CSVMetaReportWriter(new StringWriter());
    private MetaReport metaReport;

    @Given("a meta report that contains the following archetypes:")
    public void the_meta_report_contains_the_following_archetypes(List<Map<String, String>> archetypes) {
        List<ArchetypeStat> archetypeStats = new ArrayList<>();
        for (Map<String, String> archetypeData : archetypes) {
            Archetype archetype = new Archetype(extractDecks(archetypeData));
            archetypeStats.add(new ArchetypeStat(archetype, extractWinrate(archetypeData), extractNumberOfGames(archetypeData)));
        }
        metaReport = new MetaReport(archetypeStats);
    }

    @Given("a meta report that an archetype with the following decks:")
    public void a_meta_report_that_an_archetype_with_the_following_decks(List<Map<String, String>> deckStats) {
        ArchetypeStat archetypeStat = new ArchetypeStat();
        for (Map<String, String> deckStat : deckStats) {
            DeckMetaStat deckMetaStat = new DeckMetaStat(deckWithDataFactory.fromCode(deckStat.get("deck")),
                    extractNumberOfGames(deckStat), extractWinrate(deckStat));
            archetypeStat.addDeckStats(deckMetaStat);
        }
        metaReport = new MetaReport(List.of(archetypeStat));
    }

    private double extractWinrate(Map<String, String> archetypeData) {
        return Double.parseDouble(archetypeData.get("winrate"));
    }

    private int extractNumberOfGames(Map<String, String> archetypeData) {
        return Integer.parseInt(archetypeData.get("numberOfGames"));
    }

    private List<DeckWithData> extractDecks(Map<String, String> archetype) {
        return Arrays.stream(archetype.get("decks").split(";"))
                .map(deckWithDataFactory::fromCode)
                .collect(Collectors.toList());
    }

    @When("the meta report is written in CSV")
    public void the_meta_report_is_written_in_csv() {
        csvMetaReportWriter.writeReport(metaReport);
    }

    @Then("the result should be:")
    public void the_result_should_be(String expectedCSV) {
        assertThat(csvMetaReportWriter.toString()).isEqualTo(expectedCSV);
    }

}
