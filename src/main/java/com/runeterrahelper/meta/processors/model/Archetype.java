package com.runeterrahelper.meta.processors.model;

import com.runeterrahelper.cards.Region;
import com.runeterrahelper.decks.Deck;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Archetype {
    private final String name;
    private final Set<Region> regions;
    private final double winrate;
    private final int numberOfMatches;
    private final Set<Deck> decks = new HashSet<>();

    public Archetype(String name, Set<Region> regions, double winrate, int numberOfMatches) {
        this.name = name;
        this.regions = regions;
        this.winrate = winrate;
        this.numberOfMatches = numberOfMatches;
    }

    public String getName() {
        return name;
    }

    public Set<Region> getRegions() {
        return regions;
    }

    public double getWinrate() {
        return winrate;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public Set<Deck> getDecks() {
        return decks;
    }

    public void addToDecks(Deck... decks) {
        this.decks.addAll(Arrays.asList(decks));
    }
}
