Feature: Deck encoding

  Scenario Outline: A deck is made of a cards in three, two or one copies
    Given a deck
    And the deck contains the following "<cards>"
    Then the cards in the 3ofs should be "<threeOfs>"
    And the cards in the 2ofs should be "<twoOfs>"
    And the cards in the 1ofs should be "<oneOfs>"
    Examples:
      | cards                                   | threeOfs        | twoOfs          | oneOfs          |
      | 3:01NX004                               | 01NX004         |                 |                 |
      | 3:02NX004,2:01DE005                     | 02NX004         | 01DE005         |                 |
      | 3:02NX004,2:01DE005,1:04MT456           | 02NX004         | 01DE005         | 04MT456         |
      | 3:02NX004,3:01DE001,2:01DE005,1:04MT456 | 02NX004,01DE001 | 01DE005         | 04MT456         |
      | 3:02NX004,2:01DE005,2:01DE001,1:04MT456 | 02NX004         | 01DE005,01DE001 | 04MT456         |
      | 3:02NX004,2:01DE005,1:04MT456,1:01DE001 | 02NX004         | 01DE005         | 04MT456,01DE001 |

  Scenario Outline: The cards are sorted by number of copies, creating 3 groups.
  The first group is the one with 3 copies, then 2 copies and 1 copies.
  In those groups the cards are then grouped by set/region pair.
  The set/region groups are sorted by increasing length.
  If two set/region groups have the same length, they are ordered by alphanumerically by their set/region pair.
  Cards in each group are sorted alphanumerically.
    Given a deck
    And the deck contains the following "<cards>"
    When the deck is sorted
    Then the sorted deck should be "<sortedDeck>"
    Examples:
      | cards                                                       | sortedDeck                                      |
      | 3:01NX004                                                   | 01NX004                                         |
      | 3:01NX004,3:01NX005                                         | 01NX004,01NX005                                 |
      | 2:01NX004,3:01NX005                                         | 01NX005,01NX004                                 |
      | 2:01NX004,3:01NX005,1:01NX006                               | 01NX005,01NX004,01NX006                         |
      | 1:01NX006,2:01NX004,3:01NX005                               | 01NX005,01NX004,01NX006                         |
      | 3:01NX005,3:01NX006,3:02NX001                               | 02NX001,01NX005,01NX006                         |
      | 3:01NX005,3:01NX006,3:02NX001,3:03NX001                     | 02NX001,03NX001,01NX005,01NX006                 |
      | 3:01NX005,3:01DE006,3:02NX001,3:03NX001                     | 01DE006,01NX005,02NX001,03NX001                 |
      | 3:01NX006,3:01NX003,3:01NX005                               | 01NX003,01NX005,01NX006                         |
      | 3:01NX005,3:01DE006,3:01NX001,3:03NX001,2:01DE001,1:01DE002 | 01DE006,03NX001,01NX001,01NX005,01DE001,01DE002 |

  Scenario Outline: Encoding the deck into the varint
    Given a deck
    And the deck contains the following "<cards>"
    When the deck is encoded to a varint
    Then the varint should contain the following "<values>"
    Examples:
      | cards     | values    |
      | 3:01DE001 | 1,1,1,0,1 |
  