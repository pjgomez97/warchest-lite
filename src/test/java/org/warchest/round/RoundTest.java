package org.warchest.round;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.warchest.player.Player;
import org.warchest.player.PlayerName;
import org.warchest.playerAction.ActionType;
import org.warchest.playerAction.InitiativeAction;
import org.warchest.unit.Unit;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoundTest {

    @ParameterizedTest
    @MethodSource("provideParametersForRoundConstructorWithoutInitiative")
    void constructor_ShouldCreateExpectedRoundWithoutInitiative(Round previousRound, Round expected) {
        int timesRoundsWereEqual = 0;

        for (int i = 0; i < 1000; i++) {
            if (expected.getStartingPlayer() == new Round(previousRound).getStartingPlayer()) {
                timesRoundsWereEqual++;
            }
        }

        assertTrue(timesRoundsWereEqual >= 300);
    }

    private static Stream<Arguments> provideParametersForRoundConstructorWithoutInitiative() {
        Turn turnWithoutActions = Mockito.mock(Turn.class);

        when(turnWithoutActions.getPlayerActions()).thenReturn(Collections.emptyList());

        Round previousRoundWithoutInititative = Mockito.mock(Round.class);

        when(previousRoundWithoutInititative.getPlayerTurnByName(any())).thenReturn(turnWithoutActions);

        Round expectedRound = Mockito.mock(Round.class);

        when(expectedRound.getStartingPlayer()).thenReturn(PlayerName.CROW);

        return Stream.of(
                Arguments.of(null, expectedRound),
                Arguments.of(previousRoundWithoutInititative, expectedRound)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersForRoundConstructorWithInitiative")
    void constructor_ShouldCreateExpectedRoundWithInitiative(Round previousRound, Round expected) {
        Round round = new Round(previousRound);

        assertEquals(expected.getStartingPlayer(), round.getStartingPlayer());
    }

    private static Stream<Arguments> provideParametersForRoundConstructorWithInitiative() {
        Player playerMock = Mockito.mock(Player.class);
        Unit unitMock = Mockito.mock(Unit.class);

        Turn turnWithInitiative = Mockito.mock(Turn.class);

        when(turnWithInitiative.getPlayerActions()).thenReturn(List.of(new InitiativeAction(playerMock, ActionType.INITIATIVE, unitMock)));

        Turn turnWithoutActions = Mockito.mock(Turn.class);

        when(turnWithoutActions.getPlayerActions()).thenReturn(Collections.emptyList());

        Round previousRoundWithInititativeFromStartingPlayer = Mockito.mock(Round.class);

        when(previousRoundWithInititativeFromStartingPlayer.getStartingPlayer()).thenReturn(PlayerName.CROW);
        when(previousRoundWithInititativeFromStartingPlayer.getFinishingPlayer()).thenReturn(PlayerName.WOLF);

        when(previousRoundWithInititativeFromStartingPlayer.getPlayerTurnByName(previousRoundWithInititativeFromStartingPlayer.getStartingPlayer())).thenReturn(turnWithInitiative);
        when(previousRoundWithInititativeFromStartingPlayer.getPlayerTurnByName(previousRoundWithInititativeFromStartingPlayer.getFinishingPlayer())).thenReturn(turnWithoutActions);

        Round previousRoundWithInititativeFromFinishingPlayer = Mockito.mock(Round.class);

        when(previousRoundWithInititativeFromFinishingPlayer.getStartingPlayer()).thenReturn(PlayerName.CROW);
        when(previousRoundWithInititativeFromFinishingPlayer.getFinishingPlayer()).thenReturn(PlayerName.WOLF);

        when(previousRoundWithInititativeFromFinishingPlayer.getPlayerTurnByName(previousRoundWithInititativeFromFinishingPlayer.getFinishingPlayer())).thenReturn(turnWithInitiative);
        when(previousRoundWithInititativeFromFinishingPlayer.getPlayerTurnByName(previousRoundWithInititativeFromFinishingPlayer.getStartingPlayer())).thenReturn(turnWithoutActions);

        Round previousRoundWithInititativeFromBothPlayers = Mockito.mock(Round.class);

        when(previousRoundWithInititativeFromBothPlayers.getStartingPlayer()).thenReturn(PlayerName.CROW);
        when(previousRoundWithInititativeFromBothPlayers.getFinishingPlayer()).thenReturn(PlayerName.WOLF);

        when(previousRoundWithInititativeFromBothPlayers.getPlayerTurnByName(any())).thenReturn(turnWithInitiative);

        Round expectedRoundStartingCrow = Mockito.mock(Round.class);

        when(expectedRoundStartingCrow.getStartingPlayer()).thenReturn(PlayerName.CROW);

        Round expectedRoundStartingWolf = Mockito.mock(Round.class);

        when(expectedRoundStartingWolf.getStartingPlayer()).thenReturn(PlayerName.WOLF);

        return Stream.of(
                Arguments.of(previousRoundWithInititativeFromStartingPlayer, expectedRoundStartingCrow),
                Arguments.of(previousRoundWithInititativeFromFinishingPlayer, expectedRoundStartingWolf),
                Arguments.of(previousRoundWithInititativeFromBothPlayers, expectedRoundStartingWolf)
        );
    }
}
