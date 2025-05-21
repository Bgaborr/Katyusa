package org.database;

import org.models.Token;

import java.sql.*;
import java.time.LocalDate;

public class TokenDAO {
    private final Connection conn;

    public TokenDAO(Connection conn) {
        this.conn = conn;
    }

    public Token getTokensByPlayerId(int playerId) throws SQLException {
        String sql = "SELECT * FROM user_tokens WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, playerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int tokens = rs.getInt("tokens");
                Date lastClaim = rs.getDate("last_daily_claim");
                LocalDate lastDailyClaim = lastClaim != null ? lastClaim.toLocalDate() : null;
                return new Token(playerId, tokens, lastDailyClaim);
            }
        }
        return null;
    }

    public void updateToken(Token token) throws SQLException {
        String sql = "UPDATE user_tokens SET tokens = ?, last_daily_claim = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, token.getTokens());
            if (token.getLastDailyClaim() != null) {
                stmt.setDate(2, Date.valueOf(token.getLastDailyClaim()));
            } else {
                stmt.setNull(2, Types.DATE);
            }
            stmt.setInt(3, token.getPlayerId(1));
            stmt.executeUpdate();
        }
    }

    public void insertNewToken(int playerId, int tokens, LocalDate lastDailyClaim) throws SQLException {
        String sql = "INSERT INTO user_tokens (id, tokens, last_daily_claim) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, playerId);
            stmt.setInt(2, tokens);
            if (lastDailyClaim != null) {
                stmt.setDate(3, Date.valueOf(lastDailyClaim));
            } else {
                stmt.setNull(3, Types.DATE);
            }
            stmt.executeUpdate();
        }
    }
}
