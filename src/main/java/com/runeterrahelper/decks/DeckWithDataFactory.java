package com.runeterrahelper.decks;

import com.runeterrahelper.cards.CardType;
import com.runeterrahelper.cards.repository.CardWithData;

import java.util.List;
import java.util.stream.Collectors;

public class DeckWithDataFactory {
    public static DeckWithData fromCode(String deckCode) {
        Deck deck = Deck.fromCode(deckCode);
        List<CardWithData> cardsWithData = deck.getCards()
                .stream()
                .map(CardCopies::getCard)
                .map(card -> new CardWithData(card.getReleaseSet(), card.getRegion(), card.getCardNumber(), CardType.CHAMPION))
                .collect(Collectors.toList());
        DeckWithData deckWithData = new DeckWithData(cardsWithData);
        deckWithData.addCard(deck.getCards());
        return deckWithData;
    }
}
