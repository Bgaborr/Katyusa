package org.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.database.UserDAO;
import org.models.User;


public class UserController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField emailField;
    @FXML private Button registerButton;
    @FXML private Label  errorLabel;
    private Stage stage;

    @FXML
    private void initialize() {
    }

    @FXML
    private void register() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();


        if (!isValidEmail(email)) {
            errorLabel.setText("Hibás email formátum!");
            return;
        }

        if (!isValidPassword(password)) {
            errorLabel.setText("A jelszónak legalább 8 karakteresnek kell lennie!");
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        UserDAO userDAO = new UserDAO();
        boolean success = userDAO.register(user);

        if (success) {
            System.out.println("Sikeres regisztráció!");
            closeWindow();
        } else {
            System.out.println("Hiba a regisztrációnál!");
        }
    }
    public boolean isValidEmail(String email) {
        return email != null && email.matches("^\\S+@\\S+\\.\\S+$");
    }

    public boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
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