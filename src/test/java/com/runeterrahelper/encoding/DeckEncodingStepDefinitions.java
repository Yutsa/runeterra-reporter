package com.runeterrahelper.encoding;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;

import com.runeterrahelper.cards.Card;
import com.runeterrahelper.decks.CardCopies;
import com.runeterrahelper.decks.CardCopiesMarshaller;
import com.runeterrahelper.decks.Deck;
import com.runeterrahelper.encoding.DeckSorter;
import com.runeterrahelper.encoding.DeckVarintEncoder;
import com.runeterrahelper.encoding.SortedDeck;
import com.runeterrahelper.encoding.VarInt;

import io.cucumber.java.en.*;

public class DeckEncodingStepDefinitions {

    private Deck deck;
    private SortedDeck sortedDeck;
    private VarInt varInt;
    private String deckCode;

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

    @When("the deck is sorted")
    public void theDeckIsSorted() {
        sortedDeck = new DeckSorter().sort(deck);
    }

    @Then("the sorted deck should be {string}")
    public void theSortedDeckShouldBe(String sortedDeck) {
        Assertions.assertThat(this.sortedDeck.toString()).hasToString(sortedDeck);
    }

    @When("the deck is encoded to a varint")
    public void the_deck_is_encoded_to_a_varint() {
        varInt = new DeckVarintEncoder(new DeckSorter()).encode(deck);
    }

    @Then("the varint should contain the following {string}")
    public void the_varint_should_contain_the_following(String expectedVarint) {
        assertThat(varInt.toString()).isEqualTo(expectedVarint);
    }

    @When("the deck code is computed")
    public void theDeckCodeIsComputed() {
        deckCode = new DeckEncoder().encode(deck);
    }

    @Then("the deck code is {string}")
    public void theDeckCodeIs(String expectedDeckCode) {
        assertThat(deckCode).isEqualTo(expectedDeckCode);
    }

    @Given("a deck code {string}")
    public void a_deck_code(String deckCode) {
        this.deckCode = deckCode;
    }
    @When("the code is decoded")
    public void the_code_is_decoded() {
        deck = new DeckEncoder().decode(deckCode);
    }
    @Then("the resulting deck should contain the following {string}")
    public void the_resulting_deck_should_contain_the_following(String cards) {
        Arrays.stream(cards.split(","))
                .forEach(cardCode -> checkIfCardIsInDeck(cardCode, deck));
    }

    private void checkIfCardIsInDeck(String cardCode, Deck deck) {
        CardCopies cardCopy = CardCopies.fromString(cardCode);
        assertThat(deck.getCards()).contains(cardCopy);
    }

    private String cardListToString(List<Card> cards) {
        return cards.stream()
                .map(Card::getCode)
                .collect(Collectors.joining(","));
    }
}
