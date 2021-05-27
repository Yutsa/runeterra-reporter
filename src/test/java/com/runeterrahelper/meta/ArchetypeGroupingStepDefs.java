package com.runeterrahelper.meta;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.runeterrahelper.decks.Deck;
import com.runeterrahelper.meta.processors.ArchetypeCompatibilityChecker;
import com.runeterrahelper.meta.processors.model.Archetype;
import io.cucumber.java.en.*;

public class ArchetypeGroupingStepDefs {

  private Archetype archetype;
  private boolean isCompatible;

  @Given("an archetype")
  public void an_archetype() {
    archetype = new Archetype("foo");
  }

  @Given("the archetype contains the decks:")
  public void the_archetype_contains_the_decks(List<String> deckCodes) {
    deckCodes.stream()
             .map(Deck::fromCode)
             .forEach(archetype::addToDecks);
  }

  @When("the system checks if the deck {string} is compatible with the archetype")
  public void the_system_checks_if_the_deck_is_compatible_with_the_archetype(String deckCode) {
    isCompatible = new ArchetypeCompatibilityChecker().isArchetypeCompatibleWithDeck(archetype, Deck.fromCode(deckCode));
  }

  @Then("it should not be compatible")
  public void it_should_not_be_compatible() {
    assertThat(isCompatible).isFalse();
  }

}
