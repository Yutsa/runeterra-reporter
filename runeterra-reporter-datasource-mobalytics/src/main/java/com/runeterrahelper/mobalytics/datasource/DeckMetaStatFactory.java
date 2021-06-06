package com.runeterrahelper.mobalytics.datasource;

import com.runeterrahelper.cards.CardWithDataFactory;
import com.runeterrahelper.cards.repository.CardRepository;
import com.runeterrahelper.decks.DeckWithDataFactory;
import com.runeterrahelper.meta.processors.model.DeckMetaStat;
import com.runeterrahelper.utils.MathUtils;

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
