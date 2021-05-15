Feature: Match history of a player with deck information

  Scenario: A match recap shows the deck used by both players and who won
    Given two players that played against each other
    And the first player used the deck with the deck code "CMBAIAIFB4WDANQIAEAQGDAUDAQSIJZUAIAQCAIEAEAQKBIA"
    And the second player used the deck with the deck code "CMBAEAIACYLQQAIFAEFQ6HRDFMWDMAQBAECTCAYBAABA6IICAEAQABYBAECS2"
    And the first player won
    When the match recap is retrieved
    Then it should show both deck codes
    And it should show that the first player won

  Scenario: A match recap shows the regions from the deck of each players
    Given two players that played against each other
    And the first player used the deck with the deck code "CMBAIAIFB4WDANQIAEAQGDAUDAQSIJZUAIAQCAIEAEAQKBIA"
    And the second player used the deck with the deck code "CMBAEAIACYLQQAIFAEFQ6HRDFMWDMAQBAECTCAYBAABA6IICAEAQABYBAECS2"
    When the match recap is retrieved
    Then it should show the first player used the regions SI and FR
    And it should show the second player used the regions DE and SI