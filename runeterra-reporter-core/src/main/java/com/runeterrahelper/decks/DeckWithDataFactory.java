package com.runeterrahelper.decks;

import com.runeterrahelper.cards.CardWithDataFactory;
import com.runeterrahelper.cards.repository.CardWithData;

import java.util.List;
import java.util.stream.Collectors;

public class DeckWithDataFactory {

    private final CardWithDataFactory cardWithDataFactory;

    public DeckWithDataFactory(CardWithDataFactory cardWithDataFactory) {
        this.cardWithDataFactory = cardWithDataFactory;
    }

    public DeckWithData fromCode(String deckCode) {
        Deck deck = Deck.fromCode(deckCode);
        List<CardWithData> cardsWithData = deck.getCards()
                .stream()
                .map(CardCopies::getCard)
                .map(cardWithDataFactory::fromCard)
                .collect(Collectors.toList());
        DeckWithData deckWithData = new DeckWithData(cardsWithData);
        deckWithData.addCard(deck.getCards());
        return deckWithData;
    }
}
