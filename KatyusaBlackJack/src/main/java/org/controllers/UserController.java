package org.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.database.UserDAO;
import org.models.User;

public class UserController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField emailField;
    @FXML private Button registerButton;

    @FXML
    private void initialize() {
        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);

            UserDAO userDAO = new UserDAO();
            boolean success = userDAO.register(user);

            if (success) {
                System.out.println("Sikeres regisztráció!");

            } else {
                System.out.println("Hiba a regisztrációnál!");
            }
        });
    }
}
