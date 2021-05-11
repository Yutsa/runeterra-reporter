package com.runeterrahelper.encoding;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class VarIntStepDefs {

    Integer integer;
    private List<Integer> varint;
    private int poppedInteger;

    @Given("the integer {int}")
    public void theIntegerInteger(Integer integer) {
        this.integer = integer;
    }

    @When("the varint for this integer is computed")
    public void theVarintForThisIntegerIsComputed() {
        varint = VarInt.get(integer);
    }

    @Then("the result is {string}")
    public void theResultIsListOfBits(String listOfBits) {
        assertThat(formatResult(varint)).hasToString(listOfBits);
    }

    @When("the integer is added to the varint")
    public void the_integer_is_added_to_the_varint() {
        varint = VarInt.get(integer);
    }
    @When("the next value is popped from the varint")
    public void the_next_value_is_popped_from_the_varint() {
        VarInt.pop(varint);
    }
    @Then("the result is {int}")
    public void the_result_is(Integer result) {
        assertThat(integer).isEqualTo(result);
    }

    private String formatResult(List<Integer> integers) {
        return integers.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
