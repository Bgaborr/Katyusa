package org.models;

import java.time.LocalDate;

public class Token {
    private int playerId;
    private int tokens;
    private LocalDate lastDailyClaim;

    public Token(int playerId, int tokens, LocalDate lastDailyClaim) {
        this.playerId = playerId;
        this.tokens = tokens;
        this.lastDailyClaim = lastDailyClaim;
    }

    public int getPlayerId(int i) {
        return playerId;
    }

    public int getTokens() {
        return tokens;
    }

    public LocalDate getLastDailyClaim() {
        return lastDailyClaim;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public void setLastDailyClaim(LocalDate lastDailyClaim) {
        this.lastDailyClaim = lastDailyClaim;
    }
}
