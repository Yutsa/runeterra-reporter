package com.runeterrahelper.steps;

import com.runeterrahelper.cards.Card;
import com.runeterrahelper.cards.CardReleaseSet;
import com.runeterrahelper.cards.Region;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class CardStepDefinitions {
    private Card card;
    private String code;

    @Given("a card from the Demacia region")
    public void a_card_from_the_demacia_region() {
        card = new Card(CardReleaseSet.EMPIRE_OF_THE_ASCENDED, Region.DEMACIA);
    }

    @Given("a card from the Ionia region")
    public void a_card_from_the_ionia_region() {
        card = new Card(CardReleaseSet.EMPIRE_OF_THE_ASCENDED, Region.IONIA);
    }

    @Given("a card from the Freljord region")
    public void a_card_from_the_freljord_region() {
        card = new Card(CardReleaseSet.EMPIRE_OF_THE_ASCENDED, Region.FRELJORD);
    }

    @When("the code of the card is computed")
    public void i_compute_the_code_from_the_card() {
        code = card.getCode();
    }

    @Then("the third and fourth characters from the code should be {string}")
    public void the_third_and_fourth_caracters_from_the_code_should_be(String regionCode) {
        assertThat(code.substring(2)).isEqualTo(regionCode);
    }
}
