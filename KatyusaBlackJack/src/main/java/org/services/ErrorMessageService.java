package org.services;

public class ErrorMessageService {
    public String getEmailError() {
        return "Hibás email formátum!";
    }

    public String getPasswordError() {
        return "A jelszónak legalább 8 karakteresnek kell lennie!";
    }
}
