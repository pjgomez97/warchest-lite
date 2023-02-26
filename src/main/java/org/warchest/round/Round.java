package org.warchest.round;

import org.warchest.player.PlayerName;
import org.warchest.playerAction.ActionType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Round {

    private final Map<PlayerName, Turn> turnMap;

    private PlayerName firstPlayerName;

    private PlayerName secondPlayerName;

    public Round(Round previousRound) {
        turnMap = new HashMap<>();

        if (previousRound == null) {
            randomTurns();
        } else {
            if (previousRound.getPlayerTurnByName(previousRound.getFinishingPlayer()).getPlayerActions().stream().anyMatch(playerAction -> playerAction.getActionType() == ActionType.INITIATIVE)) {
                firstPlayerName = previousRound.getFinishingPlayer();
                secondPlayerName = previousRound.getStartingPlayer();
            } else if (previousRound.getPlayerTurnByName(previousRound.getStartingPlayer()).getPlayerActions().stream().anyMatch(playerAction -> playerAction.getActionType() == ActionType.INITIATIVE)) {
                firstPlayerName = previousRound.getStartingPlayer();
                secondPlayerName = previousRound.getFinishingPlayer();
            } else {
                randomTurns();
            }
        }

        turnMap.put(firstPlayerName, new Turn());
        turnMap.put(secondPlayerName, new Turn());
    }

    public Turn getPlayerTurnByName(PlayerName playerName) {
        return turnMap.get(playerName);
    }

    public PlayerName getStartingPlayer() {
        return firstPlayerName;
    }

    public PlayerName getFinishingPlayer() {
        return secondPlayerName;
    }

    private void randomTurns() {
        Random random = new Random();

        int initialPlayer = random.nextInt(2);

        firstPlayerName = PlayerName.values()[initialPlayer];
        secondPlayerName = PlayerName.values()[(initialPlayer + 1) % 2];
    }
}
