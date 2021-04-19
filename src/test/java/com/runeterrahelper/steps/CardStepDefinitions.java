package com.runeterrahelper.steps;

import com.runeterrahelper.cards.*;

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

    @Given("a card from the {string} release set")
    public void aCardFromTheReleaseSet(String releaseSet) {
        card = new Card(ReleaseSetParser.parse(releaseSet), Region.NOXUS);
    }

    @Then("the first two characters from the code should be {string}")
    public void theFirstTwoCharactersFromTheCodeShouldBe(String releaseSetCode) {
        assertThat(code).startsWith(releaseSetCode);
    }
}
