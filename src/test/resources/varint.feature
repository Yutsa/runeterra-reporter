Feature: VarInt encoder/decoder

  Scenario Outline: The VarInt translators transforms integers to variable lenght integer (Big Endian)
    Given the integer <integer>
    When the varint for this integer is computed
    Then the result is "<listOfBits>"
    Examples:
      | integer | listOfBits |
      | 1       | 1          |
      | 127     | 127        |
      | 128     | 1281       |
      | 129     | 1291       |
      | 130     | 1301       |
      | 254     | 2541       |
      | 255     | 2551       |
      | 256     | 1282       |
      | 257     | 1292       |
      | 383     | 2552       |
      | 384     | 1283       |
      | 511     | 2553       |
      | 512     | 1284       |
      | 654     | 1425       |
      | 785     | 1456       |
      | 999     | 2317       |