package org.warchest.board;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.warchest.player.PlayerName;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SquareTest {

    @ParameterizedTest
    @MethodSource("provideParametersForSquareBuildFromString")
    void buildFromString_ShouldReturnExpectedSquare(String s, Position position, Square expected) {
        Square square = Square.buildFromString(s, position);

        assertEquals(expected.getPosition(), square.getPosition());
        assertEquals(expected.getControlledBy(), square.getControlledBy());
        assertEquals(expected.isZone(), square.isZone());
    }

    private static Stream<Arguments> provideParametersForSquareBuildFromString() {
        Square crowSquare = Mockito.mock(Square.class);
        when(crowSquare.getControlledBy()).thenReturn(PlayerName.CROW);
        when(crowSquare.getPosition()).thenReturn(new Position(0, 0));
        when(crowSquare.isZone()).thenReturn(true);

        Square wolfSquare = Mockito.mock(Square.class);
        when(wolfSquare.getControlledBy()).thenReturn(PlayerName.WOLF);
        when(wolfSquare.getPosition()).thenReturn(new Position(0, 1));
        when(wolfSquare.isZone()).thenReturn(true);

        Square zoneSquare = Mockito.mock(Square.class);
        when(zoneSquare.getControlledBy()).thenReturn(null);
        when(zoneSquare.getPosition()).thenReturn(new Position(1, 0));
        when(zoneSquare.isZone()).thenReturn(true);

        Square normalSquare = Mockito.mock(Square.class);
        when(normalSquare.getControlledBy()).thenReturn(null);
        when(normalSquare.getPosition()).thenReturn(new Position(1, 1));
        when(normalSquare.isZone()).thenReturn(false);

        Square anotherNormalSquare = Mockito.mock(Square.class);
        when(anotherNormalSquare.getControlledBy()).thenReturn(null);
        when(anotherNormalSquare.getPosition()).thenReturn(new Position(1, 2));
        when(anotherNormalSquare.isZone()).thenReturn(false);

        return Stream.of(
                Arguments.of("C", new Position(0, 0), crowSquare),
                Arguments.of("W", new Position(0, 1), wolfSquare),
                Arguments.of("@", new Position(1, 0), zoneSquare),
                Arguments.of("-", new Position(1, 1), normalSquare),
                Arguments.of(".", new Position(1, 2), anotherNormalSquare)
        );
    }
}
