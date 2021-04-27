package com.runeterrahelper.encoding;

import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;

import io.cucumber.java.en.*;

public class VarIntStepDefs {

  Integer integer;
  private List<Integer> result;

  @Given("the integer {int}")
  public void theIntegerInteger(Integer integer) {
    this.integer = integer;
  }

  @When("the varint for this integer is computed")
  public void theVarintForThisIntegerIsComputed() {
    result = VarInt.get(integer);
  }

  @Then("the result is {string}")
  public void theResultIsListOfBits(String listOfBits) {
    Assertions.assertThat(formatResult(result)).hasToString(listOfBits);
  }

  private String formatResult(List<Integer> integers) {
    return integers.stream()
                   .map(Object::toString)
                   .collect(Collectors.joining());
  }
}
