package org.strategy;

import org.models.GameModel;

public class PlayerDrawStrategy implements DrawStrategy {
    @Override
    public void execute(GameModel model) {
        model.drawCardForPlayer();
    }
}
