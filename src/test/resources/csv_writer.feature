Feature: CSV meta report writer

  Scenario: Generating a CSV from a meta report with one archetype
    Given a meta report that contains the following archetypes:
      | decks                                                                   | numberOfGames | winrate |
      | CMBQCAYBAIBACAIFD4DAIBZGFU3UGXLHAQAQIAIJAECAODICAEARMGQCAIAQEBQBAECAOFA | 120           | 58      |
    When the meta report is written in CSV
    Then the result should be:
  """
  archetype name,number of games played,winrate,deck codes
  Freljord Shurima,120,58.0,CMBQCAYBAIBACAIFD4DAIBZGFU3UGXLHAQAQIAIJAECAODICAEARMGQCAIAQEBQBAECAOFA
  """

  Scenario: The archetypes should be ordered by number of games by default
    Given a meta report that contains the following archetypes:
      | decks                                                                   | numberOfGames | winrate |
      | CMBQCAYBAIBACAIFD4DAIBZGFU3UGXLHAQAQIAIJAECAODICAEARMGQCAIAQEBQBAECAOFA | 120           | 51      |
      | CMBQCAICFICAIBYDDIZTSBIEAICAKCILB4BQCAICFQAQGAQFAICAOHC5AIAQCAQGAEBQEFA | 500           | 58.5    |
    When the meta report is written in CSV
    Then the result should be:
  """
  archetype name,number of games played,winrate,deck codes
  Ionia Shurima,500,58.5,CMBQCAICFICAIBYDDIZTSBIEAICAKCILB4BQCAICFQAQGAQFAICAOHC5AIAQCAQGAEBQEFA
  Freljord Shurima,120,51.0,CMBQCAYBAIBACAIFD4DAIBZGFU3UGXLHAQAQIAIJAECAODICAEARMGQCAIAQEBQBAECAOFA
  """

  Scenario: The deck codes are separated by a ; and are ordered by number of games.
    Given a meta report that an archetype with the following decks:
      | deck                                                                    | numberOfGames | winrate |
      | CMBQCAICFICAIBYDDIZTSBIEAICAKCILB4BQCAICFQAQGAQFAICAOHC5AIAQCAQGAEBQEFA | 100           | 50      |
      | CMCACAICFIAQEAQKAQCAOAY2GM4QKBACAQCQSCYPAIAQGAQFAIAQEBRMAEAQIBY4        | 500           | 50      |
      | CMCACAYCAUBACAQGFICAIBYDDIZV2BIEAICAKCILB4AQCBAHHEBACAQCBIAQIAQH        | 9000          | 50      |
    When the meta report is written in CSV
    Then the result should be:
  """
  archetype name,number of games played,winrate,deck codes
  Ionia Shurima,9600,50.0,CMCACAYCAUBACAQGFICAIBYDDIZV2BIEAICAKCILB4AQCBAHHEBACAQCBIAQIAQH;CMBQCAICFICAIBYDDIZTSBIEAICAKCILB4BQCAICFQAQGAQFAICAOHC5AIAQCAQGAEBQEFA;CMCACAICFIAQEAQKAQCAOAY2GM4QKBACAQCQSCYPAIAQGAQFAIAQEBRMAEAQIBY4
  """