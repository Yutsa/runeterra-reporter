package com.runeterrahelper.meta.processors.model;

import com.runeterrahelper.decks.Deck;

public class DeckMetaStat {
    private final Deck deck;
    private final int numberOfGamesPlayed;
    private final int winrate;

    public DeckMetaStat(Deck deck, int numberOfGamesPlayed, int winrate) {
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

    public Deck getDeck() {
        return deck;
    }
}
