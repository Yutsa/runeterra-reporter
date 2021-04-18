Feature: Computing the card code of a card
  
  Scenario: A card from Demacia
    Given a card from the Demacia region
    When the code of the card is computed
    Then the third and fourth characters from the code should be "DE"

  Scenario: A card from Freljord
    Given a card from the Freljord region
    When the code of the card is computed
    Then the third and fourth characters from the code should be "FR"

  Scenario: A card from Ionia
    Given a card from the Ionia region
    When the code of the card is computed
    Then the third and fourth characters from the code should be "IO"