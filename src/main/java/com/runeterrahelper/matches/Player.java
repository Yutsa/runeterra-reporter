package com.runeterrahelper.matches;

import com.runeterrahelper.cards.Region;
import com.runeterrahelper.decks.Deck;

import java.util.Set;

class Player {
    private Deck deck;
    private boolean won;

    public void won() {
        won = true;
    }

    public void lost() {
        won = false;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck() {
        return deck;
    }

    public boolean hasWon() {
        return won;
    }

    public Set<Region> getRegions() {
        return deck.getRegions();
    }
}
