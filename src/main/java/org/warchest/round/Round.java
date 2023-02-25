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

    public Round(Round round) {
        turnMap = new HashMap<>();
        if (round == null) {
            randomTurns();
        } else {
            if (round.getPlayerTurnByName(round.getFinishingPlayer()).getPlayerActions().stream().anyMatch(playerAction -> playerAction.getActionType() == ActionType.INITIATIVE)) {
                firstPlayerName = round.getFinishingPlayer();
                secondPlayerName = round.getStartingPlayer();
            } else if (round.getPlayerTurnByName(round.getStartingPlayer()).getPlayerActions().stream().anyMatch(playerAction -> playerAction.getActionType() == ActionType.INITIATIVE)) {
                firstPlayerName = round.getStartingPlayer();
                secondPlayerName = round.getFinishingPlayer();
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
