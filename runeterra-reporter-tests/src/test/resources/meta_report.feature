Feature: Meta reports

  Background: A datasource for the card exists
    Given a datasource

  Scenario: Generating a meta report with only one deck being played
    Given the datasource contains the deck "CMBQIBAHAMNDGOIBAEBCUBIEAICAKCILB4BQCAICFQAQGAQFAICAOHC5AIAQCAQGAEBQEFA" with 120 games played and 58% winrate
    When the meta report is generated
    Then it should show one archetype containing the decks "CMBQIBAHAMNDGOIBAEBCUBIEAICAKCILB4BQCAICFQAQGAQFAICAOHC5AIAQCAQGAEBQEFA" with 120 games played and 58% winrate.

  Scenario: Generating a meta report with two decks from the same archetype
    Given the datasource contains the deck "CMBQIBAHAMNDGOIBAEBCUBIEAICAKCILB4BQCAICFQAQGAQFAICAOHC5AIAQCAQGAEBQEFA" with 120 games played and 58% winrate
    Given the datasource contains the deck "CMBQEAICAYVAIBAHAMNDGXIFAQBAIBIJBMHQGAIBAIWACAYCAUAQIBZZAEAQGAQU" with 200 games played and 55% winrate
    When the meta report is generated
    Then it should show one archetype with 320 games played and 56.13% winrate with the following decks:
      | CMBQIBAHAMNDGOIBAEBCUBIEAICAKCILB4BQCAICFQAQGAQFAICAOHC5AIAQCAQGAEBQEFA |
      | CMBQEAICAYVAIBAHAMNDGXIFAQBAIBIJBMHQGAIBAIWACAYCAUAQIBZZAEAQGAQU        |

  Scenario: Generating a meta report with three decks from the same archetype
    Given the datasource contains the deck "CMBQIBAHAMNDGOIBAEBCUBIEAICAKCILB4BQCAICFQAQGAQFAICAOHC5AIAQCAQGAEBQEFA" with 120 games played and 58% winrate
    Given the datasource contains the deck "CMBQEAICAYVAIBAHAMNDGXIFAQBAIBIJBMHQGAIBAIWACAYCAUAQIBZZAEAQGAQU" with 200 games played and 55% winrate
    Given the datasource contains the deck "CMCACAYCAUBACAQGFICAIAQEAUEQ6BAEA4BRUM25AMAQEAQKAECAECYBAQDTSAIBAMBBI" with 150 games played and 53% winrate
    When the meta report is generated
    Then it should show one archetype with 470 games played and 55.13% winrate with the following decks:
      | CMBQIBAHAMNDGOIBAEBCUBIEAICAKCILB4BQCAICFQAQGAQFAICAOHC5AIAQCAQGAEBQEFA |
      | CMBQEAICAYVAIBAHAMNDGXIFAQBAIBIJBMHQGAIBAIWACAYCAUAQIBZZAEAQGAQU        |
      | CMCACAYCAUBACAQGFICAIAQEAUEQ6BAEA4BRUM25AMAQEAQKAECAECYBAQDTSAIBAMBBI   |

  Scenario: Generating a meta report with two decks from different archetypes
    Given the datasource contains the deck "CMBQIBAHAMNDGOIBAEBCUBIEAICAKCILB4BQCAICFQAQGAQFAICAOHC5AIAQCAQGAEBQEFA" with 120 games played and 58% winrate
    Given the datasource contains the deck "CMBQEBAFAMIAIBAHAINC6ZYFAECQWKBQGE2ACAQBAUMSEAQBAQCQIAQEA45XS" with 200 games played and 55% winrate
    When the meta report is generated
    Then it should contain an archetype with 120 games played and 58% winrate with the following decks:
      | CMBQIBAHAMNDGOIBAEBCUBIEAICAKCILB4BQCAICFQAQGAQFAICAOHC5AIAQCAQGAEBQEFA |
    And it should contain an archetype with 200 games played and 55% winrate with the following decks:
      | CMBQEBAFAMIAIBAHAINC6ZYFAECQWKBQGE2ACAQBAUMSEAQBAQCQIAQEA45XS |

  Scenario: Generating a meta report with 3 decks, two of which are in the same archetype
  The decks are two Overwhelm Shurima lists and a Zed/Sivir list
    Given the datasource contains the deck "CEBQMBAHEYWTOQ25M4AQGAICAIAQCBI7AQBACAIWDIAQIBYNAIBACAQGAECACCIBAECAOFA" with 120 games played and 58% winrate
    And the datasource contains the deck "CECACAYBAIBAEAICAYCAIBZGFU3UGBABAECRUHZQAIAQCAIWAICAODK5AEAQIB2F" with 200 games played and 55% winrate
    And the datasource contains the deck "CEBQCAICBEAQGAQZA4CAOBIUDITDOULHAIAQGAQUAUCAOAKFJ5OWMAIBAQDTW" with 230 games played and 55% winrate
    When the meta report is generated
    Then it should contain an archetype with 320 games played and 56.13% winrate with the following decks:
      | CEBQMBAHEYWTOQ25M4AQGAICAIAQCBI7AQBACAIWDIAQIBYNAIBACAQGAECACCIBAECAOFA |
      | CECACAYBAIBAEAICAYCAIBZGFU3UGBABAECRUHZQAIAQCAIWAICAODK5AEAQIB2F        |
    And it should contain an archetype with 230 games played and 55% winrate with the following decks:
      | CEBQCAICBEAQGAQZA4CAOBIUDITDOULHAIAQGAQUAUCAOAKFJ5OWMAIBAQDTW |

  Scenario: Generating an archetype name using the regions and the champions in the archetype.
    Given the card with code "04SH020" has name "Sivir" and is a champion
    Given the card with code "04SH067" has name "Renekton" and is a champion
    Given the card with code "02FR002" has name "Sejuani" and is a champion
    And the datasource contains the deck "CEBQMBAHEYWTOQ25M4AQGAICAIAQCBI7AQBACAIWDIAQIBYNAIBACAQGAECACCIBAECAOFA" with 120 games played and 58% winrate
    When the meta report is generated
    Then it should contain an archetype named "Freljord Shurima Renekton Sejuani Sivir"

  Scenario: An archetype can have decks with champions and without champions.
  For instance, the Matron Cithria archetype has versions with Elise, with  Shyvana, with Kalista or without any champions.
    Given the card with code "03DE011" has name "Shyvana" and is a champion
    Given the card with code "01SI030" has name "Kalista" and is a champion
    Given the card with code "01SI053" has name "Elise" and is a champion
    And the datasource contains the deck "CECQCAIADIAQGBIEAECAABIDAQCQIDIQAUAQKBAMFQYDCAQBAIAAQAQBAAHRGAIBAECRS" with 100 games played and 51% winrate
    And the datasource contains the deck "CECQCAIADIAQGBIEAECAABICAQCQ2EAGAECQIDA6FAWDAAIDAECQWMJVAEAQEBIE" with 100 games played and 51% winrate
    And the datasource contains the deck "CECACAIADIAQIAAFAICAKDIQAYAQKBA6FAWDAMIEAEAQADYBAIAACAIDAUCAEAIFBMGAA" with 100 games played and 51% winrate
    And the datasource contains the deck "CEDAEBAAAUHAEAIFBQWACBAFBUBQGAAGBMHAEAIACMNACAYFAQBACAIFAQBACAALB4AQCAIFGE" with 100 games played and 51% winrate
    When the meta report is generated
    Then it should contain an archetype named "Demacia Shadow Isles Elise Kalista Shyvana"

  Scenario: An archetype can have different decks variant with different champions.
  For instance the Dragons decks can combine Shyvana, Zoe and Aurelion Sol in different ways
    Given the card with code "03DE011" has name "Shyvana" and is a champion
    Given the card with code "03MT009" has name "Zoe" and is a champion
    Given the card with code "03MT087" has name "Aurelion Sol" and is a champion
    And the datasource contains the deck "CICQCAIADIAQEAABAECAADQCAMEVLXIBAQBQABQIBMHAGAIBAAFQCBAJAQCAGCIDHFLWIAIBAMEQS" with 100 games played and 50% winrate
    And the datasource contains the deck "CICACAIADIAQICIEAIBQSVO5AECAGAAGBAFQ4BABAIAACAIEAAHAEAIABMHQIAYJAM4VOZAA" with 100 games played and 50% winrate
    And the datasource contains the deck "CECQCBAABYCAGAAGBAFQ4AIBAANACAYJKUAQEAABAMAQICIEAUBQSAZZK5SN2AIBAEAAWAICAMEQSMY" with 100 games played and 50% winrate
    When the meta report is generated
    Then it should contain an archetype named "Demacia Targon Aurelion Sol Shyvana Zoe"