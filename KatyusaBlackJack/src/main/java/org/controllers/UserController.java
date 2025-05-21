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
    private UserValidator validator;
    private  UserDAO userDAO;
    private  ErrorMessageService errorService;
    private Stage stage;

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField emailField;
    @FXML private Label errorLabel;

    @FXML
    public void initialize() {
        if (validator == null) {
            validator = new UserValidator();
        }
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        if (errorService == null) {
            errorService = new ErrorMessageService();
        }
    }

    public UserController() {
    }
    public UserController(UserValidator validator, UserDAO userDAO, ErrorMessageService errorService) {
        this.validator = validator;
        this.userDAO = userDAO;
        this.errorService = errorService;
    }
    public void setValidator(UserValidator validator) {
        this.validator = validator;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setErrorService(ErrorMessageService errorService) {
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


