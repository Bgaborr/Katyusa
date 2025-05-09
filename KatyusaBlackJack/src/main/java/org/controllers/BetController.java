package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class BetController {
    @FXML
    private Label totalLabel;
    private int totalBet = 0;

    private GameController mainController;

    public void setMainController(GameController controller) {
        this.mainController = controller;
    }

    @FXML
    private void handleChipClick(ActionEvent event) {
        Button button = (Button) event.getSource();
        totalBet += Integer.parseInt(button.getText());
        totalLabel.setText("Össz tét: " + totalBet);
    }

    @FXML
    private void acceptBet(ActionEvent event) {
        if (mainController != null) {
            mainController.updateBet(totalBet);
        }
        ((Stage)((Button)event.getSource()).getScene().getWindow()).close();
    }
}