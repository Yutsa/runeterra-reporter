package com.runeterrareporter.decks;

import com.runeterrareporter.cards.repository.CardWithData;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DeckWithData extends Deck {
    private final List<CardWithData> cards;

    public DeckWithData(List<CardWithData> cards) {
        this.cards = cards;
    }

    public Set<CardWithData> getChampions() {
        return cards.stream()
                .filter(CardWithData::isChampion)
                .collect(Collectors.toSet());
    }
}
