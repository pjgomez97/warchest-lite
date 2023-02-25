package org.warchest;

import org.warchest.board.Board;
import org.warchest.player.Player;
import org.warchest.player.PlayerName;
import org.warchest.round.RoundInfo;
import org.warchest.unit.Archer;
import org.warchest.unit.Berserker;
import org.warchest.unit.Cavalry;
import org.warchest.unit.Crossbowman;
import org.warchest.unit.Knight;
import org.warchest.unit.Lancer;
import org.warchest.unit.Mercenary;
import org.warchest.unit.Royal;
import org.warchest.unit.Swordsman;
import org.warchest.unit.Unit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Game {

    private static final int UNIT_TYPES_PER_PLAYER = 4;

    private final List<List<Unit>> units = new ArrayList<>(List.of(
            new LinkedList<>(List.of(new Archer(), new Archer(), new Archer(), new Archer())),
            new LinkedList<>(List.of(new Berserker(), new Berserker(), new Berserker(), new Berserker())),
            new LinkedList<>(List.of(new Cavalry(), new Cavalry(), new Cavalry(), new Cavalry())),
            new LinkedList<>(List.of(new Crossbowman(), new Crossbowman(), new Crossbowman(), new Crossbowman(), new Crossbowman())),
            new LinkedList<>(List.of(new Knight(), new Knight(), new Knight(), new Knight(), new Knight())),
            new LinkedList<>(List.of(new Lancer(), new Lancer(), new Lancer(), new Lancer())),
            new LinkedList<>(List.of(new Mercenary(), new Mercenary(), new Mercenary(), new Mercenary(), new Mercenary())),
            new LinkedList<>(List.of(new Swordsman(), new Swordsman(), new Swordsman(), new Swordsman()))
    ));

    private final Board board = new Board(new String[][]{
            {"-", "-", "C", "-", "-", "-", "C", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "@", "-", "@", "-", "-", "@", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "@", "-", "-", "@", "-", "@", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "W", "-", "-", "-", "W", "-", "-"}
    });

    private Map<PlayerName, Player> players;

    private RoundInfo roundInfo = null;

    public Game() {
        initializePlayers();
    }

    public void play() {
        System.out.println("Game start");

        while (true) {
            roundInfo = new RoundInfo(roundInfo);

            printSeparator();

            board.printBoard();

            printPlayerCurrentStatus(roundInfo.getStartingPlayer());

            if (hasPlayerWon(roundInfo.getStartingPlayer())) {
                System.out.println("Player " + roundInfo.getStartingPlayer() + " has won the game");
                break;
            }

            printSeparator();

            board.printBoard();

            printPlayerCurrentStatus(roundInfo.getFinishingPlayer());

            if (hasPlayerWon(roundInfo.getFinishingPlayer())) {
                System.out.println("Player " + roundInfo.getFinishingPlayer() + " has won the game");
                break;
            }
        }
    }

    private void initializePlayers() {
        Random random = new Random();

        List<Unit> firstPlayerBag = new ArrayList<>();
        List<Unit> firstPlayerRecruitment = new ArrayList<>();

        List<Unit> secondPlayerBag = new ArrayList<>();
        List<Unit> secondPlayerRecruitment = new ArrayList<>();

        for (int i = 0; i < UNIT_TYPES_PER_PLAYER; i++) {
            int position = random.nextInt(units.size());

            initializePlayerUnits(firstPlayerBag, firstPlayerRecruitment, position);

            position = random.nextInt(units.size());

            initializePlayerUnits(secondPlayerBag, secondPlayerRecruitment, position);
        }

        firstPlayerBag.add(new Royal());
        secondPlayerBag.add(new Royal());

        Player firstPlayer = new Player(firstPlayerBag, firstPlayerRecruitment);
        Player secondPlayer = new Player(secondPlayerBag, secondPlayerRecruitment);

        this.players = Map.of(PlayerName.CROW, firstPlayer, PlayerName.WOLF, secondPlayer);
    }

    private void initializePlayerUnits(List<Unit> playerBag, List<Unit> playerRecruitment, int position) {
        playerBag.add(units.get(position).remove(0));
        playerBag.add(units.get(position).remove(0));

        playerRecruitment.addAll(units.remove(position));
    }

    private void printSeparator() {
        System.out.print("\n\n-----------------------------------------------------\n\n");
    }

    public void printPlayerCurrentStatus(PlayerName playerName) {
        System.out.println();
        System.out.printf("========== %s ==========%n", playerName);

        Player player = this.players.get(playerName);

        player.printHand();
        player.printRecruitment();
        player.printDiscard();
    }

    private boolean hasPlayerWon(PlayerName playerName) {
        return this.players.get(playerName).getRemainingTokens() == 0;
    }
}
