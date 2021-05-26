package com.runeterrahelper.matches;

import java.util.Set;

import com.runeterrahelper.cards.Region;
import com.runeterrahelper.decks.Deck;

class MatchRecap {
    private final Player player1;
    private final Player player2;

    public MatchRecap(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Deck player1Deck() {
        return player1.getDeck();
    }

    public Deck player2Deck() {
        return player2.getDeck();
    }

    public Set<Region> player1Regions() {
        return player1.getRegions();
    }

    public Set<Region> player2Regions() {
        return player2.getRegions();
    }

    public Player victoriousPlayer() {
        return player1.hasWon() ? player1 : player2;
    }
}
