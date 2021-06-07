package com.runeterrareporter.mobalytics.datasource;

import com.runeterrareporter.cards.CardWithDataFactory;
import com.runeterrareporter.cards.repository.CardRepository;
import com.runeterrareporter.decks.DeckWithDataFactory;
import com.runeterrareporter.meta.processors.model.DeckMetaStat;
import com.runeterrareporter.utils.MathUtils;

public class DeckMetaStatFactory {
    private final CardRepository cardRepository;

    public DeckMetaStatFactory(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public DeckMetaStat createDeckMetaStat(MobalyticsDeckStat mobalyticsDeckStat) {
        DeckWithDataFactory deckWithDataFactory = new DeckWithDataFactory(new CardWithDataFactory(cardRepository));
        return new DeckMetaStat(deckWithDataFactory.fromCode(mobalyticsDeckStat.getCardsCode()), mobalyticsDeckStat.getMatchesCollected(), computeWinrate(mobalyticsDeckStat));
    }

    private double computeWinrate(MobalyticsDeckStat mobalyticsDeckStat) {
        return MathUtils.roundToTwoDigits(((double) mobalyticsDeckStat.getWins() / mobalyticsDeckStat.getMatchesCollected()) * 100);
    }
}
