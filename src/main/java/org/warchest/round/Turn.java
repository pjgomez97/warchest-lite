package org.warchest.round;

import java.util.ArrayList;
import java.util.List;

public class Turn {

    private static final int MAX_MOVES_PER_TURN = 3;

    private final List<PlayerAction> playerActions;

    private int movesLeft = MAX_MOVES_PER_TURN;

    public Turn() {
        this.playerActions = new ArrayList<>();
    }

    public List<PlayerAction> getPlayerActions() {
        return this.playerActions;
    }

    public void addPlayerAction(PlayerAction playerAction) {
        this.playerActions.add(playerAction);
    }

    public int getMovesLeft() {
        return this.movesLeft;
    }

    public void decreaseMovesLeft() {
        this.movesLeft--;
    }
}
