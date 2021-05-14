package com.runeterrahelper.encoding;

import com.runeterrahelper.cards.Card;
import com.runeterrahelper.decks.CardCopies;
import com.runeterrahelper.decks.Deck;

import org.apache.commons.codec.binary.Base32;

import java.util.List;

public class DeckEncoder {
    public String encode(Deck deck) {
        VarInt varInt = new DeckVarintEncoder(new DeckSorter()).encode(deck);
        List<Integer> values = varInt.getValues();
        byte[] output = new byte[values.size()];
        for (int i = 0; i < values.size(); i++)
        {
            output[i] = values.get(i).byteValue();
        }
        return new Base32().encodeAsString(output).replace("=", "");
    }

    public Deck decode(String deckCode) {
        CardCopies cardCopies = CardCopies.fromString("3:01SI015");
        Deck deck = new Deck();
        deck.addCardCopies(List.of(cardCopies));
        return deck;
    }
}
