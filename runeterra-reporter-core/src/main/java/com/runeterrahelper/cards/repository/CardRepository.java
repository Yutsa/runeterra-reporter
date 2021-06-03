package com.runeterrahelper.cards.repository;

import com.runeterrahelper.cards.Card;

public interface CardRepository {
    CardWithData getCardWithDataFromCard(Card card);
}
