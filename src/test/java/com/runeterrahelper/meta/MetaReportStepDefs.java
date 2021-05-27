package com.runeterrahelper.meta;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.SoftAssertions;

import com.runeterrahelper.decks.Deck;
import com.runeterrahelper.meta.processors.*;
import com.runeterrahelper.meta.processors.model.*;
import io.cucumber.java.en.*;

public class MetaReportStepDefs {

  private FakeMetaDatasource metaDataSource;
  private MetaReport metaReport;

  @Given("a datasource")
  public void a_datasource() {
    metaDataSource = new FakeMetaDatasource();
  }

  @Given("the datasource contains the deck {string} with {int} games played and {int}% winrate")
  public void the_datasource_contains_the_deck_with_games_played_and_winrate(String deckCode, int numberOfGames, int winrate) {
    metaDataSource.addDeck(deckCode, numberOfGames, winrate);
  }

  @When("the meta report is generated")
  public void the_meta_report_is_generated() {
    metaReport = new MetaReporter(metaDataSource, new ArchetypeGenerator(new ArchetypeCompatibilityChecker())).generateReport();
  }

  @Then("it should show one archetype containing the decks {string} with {int} games played and {double}% winrate.")
  public void it_should_show_one_archetype_containing_the_deck_with_games_played_and_winrate(String deckCode, int numberOfGames, double winrate) {
    assertThat(metaReport.getArchetypes()).hasSize(1);
    Archetype archetype = metaReport.getArchetypes().get(0);
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(archetype.getDecks()).contains(Deck.fromCode(deckCode));
      softly.assertThat(archetype.getNumberOfMatches()).isEqualTo(numberOfGames);
      softly.assertThat(archetype.getWinrate()).isEqualTo(winrate);
    });
  }

  @Then("it should show one archetype with {int} games played and {double}% winrate with the following decks:")
  public void it_should_show_one_archetype_with_games_played_and_winrate_with_the_following_decks(int numberOfGames, double winrate, List<String> deckCodes) {
    assertThat(metaReport.getArchetypes()).hasSize(1);
    Archetype archetype = metaReport.getArchetypes().get(0);
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(archetypesContainsAllDecks(archetype, deckCodes)).isTrue();
      softly.assertThat(archetype.getNumberOfMatches()).isEqualTo(numberOfGames);
      softly.assertThat(archetype.getWinrate()).isEqualTo(winrate);
    });
  }

  @Then("it should contain an archetype with {int} games played and {double}% winrate with the following decks:")
  public void it_should_contain_an_archetype_with_games_played_and_winrate_with_the_following_decks(int numberOfGames, double winrate, List<String> deckCodes) {
    assertThat(metaReport.getArchetypes()).anyMatch(archetype -> checkArchetype(archetype, numberOfGames, winrate, deckCodes));
  }

  private boolean checkArchetype(final Archetype archetype, final int numberOfGames, final double winrate, final List<String> deckCodes) {
    return archetypesContainsAllDecks(archetype, deckCodes) &&
           archetype.getNumberOfMatches() == numberOfGames &&
           archetype.getWinrate() == winrate;
  }

  private boolean archetypesContainsAllDecks(Archetype archetype, List<String> deckCodes) {
    return deckCodes.stream()
                    .map(Deck::fromCode)
                    .allMatch(deck -> archetype.getDecks().contains(deck));
  }
}
