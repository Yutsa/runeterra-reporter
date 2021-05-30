package com.runeterrahelper.meta.processors.model;

import com.runeterrahelper.decks.DeckWithData;

public class DeckMetaStat {
    private final DeckWithData deck;
    private final int numberOfGamesPlayed;
    private final double winrate;

    public DeckMetaStat(DeckWithData deck, int numberOfGamesPlayed, double winrate) {
        this.deck = deck;
        this.numberOfGamesPlayed = numberOfGamesPlayed;
        this.winrate = winrate;
    }

    public int getNumberOfGamesPlayed() {
        return numberOfGamesPlayed;
    }

    public double getWinrate() {
        return winrate;
    }

    public DeckWithData getDeck() {
        return deck;
    }
}
