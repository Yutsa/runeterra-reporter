package com.runeterrahelper.cards.repository;

import java.util.Optional;

import com.runeterrahelper.cards.Card;

public interface CardRepository {
    Optional<CardWithData> getCardWithDataFromCard(Card card);
}
