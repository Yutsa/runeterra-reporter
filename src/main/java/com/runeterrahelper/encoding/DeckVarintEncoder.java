package com.runeterrahelper.encoding;

import com.runeterrahelper.decks.*;

class DeckVarintEncoder {

    private final DeckSorter deckSorter;
    private final VarInt varInt = new VarInt();

    public DeckVarintEncoder(DeckSorter deckSorter) {
        this.deckSorter = deckSorter;
    }

    public VarInt encode(Deck deck) {
        SortedDeck sortedDeck = deckSorter.sort(deck);
        encodeXOfs(sortedDeck.getThreeOfs());
        encodeXOfs(sortedDeck.getTwoOfs());
        encodeXOfs(sortedDeck.getOneOfs());
        return varInt;
    }

    private void encodeXOfs(CardsGroupedByCopies cards) {
        if (!cards.getCards().isEmpty()) {
            varInt.add(cards.numberOfSetRegionCombination());
            cards.retrieveGroups().forEach(this::encodeSetRegionGroup);
        }
    }

    private void encodeSetRegionGroup(SetRegionCardGroup cardGroup) {
        varInt.add(cardGroup.numberOfCards());
        varInt.add(cardGroup.getReleaseSet().getId());
        varInt.add(cardGroup.getRegion().getId());
        cardGroup.getCards().forEach(card -> varInt.add(card.getCardNumber()));
    }
}
