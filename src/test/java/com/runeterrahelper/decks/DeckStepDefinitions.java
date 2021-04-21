package com.runeterrahelper.decks;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import com.runeterrahelper.cards.Card;
import io.cucumber.java.en.*;

public class DeckStepDefinitions {

  private Deck deck;

  @Given("a deck")
  public void aDeck() {
    deck = new Deck();
  }

  @And("the deck contains the following {string}")
  public void theDeckContainsTheFollowing(String cards) {
    List<CardCopies> cardCopies = CardCopiesMarshaller.unmarshall(cards);
    deck.addCardCopies(cardCopies);
  }

  @Then("the cards in the {int}ofs should be {string}")
  public void theCardsInTheOfsShouldBe(int copies, String cardCopiesList) {
    if (copies == 1) {
      assertThat(cardListToString(deck.oneOfs())).isEqualTo(cardCopiesList);
    } else if (copies == 2) {
      assertThat(cardListToString(deck.twoOfs())).isEqualTo(cardCopiesList);
    } else if (copies == 3) {
      assertThat(cardListToString(deck.threeOfs())).isEqualTo(cardCopiesList);
    }
  }

  private String cardListToString(List<Card> cards) {
    return cards.stream()
                .map(Card::getCode)
                .collect(Collectors.joining(","));
  }
}
