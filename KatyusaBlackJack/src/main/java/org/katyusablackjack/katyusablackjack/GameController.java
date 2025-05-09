package org.katyusablackjack.katyusablackjack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {
    @FXML
    public Label betLabel;

    public void showBetView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("bet-view.fxml"));
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
    }
}