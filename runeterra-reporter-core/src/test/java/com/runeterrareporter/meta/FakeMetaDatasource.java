package com.runeterrareporter.meta;

import com.runeterrareporter.decks.DeckWithDataFactory;
import com.runeterrareporter.meta.datasources.MetaDatasource;
import com.runeterrareporter.meta.processors.model.DeckMetaStat;

import java.util.HashSet;
import java.util.Set;

public class FakeMetaDatasource implements MetaDatasource {
    private final Set<DeckMetaStat> decksPlayed = new HashSet<>();
    private final DeckWithDataFactory deckWithDataFactory;

    public FakeMetaDatasource(DeckWithDataFactory deckWithDataFactory) {
        this.deckWithDataFactory = deckWithDataFactory;
    }

    @Override
    public Set<DeckMetaStat> retrieveDecks() {
        return decksPlayed;
    }

    public void addDeck(String deckCode, int numberOfGames, int winrate) {
        decksPlayed.add(new DeckMetaStat(deckWithDataFactory.fromCode(deckCode), numberOfGames, winrate));
    }
}
