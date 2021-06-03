package com.runeterrahelper.carddatamongodb;

import org.apache.commons.logging.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.runeterrahelper.cards.Card;

@SpringBootApplication
public class CarddataMongodbApplication {

  private static final Log log = LogFactory.getLog(CarddataMongodbApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(CarddataMongodbApplication.class, args);
    new MongoCardRepository("runeterra").getCardWithDataFromCard(Card.fromCode("01DE001"))
                                        .ifPresent(card -> log.info(card.toString()));
  }
}
