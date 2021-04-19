Feature: Computing the card code of a card

  Scenario Outline: The code of the region is in the code of the card
    Given a card from the "<region>" region
    When the code of the card is computed
    Then the third and fourth characters from the code should be "<regionCode>"
    Examples:
      | region   | regionCode |
      | Demacia  | DE         |
      | Freljord | FR         |