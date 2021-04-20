package com.runeterrahelper.steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.runeterrahelper.cards.*;
import io.cucumber.java.en.*;

public class CardStepDefinitions {

  private String code;
  private CardBuilder cardBuilder;

  @When("the code of the card is computed")
  public void i_compute_the_code_from_the_card() {
    code = cardBuilder.build().getCode();
  }

  @Then("the third and fourth characters from the code should be {string}")
  public void the_third_and_fourth_caracters_from_the_code_should_be(String regionCode) {
    assertThat(code.substring(2, 4)).isEqualTo(regionCode);
  }

  @Then("the first two characters from the code should be {string}")
  public void theFirstTwoCharactersFromTheCodeShouldBe(String releaseSetCode) {
    assertThat(code).startsWith(releaseSetCode);
  }

  @Then("the fifth to last character from the code should be the {string}")
  public void theFifthToLastCharacterFromTheCodeShouldBeThe(String cardNumber) {
    assertThat(code.substring(4)).isEqualTo(cardNumber);
  }

  @Given("a card")
  public void aCard() {
    cardBuilder = CardBuilder.createCard();
  }

  @And("the card being from the {string} release set")
  public void theCardBeingFromTheReleaseSet(String releaseSet) {
    cardBuilder.withReleaseSet(ReleaseSetParser.parse(releaseSet));
  }

  @And("the card being from the {string} region")
  public void theCardBeingFromTheRegion(String region) {
    cardBuilder.withRegion(RegionParser.parse(region));
  }

  @And("the card having the number {string}")
  public void theCardHavingTheNumber(String cardNumber) {
    cardBuilder.withCardNumber(Integer.parseInt(cardNumber));
  }

  @Then("the code should be {string}")
  public void theCodeShouldBe(String cardCode) {
    assertThat(code).isEqualTo(cardCode);
  }
}
