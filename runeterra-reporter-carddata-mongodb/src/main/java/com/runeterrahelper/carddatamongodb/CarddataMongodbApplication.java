package com.runeterrahelper.carddatamongodb;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.apache.commons.logging.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.*;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.client.MongoClients;
import com.runeterrahelper.cards.Card;

@SpringBootApplication
public class CarddataMongodbApplication {

  private static final Log log = LogFactory.getLog(CarddataMongodbApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(CarddataMongodbApplication.class, args);
    log.info(new MongoCardRepository("runeterra").getCardWithDataFromCard(Card.fromCode("01DE001")));
  }
}
