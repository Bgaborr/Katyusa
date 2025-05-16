package org.models;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class GameModel extends Observable {


    private final List<Card> playerCards = new ArrayList<>();
    private final List<Card> dealerCards = new ArrayList<>();

    private final List<Card> deck = new ArrayList<>();

    public GameModel() {
        initDeck();
        shuffleDeck();
    }

    private void initDeck() {
        String[] suits = {"♠", "♥", "♦", "♣"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        for (String suit : suits) {
            for (String value : values) {
                deck.add(new Card(value, suit));
            }
        }
    }

    private void shuffleDeck() {
        Random random = new Random();
        for (int i = deck.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Card temp = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, temp);
        }
    }

    private Card drawCard() {
        if (deck.isEmpty()) {
            initDeck();
            shuffleDeck();
        }
        return deck.remove(deck.size() - 1);
    }

    public Card drawCardForPlayer() {
        Card card = drawCard();
        playerCards.add(card);
        setChanged();
        notifyObservers(List.of("player", card));
        return card;
    }

    public Card drawCardForDealer() {
        Card card = drawCard();
        dealerCards.add(card);
        setChanged();
        notifyObservers(List.of("dealer", card));
        return card;
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public List<Card> getDealerCards() {
        return dealerCards;
    }

    public void resetGame() {
        playerCards.clear();
        dealerCards.clear();
        deck.clear();
        initDeck();
        shuffleDeck();
    }
}
