package com.runeterrahelper.meta.processors;

import com.runeterrahelper.archetypes.Archetype;
import com.runeterrahelper.archetypes.ArchetypeCompatibilityChecker;
import com.runeterrahelper.decks.Deck;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ArchetypeGroupingStepDefs {

  private Archetype archetype;
  private boolean isCompatible;

  @Given("an archetype")
  public void an_archetype() {
    archetype = new Archetype();
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
