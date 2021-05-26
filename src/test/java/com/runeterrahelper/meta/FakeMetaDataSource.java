package com.runeterrahelper.meta;

import com.runeterrahelper.decks.Deck;
import com.runeterrahelper.meta.datasources.MetaDataSource;
import com.runeterrahelper.meta.processors.model.Archetype;
import com.runeterrahelper.meta.processors.model.DeckMetaStat;

import java.util.HashSet;
import java.util.Set;

public class FakeMetaDataSource implements MetaDataSource {
    private final Set<DeckMetaStat> decksPlayed = new HashSet<>();

    @Override
    public Set<Archetype> retrieveArchetypes() {
        return null;
    }

    @Override
    public Set<DeckMetaStat> retrieveDecks() {
        return decksPlayed;
    }

    public void addDeck(String deckCode, int numberOfGames, int winrate) {
        decksPlayed.add(new DeckMetaStat(Deck.fromCode(deckCode), numberOfGames, winrate));
    }
}
