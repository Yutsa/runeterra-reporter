package com.runeterrahelper.meta.processors.model;

import com.runeterrahelper.archetypes.Archetype;
import com.runeterrahelper.decks.Deck;
import com.runeterrahelper.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArchetypeStat implements Comparable<ArchetypeStat> {

    private final Archetype archetype;
    private double winrate;
    private int numberOfMatches;
    private final List<DeckMetaStat> deckMetaStats = new ArrayList<>();

    /**
     * Creates an {@link ArchetypeStat} from an {@link Archetype} with a given winrate and number of maches.
     * Using this constructor prevents getting detailed winrate and number of matches for each deck of the archetype.
     */
    public ArchetypeStat(Archetype archetype, double winrate, int numberOfMatches) {
        this.archetype = archetype;
        this.winrate = winrate;
        this.numberOfMatches = numberOfMatches;
        archetype.getDecks().forEach(deck -> deckMetaStats.add(new DeckMetaStat(deck, 0, 0)));
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

    public List<Deck> getDecks() {
        return deckMetaStats
                .stream()
                .sorted()
                .map(DeckMetaStat::getDeck)
                .map(Deck.class::cast)
                .collect(Collectors.toList());
    }

    public Archetype getArchetype() {
        return archetype;
    }

    public void addDeckStats(final DeckMetaStat deckMetaStat) {
        deckMetaStats.add(deckMetaStat);
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

    @Override
    public int compareTo(ArchetypeStat archetypeStat) {
        return archetypeStat.getNumberOfMatches() - numberOfMatches;
    }
}
