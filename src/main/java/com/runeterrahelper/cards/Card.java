package com.runeterrahelper.cards;

import org.apache.commons.lang.StringUtils;

public class Card {

  private final CardReleaseSet releaseSet;
  private final Region region;
  private final int cardNumber;

  public Card(CardReleaseSet releaseSet, Region region, int cardNumber) {
    this.releaseSet = releaseSet;
    this.region = region;
    this.cardNumber = cardNumber;
  }

  public String getCode() {
    return releaseSet.getReleaseSetCode() + region.getRegionCode() + formatCardNumber();
  }

  private String formatCardNumber() {
      return StringUtils.leftPad(String.valueOf(this.cardNumber), 3, "0");
  }

  public Region getRegion() {
    return region;
  }

  public int getCardNumber() {
    return cardNumber;
  }
}
