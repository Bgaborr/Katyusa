package org.rules;


public class GameResult {
    private final String message;
    private final boolean playerWins;

    public GameResult(String message, boolean playerWins) {
        this.message = message;
        this.playerWins = playerWins;
    }

    public String getMessage() {
        return message;
    }

    public boolean isPlayerWins() {
        return playerWins;
    }
}