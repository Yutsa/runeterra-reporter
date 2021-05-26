Feature: Meta reports

  Scenario: Generating a meta report with only one deck being played
    Given a datasource
    And the datasource contains the deck "CMBQIBAHAMNDGOIBAEBCUBIEAICAKCILB4BQCAICFQAQGAQFAICAOHC5AIAQCAQGAEBQEFA" with 120 games played and 58% winrate
    When the meta report is generated
    Then it should show one archetype containing the decks "CMBQIBAHAMNDGOIBAEBCUBIEAICAKCILB4BQCAICFQAQGAQFAICAOHC5AIAQCAQGAEBQEFA" with 120 games played and 58% winrate.

  Scenario: Generating a meta report with two decks from the same archetype
    Given a datasource
    And the datasource contains the deck "CMBQIBAHAMNDGOIBAEBCUBIEAICAKCILB4BQCAICFQAQGAQFAICAOHC5AIAQCAQGAEBQEFA" with 120 games played and 58% winrate
    And the datasource contains the deck "CMBQEAICAYVAIBAHAMNDGXIFAQBAIBIJBMHQGAIBAIWACAYCAUAQIBZZAEAQGAQU" with 200 games played and 55% winrate
    When the meta report is generated
    Then it should show one archetype with 320 games played and 56.13% winrate with the following decks:
      | CMBQIBAHAMNDGOIBAEBCUBIEAICAKCILB4BQCAICFQAQGAQFAICAOHC5AIAQCAQGAEBQEFA |
      | CMBQEAICAYVAIBAHAMNDGXIFAQBAIBIJBMHQGAIBAIWACAYCAUAQIBZZAEAQGAQU        |

  Scenario: Generating a meta report with three decks from the same archetype
    Given a datasource
    And the datasource contains the deck "CMBQIBAHAMNDGOIBAEBCUBIEAICAKCILB4BQCAICFQAQGAQFAICAOHC5AIAQCAQGAEBQEFA" with 120 games played and 58% winrate
    And the datasource contains the deck "CMBQEAICAYVAIBAHAMNDGXIFAQBAIBIJBMHQGAIBAIWACAYCAUAQIBZZAEAQGAQU" with 200 games played and 55% winrate
    And the datasource contains the deck "CMCACAYCAUBACAQGFICAIAQEAUEQ6BAEA4BRUM25AMAQEAQKAECAECYBAQDTSAIBAMBBI" with 150 games played and 53% winrate
    When the meta report is generated
    Then it should show one archetype with 470 games played and 55.13% winrate with the following decks:
      | CMBQIBAHAMNDGOIBAEBCUBIEAICAKCILB4BQCAICFQAQGAQFAICAOHC5AIAQCAQGAEBQEFA |
      | CMBQEAICAYVAIBAHAMNDGXIFAQBAIBIJBMHQGAIBAIWACAYCAUAQIBZZAEAQGAQU        |
      | CMCACAYCAUBACAQGFICAIAQEAUEQ6BAEA4BRUM25AMAQEAQKAECAECYBAQDTSAIBAMBBI   |