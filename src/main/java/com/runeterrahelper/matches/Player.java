package com.runeterrahelper.matches;

import com.runeterrahelper.cards.Region;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Player {
    private String deck;
    private boolean won;
    private final Set<Region> regions = new HashSet<>();

    public void usesDeck(String deckCode) {
        deck = deckCode;
    }

    public void won() {
        won = true;
    }

    public String getDeck() {
        return deck;
    }

    public boolean hasWon() {
        return won;
    }

    public Set<Region> getRegions() {
        return regions;
    }

    public void addToRegions(Region... regions) {
        this.regions.addAll(Arrays.asList(regions));
    }
}
