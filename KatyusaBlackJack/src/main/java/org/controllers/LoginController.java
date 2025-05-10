package org.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.database.UserDAO;
import org.models.User;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML   private Label errorLabel;
    private Stage stage;
    private SceneController sceneController = new SceneController();
    @FXML
    private void initialize() {
    }

    @FXML
    private void login() {
        try {
            String username = usernameField.getText();
            String password = passwordField.getText();

            UserDAO userDAO = new UserDAO();
            User user = userDAO.login(username, password);

            if (user != null) {
                System.out.println("Sikeres bejelentkezés!");
                sceneController.switchToGameView(stage);
            } else {
                errorLabel.setText("Hibás felhasználónév vagy jelszó!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Hiba történt a bejelentkezés során.");
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void closeWindow() {
        if (stage != null) {
            stage.close();
        }
    }


}
