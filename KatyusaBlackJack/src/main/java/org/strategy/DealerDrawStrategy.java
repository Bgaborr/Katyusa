package org.strategy;

import org.models.GameModel;

public class DealerDrawStrategy implements DrawStrategy {
    @Override
    public void execute(GameModel model) {
        model.drawCardForDealer();
    }
}
