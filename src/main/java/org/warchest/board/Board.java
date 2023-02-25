package org.warchest.board;

import org.warchest.player.Player;
import org.warchest.unit.Unit;

public class Board {

    private static final String COLUMN_HEADERS = "0 1 2 3 4 5 6 7 8";

    private static final String[] ROW_HEADERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};

    private final Square[][] board;

    public Board(String[][] board) {
        this.board = new Square[board.length][board.length];
        initializeBoardFromStringBoard(board);
    }

    public void printBoard() {
        System.out.print("\n---- Current board state ----\n\n");
        System.out.println("   " + COLUMN_HEADERS);
        System.out.println("   -----------------");
        for (int row = 0; row < board.length; row++) {
            System.out.print(ROW_HEADERS[row] + "| ");
            for (int column = 0; column < board[0].length; column++) {
                System.out.print(board[row][column].toString() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public Square getSquareFromPlayerInput(String playerInput) {
        String[] coordinates = playerInput.split(":");
        int row = -1;
        int column = Integer.parseInt(coordinates[1]);

        switch (coordinates[0]) {
            case "A" -> row = 0;
            case "B" -> row = 1;
            case "C" -> row = 2;
            case "D" -> row = 3;
            case "E" -> row = 4;
            case "F" -> row = 5;
            case "G" -> row = 6;
            case "H" -> row = 7;
            case "I" -> row = 8;
        }

        return board[row][column];
    }

    public int unitsPresentForPlayer(Player player) {
        int count = 0;
        for (Square[] squares : board) {
            for (Square square : squares) {
                if (square.getOccupiedBy() != null && ((Unit) square.getOccupiedBy()).getOwner() == player) {
                    count++;
                }
            }
        }
        return count;
    }

    private void initializeBoardFromStringBoard(String[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                this.board[row][column] = Square.buildFromString(board[row][column]);
            }
        }
    }
}
