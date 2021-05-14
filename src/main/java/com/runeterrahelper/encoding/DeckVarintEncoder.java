package com.runeterrahelper.encoding;

import com.runeterrahelper.decks.*;

class DeckVarintEncoder {

    private final DeckSorter deckSorter;
    private final int format = 1;
    private final int version = 3;
    private final VarInt varInt = new VarInt();

    public DeckVarintEncoder(DeckSorter deckSorter) {
        this.deckSorter = deckSorter;
    }

    public VarInt encode(Deck deck) {
        encodeFormatAndVersion();
        SortedDeck sortedDeck = deckSorter.sort(deck);
        encodeXOfs(sortedDeck.getThreeOfs());
        encodeXOfs(sortedDeck.getTwoOfs());
        encodeXOfs(sortedDeck.getOneOfs());
        return varInt;
    }

    private void encodeFormatAndVersion() {
        // The first 4 bits are the format and the last 4 are the version.
        int versionAndFormat = format << 4 | version;
        varInt.add(versionAndFormat);
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
