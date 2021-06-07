package com.runeterrareporter.encoding;

import com.runeterrareporter.cards.Card;
import com.runeterrareporter.cards.Region;
import com.runeterrareporter.decks.CardCopies;
import com.runeterrareporter.decks.Deck;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

class DeckVarintEncoder {

    private final DeckSorter deckSorter;
    private final int format = 1;
    private final int version = 3;
    private VarInt varInt = new VarInt();

    public DeckVarintEncoder(DeckSorter deckSorter) {
        this.deckSorter = deckSorter;
    }

    public VarInt encode(Deck deck) {
        varInt = new VarInt();
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
        varInt.add(cards.numberOfSetRegionCombination());
        cards.retrieveGroups().forEach(this::encodeSetRegionGroup);
    }

    private void encodeSetRegionGroup(SetRegionCardGroup cardGroup) {
        varInt.add(cardGroup.numberOfCards());
        varInt.add(cardGroup.getReleaseSet().getId());
        varInt.add(cardGroup.getRegion().getId());
        cardGroup.getCards().forEach(card -> varInt.add(card.getCardNumber()));
    }

    public Deck decode(ArrayList<Integer> bytes) {
        Deck deck = new Deck();
        VarInt varInt = new VarInt(bytes);
        int formatAndVersion = varInt.pop();
        decodeXOfs(varInt, deck, 3);
        decodeXOfs(varInt, deck, 2);
        decodeXOfs(varInt, deck, 1);
        return deck;
    }

    private void decodeXOfs(VarInt varInt, Deck deck, int XOfs) {
        int numberOfSetRegionGroups = varInt.pop();
        for (int i = 0; i < numberOfSetRegionGroups; i++) {
            int numberOfCards = varInt.pop();
            String releaseSetId = formatReleaseSet(varInt.pop());
            String regionId = formatRegionId(varInt.pop());
            for (int j = 0; j < numberOfCards; j++) {
                String cardCode = formatCardCode(varInt.pop());
                deck.addCard(new CardCopies(XOfs, Card.fromCode(releaseSetId + regionId + cardCode)));
            }
        }
    }

    private String formatCardCode(int cardCode) {
        return StringUtils.leftPad(String.valueOf(cardCode), 3, "0");
    }

    private String formatRegionId(int regionId) {
        return Region.fromId(regionId).getRegionCode();
    }

    private String formatReleaseSet(int releaseSetId) {
        return StringUtils.leftPad(String.valueOf(releaseSetId), 2, "0");
    }
}
