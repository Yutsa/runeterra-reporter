package com.runeterrahelper.matches;

import com.runeterrahelper.cards.Region;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.runeterrahelper.cards.Region.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class MatchHistoryStepDefs {

    private Player player1;
    private String deckCode1;

    private Player player2;
    private String deckCode2;

    private MatchRecap matchRecap;

    @Given("two players that played against each other")
    public void two_players_that_played_against_each_other() {
        player1 = new Player();
        player2 = new Player();
    }

    @Given("the first player used the deck with the deck code {string}")
    public void the_first_player_used_the_deck_with_the_deck_code(String deckCode) {
        deckCode1 = deckCode;
        player1.usesDeck(deckCode);
        player1.addToRegions(SHADOW_ISLES, FRELJORD);
    }

    @Given("the second player used the deck with the deck code {string}")
    public void the_second_player_used_the_deck_with_the_deck_code(String deckCode) {
        deckCode2 = deckCode;
        player2.usesDeck(deckCode);
        player2.addToRegions(SHADOW_ISLES, DEMACIA);
    }

    @Given("the first player won")
    public void the_first_player_won() {
        player1.won();
    }

    @When("the match recap is retrieved")
    public void the_match_recap_is_retrieved() {
        matchRecap = new MatchRecap(player1, player2);
    }

    @Then("it should show both deck codes")
    public void it_should_show_both_deck_codes() {
        assertSoftly(softly -> {
            softly.assertThat(matchRecap.firstDeckCode()).isEqualTo(deckCode1);
            softly.assertThat(matchRecap.secondDeckCode()).isEqualTo(deckCode2);
        });
    }

    @Then("it should show that the first player won")
    public void it_should_show_that_the_first_player_won() {
        assertThat(matchRecap.victoriousPlayer()).isEqualTo(player1);
    }

    @Then("it should show the first player used the regions {region} and {region}")
    public void it_should_show_the_first_player_used_the_regions_and(Region region1, Region region2) {
        assertThat(matchRecap.getPlayer1().getRegions()).contains(region1, region2);
    }
    
    @Then("it should show the second player used the regions {region} and {region}")
    public void it_should_show_the_second_player_used_the_regions_and(Region region1, Region region2) {
        assertThat(matchRecap.getPlayer2().getRegions()).contains(region1, region2);
    }

    @ParameterType("DE|FR|IO|NX|PZ|SI|BW|MT|SH")
    public Region region(String region) { 
        return Region.fromString(region);
    }
}
