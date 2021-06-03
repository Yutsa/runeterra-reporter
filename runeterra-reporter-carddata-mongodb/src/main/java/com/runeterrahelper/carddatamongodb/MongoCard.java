package com.runeterrahelper.carddatamongodb;

public class MongoCard {

  private String cardCode;
  private String name;
  private String rarity;

  public String getRarity() {
    return rarity;
  }

  public String getCardCode() {
    return cardCode;
  }

  public MongoCard setCardCode(final String cardCode) {
    this.cardCode = cardCode;
    return this;
  }

  public String getName() {
    return name;
  }

  public MongoCard setName(final String name) {
    this.name = name;
    return this;
  }

  @Override
  public String toString() {
    return "MongoCard{" +
           "cardCode='" + cardCode + '\'' +
           ", name='" + name + '\'' +
           ", rarity='" + rarity + '\'' +
           '}';
  }

}
