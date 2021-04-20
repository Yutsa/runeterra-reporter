package com.runeterrahelper.cards;

import java.util.Arrays;

public enum CardReleaseSet {
  FOUNDATION("01"),
  RISING_TIDES("02"),
  CALL_OF_THE_MOUNTAIN("03"),
  EMPIRE_OF_THE_ASCENDED("04");

  private final String releaseSetCode;

  CardReleaseSet(String releaseSetCode) {
    this.releaseSetCode = releaseSetCode;
  }

  public static CardReleaseSet fromString(final String setCode) {
    return Arrays.stream(values())
                 .filter(releaseSet -> releaseSet.getReleaseSetCode().equals(setCode))
                 .findFirst()
                 .orElse(FOUNDATION);
  }

  public String getReleaseSetCode() {
    return releaseSetCode;
  }
}
