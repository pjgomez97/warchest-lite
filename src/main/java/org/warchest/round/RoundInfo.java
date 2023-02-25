package org.warchest.round;

import org.warchest.player.PlayerName;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoundInfo {

    private static final int MAX_MOVES_PER_ROUND = 3;

    private PlayerName startingPlayer;

    private PlayerName finishingPlayer;

    private final List<PlayerAction> startingPlayerActions;

    private final List<PlayerAction> finishingPlayerActions;

    private int startingPlayerMovesLeft = MAX_MOVES_PER_ROUND;

    private int finishingPlayerMovesLeft = MAX_MOVES_PER_ROUND;

    public RoundInfo(RoundInfo roundInfo) {
        this.startingPlayerActions = new ArrayList<>();
        this.finishingPlayerActions = new ArrayList<>();

        if (roundInfo == null) {
            randomTurn();
        } else {
            if (roundInfo.finishingPlayerActions.stream().anyMatch(playerAction -> playerAction.action() == Action.INITIATIVE)) {
                this.startingPlayer = roundInfo.getFinishingPlayer();
                this.finishingPlayer = roundInfo.getStartingPlayer();
            } else if (roundInfo.startingPlayerActions.stream().anyMatch(playerAction -> playerAction.action() == Action.INITIATIVE)) {
                this.startingPlayer = roundInfo.getStartingPlayer();
                this.finishingPlayer = roundInfo.getFinishingPlayer();
            } else {
                randomTurn();
            }
        }
    }

    public PlayerName getStartingPlayer() {
        return startingPlayer;
    }

    public PlayerName getFinishingPlayer() {
        return finishingPlayer;
    }

    public List<PlayerAction> getStartingPlayerActions() {
        return startingPlayerActions;
    }

    public void addActionToStartingPlayerActions(PlayerAction action) {
        startingPlayerActions.add(action);
    }

    public List<PlayerAction> getFinishingPlayerActions() {
        return finishingPlayerActions;
    }

    public void addActionToFinishingPlayerActions(PlayerAction action) {
        finishingPlayerActions.add(action);
    }

    public int getStartingPlayerMovesLeft() {
        return startingPlayerMovesLeft;
    }

    public void decreaseStartingPlayerMovesLeft() {
        startingPlayerMovesLeft--;
    }

    public int getFinishingPlayerMovesLeft() {
        return finishingPlayerMovesLeft;
    }

    public void decreaseFinishingPlayerMovesLeft() {
        finishingPlayerMovesLeft--;
    }

    private void randomTurn() {
        Random random = new Random();

        int initialPlayer = random.nextInt(2);

        this.startingPlayer = PlayerName.values()[initialPlayer];
        this.finishingPlayer = PlayerName.values()[(initialPlayer + 1) % 2];
    }
}
