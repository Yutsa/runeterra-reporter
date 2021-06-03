package com.runeterrahelper.carddatamongodb;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Optional;

import org.springframework.data.mongodb.core.*;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.client.MongoClients;
import com.runeterrahelper.cards.CardType;
import com.runeterrahelper.cards.repository.*;

public class MongoCardRepository implements CardRepository {

  private final String databaseName;

  public MongoCardRepository(final String databaseName) {this.databaseName = databaseName;}

  @Override
  public Optional<CardWithData> getCardWithDataFromCard(final com.runeterrahelper.cards.Card card) {
    MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), databaseName);
    return Optional.ofNullable(mongoOps.findOne(new Query(where("cardCode").is(card.getCode())), MongoCard.class, "cards"))
                   .map(mongoCard -> new CardWithData(card, CardType.CHAMPION, mongoCard.getName()));
  }
}
