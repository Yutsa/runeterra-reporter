Feature: Computing the card code of a card

  Scenario Outline: The code of the region is in the code of the card
    Given a card from the "<region>" region
    When the code of the card is computed
    Then the third and fourth characters from the code should be "<regionCode>"
    Examples:
      | region          | regionCode |
      | Demacia         | DE         |
      | Freljord        | FR         |
      | Ionia           | IO         |
      | Noxus           | NX         |
      | Piltover & Zaun | PZ         |
      | Shadow Isles    | SI         |
      | Bilgewater      | BW         |
      | Mount Targon    | MT         |
      | Shurima         | SH         |

  Scenario Outline: The code of the release set is in the code of the card
    Given a card from the "<releaseSet>" release set
    When the code of the card is computed
    Then the first two characters from the code should be "<releaseSetCode>"
    Examples:
      | releaseSet             | releaseSetCode |
      | Foundation             | 01             |
      | Rising Tides           | 02             |
      | Call of the Mountain   | 03             |
      | Empire of the Ascended | 04             |
      