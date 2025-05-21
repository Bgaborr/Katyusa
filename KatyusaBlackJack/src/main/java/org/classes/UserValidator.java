package org.classes;

public class UserValidator {
    public boolean isValidEmail(String email) {
        return email != null && email.matches("^\\S+@\\S+\\.\\S+$");
    }

    public boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }
}
