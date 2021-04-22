package com.runeterrahelper.cards;

import java.util.Arrays;

public enum ReleaseSet {
  FOUNDATION("01"),
  RISING_TIDES("02"),
  CALL_OF_THE_MOUNTAIN("03"),
  EMPIRE_OF_THE_ASCENDED("04");

  private final String releaseSetCode;

  ReleaseSet(String releaseSetCode) {
    this.releaseSetCode = releaseSetCode;
  }

  public static ReleaseSet fromString(final String setCode) {
    return Arrays.stream(values())
                 .filter(releaseSet -> releaseSet.getReleaseSetCode().equals(setCode))
                 .findFirst()
                 .orElse(FOUNDATION);
  }

  public String getReleaseSetCode() {
    return releaseSetCode;
  }
}
