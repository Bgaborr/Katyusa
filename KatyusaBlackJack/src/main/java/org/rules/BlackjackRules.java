package org.rules;

import org.models.Card;
import java.util.List;

public class BlackjackRules implements GameRules {
    @Override
    public GameResult evaluateGame(List<Card> playerCards, List<Card> dealerCards) {
        int playerValue = calculateHandValue(playerCards);
        int dealerValue = calculateHandValue(dealerCards);

        if (playerValue > 21) {
            return new GameResult("Vesztettél, túllépted a 21-et!", false);
        } else if (dealerValue > 21) {
            return new GameResult("Nyertél, a dealer túllépte a 21-et!", true);
        } else if (playerValue > dealerValue) {
            return new GameResult("Nyertél!", true);
        } else if (playerValue == dealerValue) {
            return new GameResult("Döntetlen!", false);
        } else {
            return new GameResult("Vesztettél!", false);
        }
    }

    @Override
    public int calculateHandValue(List<Card> cards) {
        int value = 0;
        int aceCount = 0;

        for (Card card : cards) {
            String val = card.getValue();
            if ("J".equals(val) || "Q".equals(val) || "K".equals(val)) {
                value += 10;
            } else if ("A".equals(val)) {
                aceCount++;
                value += 11;
            } else {
                value += Integer.parseInt(val);
            }
        }

        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }

        return value;
    }

    @Override
    public boolean shouldDealerDraw(List<Card> dealerCards) {
        return calculateHandValue(dealerCards) < 17;
    }
}