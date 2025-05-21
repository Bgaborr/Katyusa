package org.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneService {
    private static final String ICON_PATH = "/image/bj.png";

    public void loadModal(String fxmlPath, String title, Stage owner, double x, double y) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.getIcons().add(new Image(getClass().getResourceAsStream(ICON_PATH)));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setX(x);
        stage.setY(y);
        stage.setResizable(false);
        stage.showAndWait();
    }

    public void switchScene(String fxmlPath, Stage stage, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.getIcons().add(new Image(getClass().getResourceAsStream(ICON_PATH)));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setX(100);
        stage.setY(50);
        stage.show();
    }
}