package com.runeterrahelper.steps;

import com.runeterrahelper.cards.Card;
import com.runeterrahelper.cards.CardReleaseSet;
import com.runeterrahelper.cards.Region;
import com.runeterrahelper.cards.RegionParser;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class CardStepDefinitions {
    private Card card;
    private String code;

    @Given("a card from the {string} region")
    public void aCardFromTheRegion(String regionName) {
        card = new Card(CardReleaseSet.FOUNDATION, RegionParser.parse(regionName));
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
