package com.runeterrahelper.cards;

import java.util.Arrays;

public enum ReleaseSet {
  FOUNDATION("01", 1),
  RISING_TIDES("02", 2),
  CALL_OF_THE_MOUNTAIN("03", 3),
  EMPIRE_OF_THE_ASCENDED("04", 4);

  private final String releaseSetCode;
  private final int id;

  ReleaseSet(String releaseSetCode, int id) {
    this.releaseSetCode = releaseSetCode;
    this.id = id;
  }

  public static ReleaseSet fromString(final String setCode) {
    return Arrays.stream(values())
                 .filter(releaseSet -> releaseSet.getReleaseSetCode().equals(setCode))
                 .findFirst()
                 .orElseThrow();
  }

  public String getReleaseSetCode() {
    return releaseSetCode;
  }

  public int getId() {
    return id;
  }
}
