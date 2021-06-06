package com.runeterrahelper.cardrepository.mongodb;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Optional;

import com.runeterrahelper.cards.Card;
import org.springframework.data.mongodb.core.*;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.client.MongoClients;
import com.runeterrahelper.cards.CardType;
import com.runeterrahelper.cards.repository.*;

public class MongoCardRepository implements CardRepository {

  private final String databaseName;

  public MongoCardRepository(final String databaseName) {this.databaseName = databaseName;}

  @Override
  public Optional<CardWithData> getCardWithDataFromCard(final Card card) {
    MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), databaseName);
    return Optional.ofNullable(mongoOps.findOne(new Query(where("cardCode").is(card.getCode())), MongoCard.class, "card"))
                   .map(mongoCard -> new CardWithData(card, mongoCard.getRarity().equals("Champion") ? CardType.CHAMPION : CardType.SPELL, mongoCard.getName()));
  }
}
