package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.models.Card;
import org.models.GameModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import java.util.Observable;
import java.util.Observer;

public class GameController implements Observer {

    @FXML
    private Label betLabel;

    @FXML
    private  Label betErrorLabel;

    @FXML
    private Button draw_btn;

    @FXML
    private Button stop_btn;

    @FXML
    private HBox playerCardsBox;

    @FXML
    private HBox dealerCardsBox;

    @FXML
    private Label dealerLabel;

    private final GameModel gameModel = new GameModel();

    @FXML
    public void initialize() {
        gameModel.addObserver(this);
        betLabel.setText("Tét: 0");
        gameModel.resetGame();


        updateBet(0);
    }

    public void showBetView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/bet-view.fxml"));
        Parent root = loader.load();

        BetController betController = loader.getController();
        betController.setMainController(this);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Tét");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/bj.png"))));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.setX(200);
        stage.setY(400);
        stage.setResizable(false);
        stage.showAndWait();
    }

    public void updateBet(int amount) {
        betLabel.setText("Tét: " + amount);
        betErrorLabel.setText("");
    }
    @FXML
    public void starGame(){
        if (betLabel.getText().equals("Tét: 0")){
            betErrorLabel.setText("Kezdés előtt rakj tétet!");
        }
        else {
            dealerLabel.setText("Osztó");
            stop_btn.setDisable(false);
            draw_btn.setDisable(false);
            gameModel.drawCardForPlayer();
            gameModel.drawCardForPlayer();
            gameModel.drawCardForDealer();
        }
    }
    @FXML
    public void onPlayerDraw() {
        gameModel.drawCardForPlayer();
        int playerValue = calculateHandValue(gameModel.getPlayerCards());

        if (playerValue > 21) {
            showAlert("Vesztettél!", "Túllépted a 21 pontot.");
            endGame();
        } else if (playerValue == 21) {
            onPlayerStand();
        }
    }

    @FXML
    public void onPlayerStand() {
        int dealerValue = calculateHandValue(gameModel.getDealerCards());

        while (dealerValue < 17) {
            gameModel.drawCardForDealer();
            dealerValue = calculateHandValue(gameModel.getDealerCards());
        }

        evaluateGame();
    }

    private void evaluateGame() {
        int playerValue = calculateHandValue(gameModel.getPlayerCards());
        int dealerValue = calculateHandValue(gameModel.getDealerCards());

        String result;
        if (playerValue > 21) {
            result = "Vesztettél, túllépted a 21-et!";
        } else if (dealerValue > 21) {
            result = "Nyertél, a dealer túllépte a 21-et!";
        } else if (playerValue > dealerValue) {
            result = "Nyertél!";
        } else if (playerValue == dealerValue) {
            result = "Döntetlen!";
        } else {
            result = "Vesztettél!";
        }

        showAlert("Eredmény", result);
        endGame();

    }

    private void endGame() {
        gameModel.resetGame();
        playerCardsBox.getChildren().clear();
        dealerCardsBox.getChildren().clear();
        betLabel.setText("Tét: 0");
        starGame();
    }

    private int calculateHandValue(List<Card> cards) {
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

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof List<?> list && list.size() == 2) {
            String target = (String) list.get(0);
            Card card = (Card) list.get(1);

            Text cardText = new Text(card.toString());
            cardText.setStyle("-fx-font-size: 20px; -fx-fill: white;");

            String value = card.getValue().toLowerCase();
            String suit = card.getSuit().toLowerCase();
            switch (suit){
                case "♠": suit="spades";
                case "♥": suit="hearts";
                case "♦": suit="diamonds";
                case "♣": suit="clubs";
            }
            String fileName = value+"_of_"+suit+".png";
            System.out.println(fileName);
            String path = "/cards/" + fileName;
            URL imageUrl = getClass().getResource(path);


            Image image = new Image(imageUrl.toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(80);
            imageView.setPreserveRatio(true);

            if ("player".equals(target)) {
                playerCardsBox.getChildren().add(imageView);
            } else if ("dealer".equals(target)) {
                dealerCardsBox.getChildren().add(imageView);
            }
        }
    }
}