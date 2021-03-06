package com.runeterrareporter.meta.processors;

import com.runeterrareporter.archetypes.Archetype;
import com.runeterrareporter.archetypes.ArchetypeCompatibilityChecker;
import com.runeterrareporter.cards.CardWithDataFactory;
import com.runeterrareporter.cards.repository.FakeCardRepository;
import com.runeterrareporter.decks.Deck;
import com.runeterrareporter.decks.DeckWithDataFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ArchetypeGroupingStepDefs {

  private final DeckWithDataFactory deckWithDataFactory = new DeckWithDataFactory(new CardWithDataFactory(new FakeCardRepository()));
  private Archetype archetype;
  private boolean isCompatible;

  @Given("an archetype")
  public void an_archetype() {
    archetype = new Archetype();
  }

  @Given("the archetype contains the decks:")
  public void the_archetype_contains_the_decks(List<String> deckCodes) {
    deckCodes.stream()
             .map(deckWithDataFactory::fromCode)
             .forEach(archetype::addToDecks);
  }

  @When("the system checks if the deck {string} is compatible with the archetype")
  public void the_system_checks_if_the_deck_is_compatible_with_the_archetype(String deckCode) {
    isCompatible = new ArchetypeCompatibilityChecker().isArchetypeCompatibleWithDeck(archetype, deckWithDataFactory.fromCode(deckCode));
  }

  @Then("it should not be compatible")
  public void it_should_not_be_compatible() {
    assertThat(isCompatible).isFalse();
  }

}
