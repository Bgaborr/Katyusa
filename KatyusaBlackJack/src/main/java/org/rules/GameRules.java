package org.rules;

import org.models.Card;
import java.util.List;

public interface GameRules {
    GameResult evaluateGame(List<Card> playerCards, List<Card> dealerCards);
    int calculateHandValue(List<Card> cards);
    boolean shouldDealerDraw(List<Card> dealerCards);
}