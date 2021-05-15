package com.runeterrahelper.matches;

import com.runeterrahelper.decks.Deck;

class MatchRecap {
    private final Player player1;
    private final Player player2;

    public MatchRecap(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Deck firstDeckCode() {
        return player1.getDeck();
    }

    public Deck secondDeckCode() {
        return player2.getDeck();
    }

    public Player victoriousPlayer() {
        return player1.hasWon() ? player1 : player2;
    }
}
