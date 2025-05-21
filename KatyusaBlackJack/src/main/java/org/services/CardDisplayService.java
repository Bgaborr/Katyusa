package org.services;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.models.Card;

import java.net.URL;

public class CardDisplayService {
    public Node createCardNode(Card card) {
        String value = card.getValue().toLowerCase();
        String suit = convertSuit(card.getSuit().toLowerCase());

        String fileName = value + "_of_" + suit + ".png";
        String path = "/cards/" + fileName;
        URL imageUrl = getClass().getResource(path);

        if (imageUrl == null) {
            return createFallbackCardNode(card);
        }

        Image image = new Image(imageUrl.toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(80);
        imageView.setPreserveRatio(true);

        return imageView;
    }

    private String convertSuit(String suit) {
        return switch (suit) {
            case "♠" -> "spades";
            case "♥" -> "hearts";
            case "♦" -> "diamonds";
            case "♣" -> "clubs";
            default -> suit;
        };
    }

    private Node createFallbackCardNode(Card card) {
        Text cardText = new Text(card.toString());
        cardText.setStyle("-fx-font-size: 20px; -fx-fill: white;");
        return cardText;
    }
}