package org.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main-view.fxml"));
        Parent root = loader.load();

        stage.setScene(new Scene(root, 500, 720));
        stage.setTitle("Black Jack");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/image/bj.png")));
        stage.setResizable(false);
        stage.setX(100);
        stage.setY(50);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}