package org.controllers;

import javafx.application.Platform;
import org.services.TokenSaverService;

public class ExitHandler {

    private final int playerId;
    private final int currentTokens;

    public ExitHandler(int playerId, int currentTokens) {
        this.playerId = playerId;
        this.currentTokens = currentTokens;
    }

    public void handleExit() {
        TokenSaverService saver = new TokenSaverService();
        saver.saveTokensOnExit(playerId, currentTokens);
        Platform.exit();
    }
}
