package org.warchest.round;

import org.warchest.player.PlayerName;

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
            if (round.getPlayerTurnByName(round.getFinishingPlayer()).getPlayerActions().stream().anyMatch(playerAction -> playerAction.action() == Action.INITIATIVE)) {
                this.firstPlayerName = round.getFinishingPlayer();
                this.secondPlayerName = round.getStartingPlayer();
            } else if (round.getPlayerTurnByName(round.getStartingPlayer()).getPlayerActions().stream().anyMatch(playerAction -> playerAction.action() == Action.INITIATIVE)) {
                this.firstPlayerName = round.getStartingPlayer();
                this.secondPlayerName = round.getFinishingPlayer();
            } else {
                randomTurns();
            }
        }
        this.turnMap.put(this.firstPlayerName, new Turn());
        this.turnMap.put(this.secondPlayerName, new Turn());
    }

    public Turn getPlayerTurnByName(PlayerName playerName) {
        return this.turnMap.get(playerName);
    }

    public PlayerName getStartingPlayer() {
        return this.firstPlayerName;
    }

    public PlayerName getFinishingPlayer() {
        return this.secondPlayerName;
    }

    private void randomTurns() {
        Random random = new Random();

        int initialPlayer = random.nextInt(2);

        this.firstPlayerName = PlayerName.values()[initialPlayer];
        this.secondPlayerName = PlayerName.values()[(initialPlayer + 1) % 2];
    }
}
