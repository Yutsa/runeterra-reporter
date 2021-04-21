package com.runeterrahelper.decks;

import java.util.List;

import org.assertj.core.api.Assertions;

import io.cucumber.java.en.*;

public class DeckStepDefinitions {

  private Deck deck;
  private Deck sortedDeck;
  // private String deckCode;

  @Given("a deck")
  public void aDeck() {
    deck = new Deck();
  }

  @And("the deck contains the following {string}")
  public void theDeckContainsTheFollowing(String cards) {
    List<CardCopies> cardCopies = CardCopiesParser.parse(cards);
    deck.addCardCopies(cardCopies);
  }

  // @When("the deck is encoded")
  // public void theDeckIsEncoded() {
  //   deckCode = deck.encode();
  // }

  // @Then("the deck code should be {string}")
  // public void theDeckCodeShouldBe(String deckCode) {
  //   Assertions.assertThat(deckCode).isEqualTo(deckCode);
  // }

  @When("the deck is sorted")
  public void theDeckIsSorted() {
    sortedDeck = new DeckSorter().sort(deck);
  }

  @Then("the cards should be order this way {string}")
  public void theCardsShouldBeOrderThisWay(String cardCopies) {
    Assertions.assertThat(CardCopiesParser.marshall(sortedDeck.getCards())).isEqualTo(cardCopies);
  }
}
