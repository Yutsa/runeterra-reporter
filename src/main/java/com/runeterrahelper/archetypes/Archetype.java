package com.runeterrahelper.archetypes;

import com.runeterrahelper.cards.Card;
import com.runeterrahelper.cards.Region;
import com.runeterrahelper.decks.DeckWithData;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Archetype {

    private final Set<DeckWithData> decks = new HashSet<>();

    public Archetype(DeckWithData... decks) {
        addToDecks(decks);
    }

    public String getName() {
        Set<Region> regions = decks.stream().flatMap(deck -> deck.getRegions().stream()).collect(Collectors.toSet());
        Set<Card> champions = decks.stream().flatMap(deck -> deck.getChampions().stream()).collect(Collectors.toSet());
        StringJoiner sj = new StringJoiner(" ");
        regions.stream()
                .map(Region::getName)
                .forEach(sj::add);
        champions.stream()
                .map(Card::getName)
                .forEach(sj::add);
        return sj.toString();
    }

    public Set<DeckWithData> getDecks() {
        return decks;
    }

    public void addToDecks(DeckWithData... decks) {
        this.decks.addAll(Arrays.asList(decks));
    }

}
