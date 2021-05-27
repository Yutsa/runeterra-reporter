Feature: Grouping decks into archetypes

  Scenario: Two decks with the same champions and cards except a different region splash should be in different
  archetypes. For instance, Zed Elusives Freljord and Zed Elusives Noxus are different decks even if they share almost
  all the same cards.
    Given an archetype
    And the archetype contains the decks:
      | CEDACBACB4BQCAIEAULAGAICAYERCAICAIFAEAQBAYDQCAYCA4BAEAICFQ4QCAYBAIAQCAICGE |
    When the system checks if the deck "CEDQIAIDB4RCGKABAMBQGAIEAMJACBACB4BACAQGBEAQEAQKAEBQEBYBAMAQEEJMHEAQCAICGE" is compatible with the archetype
    Then it should not be compatible

    Scenario: Two decks with the same region combination aren't necessarily in the same archetype.
      The decks are LeBlanc / Sivir and Azir / Darius
      Given an archetype
      And the archetype contains the decks:
      | CMBQEAIDAQPQGBADAICA6BIEA4KCMLJXKEBACBAHDIBACAYLGMCACAIDHAAQGAYOAECAGEYBAQDTW |
      When the system checks if the deck "CMBQCAQDAMCQCAYCA4GCQNYFAQDQEAY2G5TQEAIBAMSQCAYDBYAQGAIDBEHSM" is compatible with the archetype
      Then it should not be compatible