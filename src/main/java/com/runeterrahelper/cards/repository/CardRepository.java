package com.runeterrahelper.cards.repository;

import com.runeterrahelper.cards.Card;

public interface CardRepository {
    Card getCardFromCode(String cardCode);
}
