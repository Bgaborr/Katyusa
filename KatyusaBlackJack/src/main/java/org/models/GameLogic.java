package org.models;

import java.util.List;

public class GameLogic {

    public int calculateHandValue(List<Card> hand) {
        int value = 0;
        int numAces = 0;

        for (Card card : hand) {
            String cardValue = card.getValue();
            switch (cardValue) {
                case "A":
                    value += 11;
                    numAces++;
                    break;
                case "K":
                case "Q":
                case "J":
                case "10":
                    value += 10;
                    break;
                default:
                    value += Integer.parseInt(cardValue);
            }
        }

        while (value > 21 && numAces > 0) {
            value -= 10;
            numAces--;
        }

        return value;
    }

    public String evaluateWinner(int playerValue, int dealerValue) {
        if (playerValue > 21) return "Vesztettél!";
        if (dealerValue > 21 || playerValue > dealerValue) return "Nyertél!";
        if (playerValue == dealerValue) return "Döntetlen!";
        return "Vesztettél!";
    }
}
