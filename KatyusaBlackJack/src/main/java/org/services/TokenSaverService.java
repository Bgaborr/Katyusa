package org.services;

import org.database.TokenDAO;
import org.database.DatabaseManager;
import org.models.Token;

import java.sql.Connection;

public class TokenSaverService {

    public void saveTokensOnExit(int playerId, int currentTokens) {
        try (Connection conn = DatabaseManager.getConnection()) {
            TokenDAO tokenDAO = new TokenDAO(conn);

            Token token = tokenDAO.getTokensByPlayerId(playerId);
            if (token == null) {
                tokenDAO.insertNewToken(playerId, currentTokens, null);
            } else {
                token.setTokens(currentTokens);
                tokenDAO.updateToken(token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
