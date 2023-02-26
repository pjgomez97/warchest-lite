package org.warchest;

import org.warchest.board.Board;
import org.warchest.board.Square;
import org.warchest.exception.InvalidCommandException;
import org.warchest.player.Player;
import org.warchest.player.PlayerName;
import org.warchest.playerAction.*;
import org.warchest.round.Round;
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
import org.warchest.unit.UnitType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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

    private Player[] players;

    private Round round = null;

    public Game() {
        initializePlayers();
    }

    public void play() {
        System.out.println("Game start");

        while (true) {
            round = new Round(round);

            for (Player player : players) {
                player.initializeHand();
            }

            while (round.getPlayerTurnByName(round.getStartingPlayer()).getMovesLeft() > 0) {
                printSeparator();

                board.printBoard();

                printPlayerCurrentStatus(round.getStartingPlayer());

                PlayerAction playerAction = null;

                showPlayerActions(round.getPlayerTurnByName(round.getStartingPlayer()).getMovesLeft());

                while (playerAction == null) {
                    try {
                        playerAction = getInputFromPlayer(round.getStartingPlayer());
                    } catch (InvalidCommandException e) {
                        System.out.println("Invalid command. Please try again");
                    }
                }

                System.out.println();

                playerAction.perform(round.getPlayerTurnByName(round.getStartingPlayer()), board);

                if (playerAction.getActionType().equals(ActionType.ATTACK) || playerAction.getActionType().equals(ActionType.CONTROL)) {
                    if (hasPlayerWon(round.getStartingPlayer())) {
                        System.out.println("Player " + round.getStartingPlayer() + " has won the game");
                        break;
                    }
                }
            }

            while (round.getPlayerTurnByName(round.getFinishingPlayer()).getMovesLeft() > 0) {
                printSeparator();

                board.printBoard();

                printPlayerCurrentStatus(round.getFinishingPlayer());

                PlayerAction playerAction = null;

                showPlayerActions(round.getPlayerTurnByName(round.getFinishingPlayer()).getMovesLeft());

                while (playerAction == null) {
                    try {
                        playerAction = getInputFromPlayer(round.getFinishingPlayer());
                    } catch (InvalidCommandException e) {
                        System.out.println("Invalid command. Please try again");
                    }
                }

                playerAction.perform(round.getPlayerTurnByName(round.getFinishingPlayer()), board);

                if (playerAction.getActionType().equals(ActionType.ATTACK) || playerAction.getActionType().equals(ActionType.CONTROL)) {
                    if (hasPlayerWon(round.getFinishingPlayer())) {
                        System.out.println("Player " + round.getFinishingPlayer() + " has won the game");
                        break;
                    }
                }
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

        Player firstPlayer = new Player(PlayerName.CROW, firstPlayerBag, firstPlayerRecruitment);
        Player secondPlayer = new Player(PlayerName.WOLF, secondPlayerBag, secondPlayerRecruitment);

        players = new Player[]{firstPlayer, secondPlayer};
    }

    private void initializePlayerUnits(List<Unit> playerBag, List<Unit> playerRecruitment, int position) {
        playerBag.add(units.get(position).remove(0));
        playerBag.add(units.get(position).remove(0));

        playerRecruitment.addAll(units.remove(position));
    }

    private void printSeparator() {
        System.out.println("\n-----------------------------------------------------\n");
    }

    public void printPlayerCurrentStatus(PlayerName playerName) {
        System.out.println();
        System.out.printf("========== %s ==========%n", playerName);

        Player player = getPlayerByName(playerName);

        player.printHand();
        player.printRecruitment();
        player.printDiscard();
        System.out.println();
        player.printControlTokens();
    }

    private boolean hasPlayerWon(PlayerName playerName) {
        Player player = getPlayerByName(playerName);

        if (player.getRemainingTokens() == 0) {
            return true;
        }

        Player adversary = getAdversary(player);

        return adversary.hasNoUnitsToRecruit() && adversary.hasEmptyBag() && adversary.hasEmptyHand() && board.unitsPresentForPlayer(adversary) == 0;
    }

    private void showPlayerActions(int movesLeft) {
        System.out.printf("\n\nInput the desired action. You have %d moves left.\nEnter HELP for examples on the possible actions. \n\n", movesLeft);
    }

    private Player getPlayerByName(PlayerName playerName) {
        return players[0].getPlayerName().equals(playerName) ? players[0] : players[1];
    }

    private PlayerAction getInputFromPlayer(PlayerName playerName) throws InvalidCommandException {
        System.out.print("ACTION: ");
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();
        if (action.equals("HELP")) {
            showActionExamples();
            return null;
        } else {
            return parseAction(action, playerName);
        }
    }

    private void showActionExamples() {
        System.out.println("\n----- AVAILABLE COMMANDS -----");
        System.out.println("PLACE <UNIT> <SQUARE>");
        System.out.println("CONTROL <UNIT> <SQUARE>");
        System.out.println("MOVE <ORIGIN> <DESTINATION>");
        System.out.println("RECRUIT [ROYAL] <UNIT>");
        System.out.println("ATTACK <ORIGIN> <DESTINATION>");
        System.out.println("INITIATIVE <UNIT>");
        System.out.println("EXIT");
        System.out.println("\nThe board positions must be input like ROW:COLUMN. E.g: A:1, C:3\n");
    }

    private PlayerAction parseAction(String action, PlayerName playerName) throws InvalidCommandException {
        Player player = getPlayerByName(playerName);
        String[] tokens = action.split(" ");
        try {
            switch (tokens[0]) {
                case "PLACE" -> {
                    return new PlaceAction(player, ActionType.PLACE, player.getUnitFromHandByType(UnitType.valueOf(tokens[1])), board.getSquareFromPlayerInput(tokens[2]));
                }
                case "CONTROL" -> {
                    return new ControlAction(player, ActionType.CONTROL, player.getUnitFromHandByType(UnitType.valueOf(tokens[1])), board.getSquareFromPlayerInput(tokens[2]), getAdversary(player));
                }
                case "MOVE" -> {
                    Square originSquare = board.getSquareFromPlayerInput(tokens[1]);
                    Unit handUnit = originSquare.getOccupiedBy() == null ? null :  player.getUnitFromHandByType(((Unit) originSquare.getOccupiedBy()).getType());
                    return new MoveAction(player, ActionType.MOVE, handUnit, originSquare, board.getSquareFromPlayerInput(tokens[2]));
                }
                case "RECRUIT" -> {
                    if (tokens[1].equals("ROYAL")) {
                        return new RecruitAction(player, ActionType.RECRUIT, player.getUnitFromHandByType(UnitType.ROYAL), UnitType.valueOf(tokens[2]));
                    }
                    return new RecruitAction(player, ActionType.RECRUIT, player.getUnitFromHandByType(UnitType.valueOf(tokens[1])));
                }
                case "ATTACK" -> {
                    Square originSquare = board.getSquareFromPlayerInput(tokens[1]);
                    Unit handUnit = originSquare.getOccupiedBy() == null ? null :  player.getUnitFromHandByType(((Unit) originSquare.getOccupiedBy()).getType());
                    return new AttackAction(player, ActionType.ATTACK, handUnit, originSquare, board.getSquareFromPlayerInput(tokens[2]));
                }
                case "INITIATIVE" -> {
                    return new InitiativeAction(player, ActionType.INITIATIVE, player.getUnitFromHandByType(UnitType.valueOf(tokens[1])));
                }
                case "EXIT" -> {
                    System.out.println("Exiting the game...");
                    System.exit(0);
                    return null;
                }
                default -> {
                    System.out.println("Invalid command syntax. Please try again");
                    return null;
                }
            }
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            throw new InvalidCommandException("The introduced command is not valid");
        }
    }

    private Player getAdversary(Player player) {
        return players[0] == player ? players[1] : players[0];
    }
}
