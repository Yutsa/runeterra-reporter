package com.runeterrahelper.cards;

import com.runeterrahelper.cards.repository.CardRepository;
import com.runeterrahelper.cards.repository.CardWithData;

public class CardWithDataFactory {
    private final CardRepository cardRepository;

    public CardWithDataFactory(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CardWithData fromCard(Card card) {
        return cardRepository.getCardWithDataFromCard(card);
    }
}
