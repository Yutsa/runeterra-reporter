package com.runeterrareporter.archetypes;

import com.runeterrareporter.decks.Deck;

/**
 * Process responsible to check if a deck is compatible with a given archetype.
 * <p>
 * The verification is based on the regions of the decks and the number of cards in common.
 */
public class ArchetypeCompatibilityChecker {

  public boolean isArchetypeCompatibleWithDeck(final Archetype archetype, final Deck deck) {
    return archetype.getDecks().stream()
                        .anyMatch(archetypeDeck -> deckAreSimilar(archetypeDeck, deck));
  }

  private boolean deckAreSimilar(final Deck archetypeDeck, final Deck deck) {
    return haveEnoughCardsInCommon(archetypeDeck, deck)
           && haveSameRegions(archetypeDeck, deck);
  }

  private boolean haveEnoughCardsInCommon(final Deck archetypeDeck, final Deck deck) {
    return archetypeDeck.getCards().stream()
                        .filter(card -> deck.getCards().contains(card))
                        .count() >= archetypeDeck.numberOfDifferentCards() / 2;
  }

  private boolean haveSameRegions(final Deck archetypeDeck, final Deck deck) {
    return archetypeDeck.getRegions().equals(deck.getRegions());
  }
}