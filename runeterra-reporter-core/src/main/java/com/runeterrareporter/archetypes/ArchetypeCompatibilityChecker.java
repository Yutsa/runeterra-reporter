package com.runeterrareporter.archetypes;

import com.runeterrareporter.cards.Card;
import com.runeterrareporter.cards.repository.CardWithData;
import com.runeterrareporter.decks.CardCopies;
import com.runeterrareporter.decks.Deck;
import com.runeterrareporter.decks.DeckWithData;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Process responsible to check if a deck is compatible with a given archetype.
 * <p>
 * The verification is based on the regions of the decks and the number of cards in common.
 */
public class ArchetypeCompatibilityChecker {

    public boolean isArchetypeCompatibleWithDeck(final Archetype archetype, final DeckWithData deck) {
        return archetype.getDecks().stream()
                .filter(archetypeDeck -> deckAreSimilar(archetypeDeck, deck))
                .count() > (archetype.getDecks().size() / 2);
    }

    private boolean deckAreSimilar(final DeckWithData archetypeDeck, final DeckWithData deck) {
        return haveEnoughCardsInCommon(archetypeDeck, deck)
                && haveSameRegions(archetypeDeck, deck);
    }

    private boolean haveEnoughCardsInCommon(final DeckWithData archetypeDeck, final DeckWithData deck) {
        return computeSimilarityScore(
                archetypeDeck.getCardsWithData().stream()
                        .filter(card -> deck.getCardsWithData().contains(card))
                        .collect(Collectors.toList()))
                >= archetypeDeck.numberOfDifferentCards() * 0.85;
    }

    private double computeSimilarityScore(Collection<CardWithData> cards) {
        double result = 0.0;
        for (CardWithData card : cards) {
            if (card.isChampion()) {
                result += 2;
            } else {
                result += 1;
            }
        }
        return result;
    }

    private boolean haveSameRegions(final Deck archetypeDeck, final Deck deck) {
        return archetypeDeck.getRegions().equals(deck.getRegions());
    }
}