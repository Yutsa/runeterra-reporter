package com.runeterrareporter.cards.repository;

import java.util.Optional;

import com.runeterrareporter.cards.Card;

public interface CardRepository {
    Optional<CardWithData> getCardWithDataFromCard(Card card);
}
