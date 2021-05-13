package com.runeterrahelper.encoding;

import com.runeterrahelper.decks.*;

public class DeckVarintEncoder {

    private final DeckSorter deckSorter;
    private final VarInt varInt = new VarInt();

    public DeckVarintEncoder(DeckSorter deckSorter) {
        this.deckSorter = deckSorter;
    }

    public VarInt encode(Deck deck) {
        SortedDeck sortedDeck = deckSorter.sort(deck);
        CardsGroupedByCopies threeOfs = sortedDeck.getThreeOfs();
        varInt.add(threeOfs.numberOfSetRegionCombination());
        threeOfs.retrieveGroups().forEach(this::encodeCardGroup);
        return varInt;
    }

    private void encodeCardGroup(SetRegionCardGroup cardGroup) {
        varInt.add(cardGroup.numberOfCards());
        varInt.add(cardGroup.getReleaseSet().getId());
        varInt.add(cardGroup.getRegion().getId());
        cardGroup.getCards().forEach(card -> varInt.add(card.getCardNumber()));
    }
}
