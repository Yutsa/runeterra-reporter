Feature: Deck encoding

  Scenario Outline: The cards should be grouped by copies
    Given a deck
    And the deck contains the following "<cards>"
    When the deck is sorted
    Then the cards should be order this way "<order>"
    Examples:
      | cards     | order     |
      | 3:01NX004 | 3:01NX004 |


#  Scenario Outline: Encoding a deck with one region and only 3 ofs
#    Given a deck
#    And the deck contains the following "<cards>"
#    When the deck is encoded
#    Then the deck code should be "<deckCode>"
#    Examples:
#      | cards                                                                                                                                                           | deckCode                                                 |
#      | 3:01NX004,3:01NX006,3:01NX029,3:01NX030,3:01FR004,3:01FR009,3:01FR020,3:01FR021,3:01FR033,3:01FR045,3:01FR046,2:01NX011,2:01NX053,1:01FR003,1:01NX032,1:01NX036 | CMBAIAIDAQDB2HQHAEAQICIUCUQS2LQBAIAQGCZVAIAQCAIDAIAQGIBE |
#