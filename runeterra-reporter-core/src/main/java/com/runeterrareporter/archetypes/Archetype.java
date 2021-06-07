package com.runeterrareporter.archetypes;

import com.runeterrareporter.cards.Region;
import com.runeterrareporter.cards.repository.CardWithData;
import com.runeterrareporter.decks.DeckWithData;

import java.util.*;
import java.util.stream.Collectors;

public class Archetype {

    private final Set<DeckWithData> decks = new HashSet<>();

    public Archetype(DeckWithData... decks) {
        addToDecks(decks);
    }

    public Archetype(Collection<DeckWithData> decks) {
        addToDecks(decks);
    }

    public String getName() {
        Set<Region> regions = decks.stream().flatMap(deck -> deck.getRegions().stream()).collect(Collectors.toSet());
        Set<CardWithData> champions = decks.stream().flatMap(deck -> deck.getChampions().stream()).collect(Collectors.toSet());
        StringJoiner sj = new StringJoiner(" ");
        regions.stream()
                .map(Region::getName)
                .sorted()
                .forEach(sj::add);
        champions.stream()
                .map(CardWithData::getName)
                .sorted()
                .forEach(sj::add);
        return sj.toString();
    }

    public Set<DeckWithData> getDecks() {
        return decks;
    }

    public void addToDecks(DeckWithData... decks) {
        this.decks.addAll(Arrays.asList(decks));
    }

    public void addToDecks(Collection<DeckWithData> decks) {
        this.decks.addAll(decks);
    }

}
