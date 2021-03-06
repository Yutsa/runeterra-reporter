package com.runeterrareporter.meta.processors;

import com.runeterrareporter.archetypes.ArchetypeCompatibilityChecker;
import com.runeterrareporter.cards.Card;
import com.runeterrareporter.cards.CardType;
import com.runeterrareporter.cards.CardWithDataFactory;
import com.runeterrareporter.cards.repository.CardWithData;
import com.runeterrareporter.cards.repository.FakeCardRepository;
import com.runeterrareporter.decks.Deck;
import com.runeterrareporter.decks.DeckWithDataFactory;
import com.runeterrareporter.meta.FakeMetaDatasource;
import com.runeterrareporter.meta.processors.model.ArchetypeStat;
import com.runeterrareporter.meta.processors.model.MetaReport;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MetaReportStepDefs {

    private FakeMetaDatasource metaDataSource;
    private MetaReport metaReport;
    private FakeCardRepository cardRepository;

    @Given("a datasource")
    public void a_datasource() {
        cardRepository = new FakeCardRepository();
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

    @Given("the card with code {string} has name {string} and is a champion")
    public void the_card_with_code_has_name_and_is_a_champion(String cardCode, String cardName) {
        Card card = Card.fromCode(cardCode);
        cardRepository.addCard(new CardWithData(card, CardType.CHAMPION, cardName));
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
