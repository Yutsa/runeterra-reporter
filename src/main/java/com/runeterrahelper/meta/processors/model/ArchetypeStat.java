package com.runeterrahelper.meta.processors.model;

import com.runeterrahelper.archetypes.Archetype;
import com.runeterrahelper.decks.Deck;
import com.runeterrahelper.utils.MathUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class ArchetypeStat {

    private final Archetype archetype;
    private double winrate;
    private int numberOfMatches;

    public ArchetypeStat(Archetype archetype, double winrate, int numberOfMatches) {
        this.archetype = archetype;
        this.winrate = winrate;
        this.numberOfMatches = numberOfMatches;
    }

    public ArchetypeStat(Archetype archetype) {
        this.archetype = archetype;
        winrate = 0;
        numberOfMatches = 0;
    }

    public String getName() {
        return archetype.getName();
    }

    public double getWinrate() {
        return winrate;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public Set<Deck> getDecks() {
        return archetype.getDecks()
                .stream()
                .map(Deck.class::cast)
                .collect(Collectors.toSet());
    }

    public Archetype getArchetype() {
        return archetype;
    }

    public void addDeckStats(final DeckMetaStat deckMetaStat) {
        archetype.addToDecks(deckMetaStat.getDeck());
        double currentPonderation = (double) numberOfMatches / (numberOfMatches + deckMetaStat.getNumberOfGamesPlayed());
        double newDeckPonderation = 1 - currentPonderation;
        this.winrate = MathUtils.roundToTwoDigits(
                winrate * currentPonderation + deckMetaStat.getWinrate() * newDeckPonderation);
        numberOfMatches += deckMetaStat.getNumberOfGamesPlayed();
    }

    @Override
    public String toString() {
        return getName() + " [ " + numberOfMatches + " games / "
                + winrate + "% winrate / "
                + "decks : "
                + getDecks().stream().map(Deck::toDeckCode).collect(Collectors.joining(", "));
    }
}
