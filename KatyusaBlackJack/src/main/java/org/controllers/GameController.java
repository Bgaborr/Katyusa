package org.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.models.Card;
import org.models.GameModel;
import org.session.Session;
import org.rules.BlackjackRules;
import org.rules.GameResult;
import org.rules.GameRules;
import org.services.CardDisplayService;
import org.services.AlertService;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GameController implements Observer {
    @FXML private Label betLabel;
    @FXML private Label betErrorLabel;
    @FXML private Button draw_btn;
    @FXML private Button stop_btn;
    @FXML private HBox playerCardsBox;
    @FXML private HBox dealerCardsBox;
    @FXML private Label dealerLabel;

    private final GameModel gameModel = new GameModel();
    private final CardDisplayService cardDisplay = new CardDisplayService();
    private final GameRules gameRules = new BlackjackRules();
    private final AlertService alertService = new AlertService();

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
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/image/bj.png")));
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
    public void starGame() {
        if (betLabel.getText().equals("Tét: 0")) {
            betErrorLabel.setText("Kezdés előtt rakj tétet!");
        } else {
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
        int playerValue = gameRules.calculateHandValue(gameModel.getPlayerCards());

        if (playerValue > 21) {
            alertService.showAlert("Vesztettél!", "Túllépted a 21 pontot.");
            endGame();
        } else if (playerValue == 21) {
            onPlayerStand();
        }
    }

    @FXML
    public void onPlayerStand() {
        while (gameRules.shouldDealerDraw(gameModel.getDealerCards())) {
            gameModel.drawCardForDealer();
        }

        evaluateGame();
    }

    @FXML
    private void onExitButtonClick() {
        int userId = Session.getUser().getId();
        int tokens = Session.getTokens();

        ExitHandler handler = new ExitHandler(userId, tokens);
        handler.handleExit();

        Platform.exit();
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
        GameResult result = gameRules.evaluateGame(
            gameModel.getPlayerCards(), 
            gameModel.getDealerCards()
        );
        
        alertService.showAlert("Eredmény", result.getMessage());
        endGame();
    }

    private void endGame() {
        gameModel.resetGame();
        playerCardsBox.getChildren().clear();
        dealerCardsBox.getChildren().clear();
        betLabel.setText("Tét: 0");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof List<?> list && list.size() == 2) {
            String target = (String) list.get(0);
            Card card = (Card) list.get(1);

            Node cardNode = cardDisplay.createCardNode(card);

            if ("player".equals(target)) {
                playerCardsBox.getChildren().add(cardNode);
            } else if ("dealer".equals(target)) {
                dealerCardsBox.getChildren().add(cardNode);
            }
        }
    }
}