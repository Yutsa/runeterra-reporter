package com.runeterrahelper.encoding;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class VarIntStepDefs {

    private Integer integer;
    private Integer integer2;
    private List<Integer> varint = new ArrayList<>();
    private int poppedInteger;
    private int poppedInteger2;

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
        poppedInteger = VarInt.pop(varint);
    }
    @Then("the result is {int}")
    public void the_result_is(Integer result) {
        assertThat(poppedInteger).isEqualTo(result);
    }

    @Given("the integers {int} and {int}")
    public void the_integers_and(Integer integer1, Integer integer2) {
        this.integer = integer1;
        this.integer2 = integer2;
    }
    @When("the two integers are added to the varint")
    public void the_two_integers_are_added_to_the_varint() {
        varint.addAll(VarInt.get(integer));
        varint.addAll(VarInt.get(integer2));
    }
    @When("the two values are popped")
    public void the_two_values_are_popped() {
        poppedInteger = VarInt.pop(varint);
        poppedInteger2 = VarInt.pop(varint);
    }
    @Then("the popped integer are the one that were encoded")
    public void the_popped_integer_are_the_one_that_were_encoded() {
        assertThat(poppedInteger).isEqualTo(integer);
        assertThat(poppedInteger2).isEqualTo(integer2);
    }


    private String formatResult(List<Integer> integers) {
        return integers.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
