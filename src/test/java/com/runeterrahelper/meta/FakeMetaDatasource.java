package com.runeterrahelper.meta;

import com.runeterrahelper.cards.CardWithDataFactory;
import com.runeterrahelper.cards.repository.FakeCardRepository;
import com.runeterrahelper.decks.DeckWithDataFactory;
import com.runeterrahelper.meta.datasources.MetaDatasource;
import com.runeterrahelper.meta.processors.model.DeckMetaStat;

import java.util.HashSet;
import java.util.Set;

public class FakeMetaDatasource implements MetaDatasource {
    private final Set<DeckMetaStat> decksPlayed = new HashSet<>();
    private final DeckWithDataFactory deckWithDataFactory = new DeckWithDataFactory(new CardWithDataFactory(new FakeCardRepository()));

    @Override
    public Set<DeckMetaStat> retrieveDecks() {
        return decksPlayed;
    }

    public void addDeck(String deckCode, int numberOfGames, int winrate) {
        decksPlayed.add(new DeckMetaStat(deckWithDataFactory.fromCode(deckCode), numberOfGames, winrate));
    }
}
