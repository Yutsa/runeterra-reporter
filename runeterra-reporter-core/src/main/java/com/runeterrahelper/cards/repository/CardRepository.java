package com.runeterrahelper.cards.repository;

public interface CardRepository {
    CardWithData getCardFromCode(String cardCode);
}
