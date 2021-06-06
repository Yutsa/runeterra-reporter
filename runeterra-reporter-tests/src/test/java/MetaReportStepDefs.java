import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.SoftAssertions;

import com.runeterrahelper.archetypes.ArchetypeCompatibilityChecker;
import com.runeterrahelper.cardrepository.mongodb.MongoCardRepository;
import com.runeterrahelper.cards.*;
import com.runeterrahelper.cards.repository.CardRepository;
import com.runeterrahelper.decks.*;
import com.runeterrahelper.meta.processors.*;
import com.runeterrahelper.meta.processors.model.*;
import io.cucumber.java.en.*;

public class MetaReportStepDefs {

    private FakeMetaDatasource metaDataSource;
    private MetaReport metaReport;

    @Given("a datasource")
    public void a_datasource() {
        CardRepository cardRepository = new MongoCardRepository("runeterra");
        metaDataSource = new FakeMetaDatasource(new DeckWithDataFactory(new CardWithDataFactory(cardRepository)));
    }

    @Given("the datasource contains the deck {string} with {int} games played and {int}% winrate")
    public void the_datasource_contains_the_deck_with_games_played_and_winrate(String deckCode, int numberOfGames, int winrate) {
        metaDataSource.addDeck(deckCode, numberOfGames, winrate);
    }

    @When("the meta report is generated")
    public void the_meta_report_is_generated() {
        metaReport = new MetaReporter(metaDataSource, new ArchetypeStatGenerator(new ArchetypeCompatibilityChecker())).generateReport();
    }

    @Then("it should show one archetype containing the decks {string} with {int} games played and {double}% winrate.")
    public void it_should_show_one_archetype_containing_the_deck_with_games_played_and_winrate(String deckCode, int numberOfGames, double winrate) {
        assertThat(metaReport.getArchetypes()).hasSize(1);
        ArchetypeStat archetypeStat = metaReport.getArchetypes().get(0);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(archetypeStat.getDecks()).contains(Deck.fromCode(deckCode));
            softly.assertThat(archetypeStat.getNumberOfMatches()).isEqualTo(numberOfGames);
            softly.assertThat(archetypeStat.getWinrate()).isEqualTo(winrate);
        });
    }

    @Then("it should show one archetype with {int} games played and {double}% winrate with the following decks:")
    public void it_should_show_one_archetype_with_games_played_and_winrate_with_the_following_decks(int numberOfGames, double winrate, List<String> deckCodes) {
        assertThat(metaReport.getArchetypes()).hasSize(1);
        ArchetypeStat archetypeStat = metaReport.getArchetypes().get(0);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(archetypesContainsAllDecks(archetypeStat, deckCodes)).isTrue();
            softly.assertThat(archetypeStat.getNumberOfMatches()).isEqualTo(numberOfGames);
            softly.assertThat(archetypeStat.getWinrate()).isEqualTo(winrate);
        });
    }

    @Then("it should contain an archetype named {string}")
    public void it_should_contain_an_archetype_named(String archetypeName) {
        assertThat(metaReport.getArchetypes().get(0).getName()).isEqualTo(archetypeName);
    }

    @Then("it should contain an archetype with {int} games played and {double}% winrate with the following decks:")
    public void it_should_contain_an_archetype_with_games_played_and_winrate_with_the_following_decks(int numberOfGames, double winrate, List<String> deckCodes) {
        assertThat(metaReport.getArchetypes()).anyMatch(archetype -> checkArchetype(archetype, numberOfGames, winrate, deckCodes));
    }

    private boolean checkArchetype(final ArchetypeStat archetypeStat, final int numberOfGames, final double winrate, final List<String> deckCodes) {
        return archetypesContainsAllDecks(archetypeStat, deckCodes) &&
                archetypeStat.getNumberOfMatches() == numberOfGames &&
                archetypeStat.getWinrate() == winrate;
    }

    private boolean archetypesContainsAllDecks(ArchetypeStat archetypeStat, List<String> deckCodes) {
        return deckCodes.stream()
                .map(Deck::fromCode)
                .allMatch(deck -> archetypeStat.getDecks().contains(deck));
    }

}
