package org.warchest.board;

public class Board {

    private static final String[] rowNames = {"A", "B", "C", "D", "E", "F", "G", "H", "I" };
    private final Square[][] board;

    public Board(String[][] board) {
        this.board = new Square[board.length][board.length];
        initializeBoardFromStringBoard(board);
    }

    public void printBoard() {
        System.out.println("   0 1 2 3 4 5 6 7 8");
        System.out.println("   -----------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print(rowNames[i] + "| ");
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(this.board[i][j].toString() + " ");
            }
            System.out.println();
        }
    }

    private void initializeBoardFromStringBoard(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                this.board[i][j] = Square.buildFromString(board[i][j]);
            }
        }
    }
}
