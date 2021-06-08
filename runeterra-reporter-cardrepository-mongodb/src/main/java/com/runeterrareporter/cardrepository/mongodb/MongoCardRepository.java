package com.runeterrareporter.cardrepository.mongodb;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Optional;

import com.runeterrareporter.cards.Card;
import org.springframework.data.mongodb.core.*;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.client.MongoClients;
import com.runeterrareporter.cards.CardType;
import com.runeterrareporter.cards.repository.*;

public class MongoCardRepository implements CardRepository {

  private final MongoOperations mongoOps;

  public MongoCardRepository(final String databaseName) {
    mongoOps = new MongoTemplate(MongoClients.create(), databaseName);
  }

  @Override
  public Optional<CardWithData> getCardWithDataFromCard(final Card card) {
    return Optional.ofNullable(mongoOps.findOne(new Query(where("cardCode").is(card.getCode())), MongoCard.class, "card"))
                   .map(mongoCard -> new CardWithData(card, mongoCard.getRarity().equals("Champion") ? CardType.CHAMPION : CardType.SPELL, mongoCard.getName()));
  }
}
