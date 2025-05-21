package org.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.database.UserDAO;
import org.models.Token;
import org.models.User;
import org.database.DatabaseManager;

import javax.swing.plaf.basic.BasicButtonUI;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button login_btn;
    @FXML   private Label errorLabel;
    private Stage stage;
    private SceneController sceneController = new SceneController();
    @FXML
    private void initialize() {
    }
    private Stage mainStage;
    private Stage loginStage;

    @FXML
    private void login() {
        try {
            String username = usernameField.getText();
            String password = passwordField.getText();

            UserDAO userDAO = new UserDAO();
            User user = userDAO.login(username, password);

            if (user != null) {
                System.out.println("Sikeres bejelentkezés!");
                org.session.Session.setUser(user);

                org.database.TokenDAO tokenDAO = new org.database.TokenDAO(DatabaseManager.getConnection());
                Token token = tokenDAO.getTokensByPlayerId(user.getId());
                int tokens = token.getTokens();
                org.session.Session.setTokens(tokens);

                SceneController sceneController = new SceneController();
                sceneController.switchToGameView(mainStage);
                loginStage.close();

            } else {
                errorLabel.setText("Hibás felhasználónév vagy jelszó!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Hiba történt a bejelentkezés során.");
        }
    }

    private void closeWindow() {
        if (stage != null) {
            stage.close();
        }
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setStage(Stage loginStage) {
        this.loginStage = loginStage;
    }
}
