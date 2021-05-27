package com.runeterrahelper.meta.processors.model;

import com.runeterrahelper.decks.DeckWithData;

public class DeckMetaStat {
    private final DeckWithData deck;
    private final int numberOfGamesPlayed;
    private final int winrate;

    public DeckMetaStat(DeckWithData deck, int numberOfGamesPlayed, int winrate) {
        this.deck = deck;
        this.numberOfGamesPlayed = numberOfGamesPlayed;
        this.winrate = winrate;
    }

    public int getNumberOfGamesPlayed() {
        return numberOfGamesPlayed;
    }

    public int getWinrate() {
        return winrate;
    }

    public DeckWithData getDeck() {
        return deck;
    }
}
