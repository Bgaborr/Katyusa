package org.models;

import java.util.*;

public class Deck {
    private final Stack<Card> cards = new Stack<>();

    public Deck() {
        String[] suits = {"♠", "♥", "♦", "♣"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        for (String suit : suits) {
            for (String value : values) {
                cards.push(new Card(value, suit));
            }
        }
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.isEmpty() ? null : cards.pop();
    }
}

