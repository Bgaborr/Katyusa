package org.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.classes.UserValidator;
import org.database.UserDAO;
import org.models.User;
import org.services.ErrorMessageService;


public class UserController {
    private final UserValidator validator;
    private final UserDAO userDAO;
    private final ErrorMessageService errorService;
    private Stage stage;

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField emailField;
    @FXML private Label errorLabel;

    // Dependency injection
    public UserController(UserValidator validator, UserDAO userDAO, ErrorMessageService errorService) {
        this.validator = validator;
        this.userDAO = userDAO;
        this.errorService = errorService;
    }

    @FXML
    private void register() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();

        if (!validator.isValidEmail(email)) {
            errorLabel.setText(errorService.getEmailError());
            return;
        }

        if (!validator.isValidPassword(password)) {
            errorLabel.setText(errorService.getPasswordError());
            return;
        }

        User user = new User(username, password, email);
        if (userDAO.register(user)) {
            closeWindow();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    private void closeWindow() {
        if (stage != null) stage.close();
    }
}


