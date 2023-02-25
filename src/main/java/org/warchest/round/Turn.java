package org.warchest.round;

import org.warchest.playerAction.PlayerAction;

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
        return playerActions;
    }

    public void addPlayerAction(PlayerAction playerAction) {
        playerActions.add(playerAction);
    }

    public int getMovesLeft() {
        return movesLeft;
    }

    public void decreaseMovesLeft() {
        movesLeft--;
    }
}
