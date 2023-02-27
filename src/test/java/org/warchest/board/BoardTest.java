package org.warchest.board;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.warchest.player.PlayerName;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BoardTest {

    private static final String[] ROW_HEADERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};

    @ParameterizedTest
    @MethodSource("provideParametersForBoardConstructor")
    void constructor_ShouldCreateExpectedBoard(String[][] boardString, Board expected) {
        Board board = new Board(boardString);

        for (int row = 0; row < boardString.length; row++) {
            for (int column = 0; column < boardString[0].length; column++) {
                String position = ROW_HEADERS[row] + ":" + column;
                assertEquals(expected.getSquareFromPlayerInput(position).getPosition(), board.getSquareFromPlayerInput(position).getPosition());
                assertEquals(expected.getSquareFromPlayerInput(position).getControlledBy(), board.getSquareFromPlayerInput(position).getControlledBy());
                assertEquals(expected.getSquareFromPlayerInput(position).isZone(), board.getSquareFromPlayerInput(position).isZone());
            }
        }
    }

    private static Stream<Arguments> provideParametersForBoardConstructor() {
        String[][] smallBoardString = new String[][]{
                {"C", "-", "-", "-", "C"},
                {"-", "-", "-", "-", "-"},
                {"-", "@", "-", "-", "@"},
                {"-", "-", "-", "-", "-"},
                {"@", "-", "-", "@", "-"},
                {"-", "-", "-", "-", "-"},
                {"W", "-", "-", "-", "W"}
        };

        Board smallBoard = Mockito.mock(Board.class);

        for (int row = 0; row < smallBoardString.length; row++) {
            for (int column = 0; column < smallBoardString[0].length; column++) {
                String position = ROW_HEADERS[row] + ":" + column;
                when(smallBoard.getSquareFromPlayerInput(position)).thenReturn(Square.buildFromString(smallBoardString[row][column], new Position(row, column)));
            }
        }

        String[][] mediumBoardString = new String[][]{
                {"-", "C", "-", "-", "-", "C", "-"},
                {"-", "-", "-", "-", "-", "-", "-"},
                {"@", "-", "@", "-", "-", "@", "-"},
                {"-", "-", "-", "-", "-", "-", "-"},
                {"-", "@", "-", "-", "@", "-", "@"},
                {"-", "-", "-", "-", "-", "-", "-"},
                {"-", "W", "-", "-", "-", "W", "-"}
        };

        Board mediumBoard = Mockito.mock(Board.class);

        for (int row = 0; row < mediumBoardString.length; row++) {
            for (int column = 0; column < mediumBoardString[0].length; column++) {
                String position = ROW_HEADERS[row] + ":" + column;
                when(mediumBoard.getSquareFromPlayerInput(position)).thenReturn(Square.buildFromString(mediumBoardString[row][column], new Position(row, column)));
            }
        }

        String[][] bigBoardString = new String[][]{
                {"-", "-", "C", "-", "-", "-", "C", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "@", "-", "@", "-", "-", "@", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "@", "-", "-", "@", "-", "@", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "W", "-", "-", "-", "W", "-", "-"}
        };

        Board bigBoard = Mockito.mock(Board.class);

        for (int row = 0; row < bigBoardString.length; row++) {
            for (int column = 0; column < bigBoardString[0].length; column++) {
                String position = ROW_HEADERS[row] + ":" + column;
                when(bigBoard.getSquareFromPlayerInput(position)).thenReturn(Square.buildFromString(bigBoardString[row][column], new Position(row, column)));
            }
        }

        return Stream.of(
                Arguments.of(smallBoardString, smallBoard),
                Arguments.of(mediumBoardString, mediumBoard),
                Arguments.of(bigBoardString, bigBoard)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersForGetSquareFromPlayerInput")
    void getSquareFromPlayerInput_ShouldReturnExpectedSquare(Board board, String playerInput, Square expected) {
        assertEquals(expected.getPosition(), board.getSquareFromPlayerInput(playerInput).getPosition());
        assertEquals(expected.getControlledBy(), board.getSquareFromPlayerInput(playerInput).getControlledBy());
        assertEquals(expected.isZone(), board.getSquareFromPlayerInput(playerInput).isZone());
    }

    private static Stream<Arguments> provideParametersForGetSquareFromPlayerInput() {
        String firstPlayerInput = "A:0";
        String secondPlayerInput = "D:2";
        String thirdPlayerInput = "G:4";

        Square firstSquare = Mockito.mock(Square.class);

        when(firstSquare.getPosition()).thenReturn(new Position(0, 0));
        when(firstSquare.getControlledBy()).thenReturn(PlayerName.CROW);
        when(firstSquare.isZone()).thenReturn(true);

        Square secondSquare = Mockito.mock(Square.class);

        when(secondSquare.getPosition()).thenReturn(new Position(3, 2));
        when(secondSquare.getControlledBy()).thenReturn(null);
        when(secondSquare.isZone()).thenReturn(false);

        Square thirdSquare = Mockito.mock(Square.class);

        when(thirdSquare.getPosition()).thenReturn(new Position(6, 4));
        when(thirdSquare.getControlledBy()).thenReturn(PlayerName.WOLF);
        when(thirdSquare.isZone()).thenReturn(true);

        Board board = new Board(new String[][]{
                {"C", "-", "-", "-", "C"},
                {"-", "-", "-", "-", "-"},
                {"-", "@", "-", "-", "@"},
                {"-", "-", "-", "-", "-"},
                {"@", "-", "-", "@", "-"},
                {"-", "-", "-", "-", "-"},
                {"W", "-", "-", "-", "W"}
        });

        return Stream.of(
                Arguments.of(board, firstPlayerInput, firstSquare),
                Arguments.of(board, secondPlayerInput, secondSquare),
                Arguments.of(board, thirdPlayerInput, thirdSquare)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersForGetSquaresAdjacentTo")
    void getSquaresAdjacentTo_ShouldReturnExpectedSquares(Board board, Square square, List<Square> expected) {
        List<Square> adjacentSquares = board.getSquaresAdjacentTo(square);

        assertTrue(adjacentSquares.containsAll(expected));
        assertTrue(expected.containsAll(adjacentSquares));
    }

    private static Stream<Arguments> provideParametersForGetSquaresAdjacentTo() {
        Board board = new Board(new String[][]{
                {"C", "-", "-", "-", "C"},
                {"-", "-", "-", "-", "-"},
                {"-", "@", "-", "-", "@"},
                {"-", "-", "-", "-", "-"},
                {"@", "-", "-", "@", "-"},
                {"-", "-", "-", "-", "-"},
                {"W", "-", "-", "-", "W"}
        });

        Square firstSquare = board.getSquareFromPlayerInput("A:0");

        List<Square> squaresAdjacentToFirst = List.of(
                board.getSquareFromPlayerInput("A:1"),
                board.getSquareFromPlayerInput("B:0"),
                board.getSquareFromPlayerInput("B:1")
        );

        Square secondSquare = board.getSquareFromPlayerInput("D:2");

        List<Square> squaresAdjacentToSecond = List.of(
                board.getSquareFromPlayerInput("C:1"),
                board.getSquareFromPlayerInput("C:2"),
                board.getSquareFromPlayerInput("C:3"),
                board.getSquareFromPlayerInput("D:1"),
                board.getSquareFromPlayerInput("D:3"),
                board.getSquareFromPlayerInput("E:1"),
                board.getSquareFromPlayerInput("E:2"),
                board.getSquareFromPlayerInput("E:3")
        );

        Square thirdSquare = board.getSquareFromPlayerInput("G:4");

        List<Square> squaresAdjacentToThird = List.of(
                board.getSquareFromPlayerInput("F:3"),
                board.getSquareFromPlayerInput("F:4"),
                board.getSquareFromPlayerInput("G:3")
        );

        return Stream.of(
                Arguments.of(board, firstSquare, squaresAdjacentToFirst),
                Arguments.of(board, secondSquare, squaresAdjacentToSecond),
                Arguments.of(board, thirdSquare, squaresAdjacentToThird)
        );
    }
}
