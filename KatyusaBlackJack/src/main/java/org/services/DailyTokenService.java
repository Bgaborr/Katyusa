package org.services;

import org.database.DatabaseManager;
import org.database.TokenDAO;
import org.models.Token;
import org.session.Session;

import java.sql.Connection;
import java.time.LocalDate;

public class DailyTokenService {
    private final AlertService alertService = new AlertService();

    public void checkAndClaimDailyToken() {
        int userId = Session.getUser().getId();

        try (Connection conn = DatabaseManager.getConnection()) {
            TokenDAO tokenDAO = new TokenDAO(conn);
            Token token = tokenDAO.getTokensByPlayerId(userId);

            LocalDate today = LocalDate.now();

            if (token == null) {
                tokenDAO.insertNewToken(userId, 100, today);
                Session.setTokens(100);
                alertService.showAlert("Napi jutalom", "100 zsetont kaptál!");
            } else if (token.getLastDailyClaim() == null || !token.getLastDailyClaim().isEqual(today)) {
                int newTokens = token.getTokens() + 100;
                token.setTokens(newTokens);
                token.setLastDailyClaim(today);
                tokenDAO.updateToken(token);
                Session.setTokens(newTokens);
                alertService.showAlert("Napi jutalom", "100 zsetont kaptál!");
            } else {
                alertService.showAlert("Napi jutalom", "Ma már átvetted a napi jutalmat.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            alertService.showAlert("Hiba", "Nem sikerült ellenőrizni a napi jutalmat.");
        }
    }
}
