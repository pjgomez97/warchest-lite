package org.warchest;

import org.warchest.board.Board;
import org.warchest.player.Player;
import org.warchest.player.PlayerName;
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
import java.util.Random;

public class Game {

    private static final int UNIT_TYPES_PER_PLAYER = 4;

    private final List<List<Unit>> units = new ArrayList<>(List.of(
            new LinkedList<>(List.of(new Archer(),      new Archer(),       new Archer(),       new Archer())),
            new LinkedList<>(List.of(new Berserker(),   new Berserker(),    new Berserker(),    new Berserker())),
            new LinkedList<>(List.of(new Cavalry(),     new Cavalry(),      new Cavalry(),      new Cavalry())),
            new LinkedList<>(List.of(new Crossbowman(), new Crossbowman(),  new Crossbowman(),  new Crossbowman(),  new Crossbowman())),
            new LinkedList<>(List.of(new Knight(),      new Knight(),       new Knight(),       new Knight(),       new Knight())),
            new LinkedList<>(List.of(new Lancer(),      new Lancer(),       new Lancer(),       new Lancer())),
            new LinkedList<>(List.of(new Mercenary(),   new Mercenary(),    new Mercenary(),    new Mercenary(),    new Mercenary())),
            new LinkedList<>(List.of(new Swordsman(),   new Swordsman(),    new Swordsman(),    new Swordsman()))
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

    private Player[] players;

    private int playerTurn;

    public Game() {
        initializePlayers();
        initializePlayerTurn();
    }

    public void play() {
        System.out.println("Game start");

        while (true) {
            nextTurn();

            printSeparator();

            board.printBoard();

            players[playerTurn].printCurrentStatus();

            if (hasPlayerWon(players[playerTurn])) {
                break;
            };
        }

        System.out.println("Player " + players[playerTurn].getName() + " has won the game");
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer(int position) {
        return players[position];
    }

    private void initializePlayerTurn() {
        playerTurn = new Random().nextInt(players.length);
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

        this.players = new Player[] {new Player(PlayerName.CROW, firstPlayerBag, firstPlayerRecruitment), new Player(PlayerName.WOLF, secondPlayerBag, secondPlayerRecruitment)};
    }

    private void initializePlayerUnits(List<Unit> playerBag, List<Unit> playerRecruitment, int position) {
        playerBag.add(units.get(position).remove(0));
        playerBag.add(units.get(position).remove(0));

        playerRecruitment.addAll(units.remove(position));
    }

    private void nextTurn() {
        this.playerTurn = (playerTurn + 1) % players.length;
    }

    private boolean hasPlayerWon(Player player) {
        return player.getRemainingTokens() == 0;
    }

    private void printSeparator() {
        System.out.print("\n\n-----------------------------------------------------\n\n");
    }
}
