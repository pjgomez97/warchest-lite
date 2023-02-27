package org.warchest.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

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
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PlayerTest {

    private Player player;

    @BeforeEach
    void setup() {
        List<Unit> playerBag = new ArrayList<>(List.of(
                new Archer(),
                new Archer(),
                new Knight(),
                new Knight(),
                new Mercenary(),
                new Mercenary(),
                new Swordsman(),
                new Swordsman(),
                new Royal()
        ));

        List<Unit> playerRecruitment = new ArrayList<>(List.of(
                new Archer(),
                new Archer(),
                new Knight(),
                new Knight(),
                new Knight(),
                new Mercenary(),
                new Mercenary(),
                new Mercenary(),
                new Swordsman(),
                new Swordsman()
        ));

        player = new Player(PlayerName.CROW, playerBag, playerRecruitment);
    }
    @ParameterizedTest
    @MethodSource("provideParametersForPlayerConstructor")
    void constructor_ShouldCreateExpectedPlayer(PlayerName playerName, List<Unit> bag, List<Unit> recruitment) {
        Player player = new Player(playerName, bag, recruitment);

        assertEquals(playerName, player.getPlayerName());
        assertFalse(player.hasEmptyBag());
        assertFalse(player.hasNoUnitsToRecruit());
        assertTrue(player.hasEmptyHand());
        assertEquals(player, player.getUnitFromRecruitmentByType(recruitment.get(0).getType()).getOwner());
        assertEquals(Player.MAX_TOKENS_PER_PLAYER, player.getRemainingTokens());
    }

    private static Stream<Arguments> provideParametersForPlayerConstructor() {
        List<Unit> crowPlayerBag = new ArrayList<>(List.of(
                new Archer(),
                new Archer(),
                new Knight(),
                new Knight(),
                new Mercenary(),
                new Mercenary(),
                new Swordsman(),
                new Swordsman(),
                new Royal()
        ));

        List<Unit> crowPlayerRecruitment = new ArrayList<>(List.of(
                new Archer(),
                new Archer(),
                new Knight(),
                new Knight(),
                new Knight(),
                new Mercenary(),
                new Mercenary(),
                new Mercenary(),
                new Swordsman(),
                new Swordsman()
        ));

        List<Unit> wolfPlayerBag = new ArrayList<>(List.of(
                new Berserker(),
                new Berserker(),
                new Cavalry(),
                new Cavalry(),
                new Crossbowman(),
                new Crossbowman(),
                new Lancer(),
                new Lancer(),
                new Royal()
        ));

        List<Unit> wolfPlayerRecruitment = new ArrayList<>(List.of(
                new Berserker(),
                new Berserker(),
                new Cavalry(),
                new Cavalry(),
                new Crossbowman(),
                new Crossbowman(),
                new Crossbowman(),
                new Lancer(),
                new Lancer()
        ));

        return Stream.of(
                Arguments.of(PlayerName.CROW, crowPlayerBag, crowPlayerRecruitment),
                Arguments.of(PlayerName.WOLF, wolfPlayerBag, wolfPlayerRecruitment)
        );
    }

    @Test
    void addToken_ShouldAddATokenToThePlayer() {
        assertEquals(Player.MAX_TOKENS_PER_PLAYER, player.getRemainingTokens());

        player.addToken();

        assertEquals(Player.MAX_TOKENS_PER_PLAYER + 1, player.getRemainingTokens());
    }

    @Test
    void removeToken_ShouldSubstractATokenToThePlayer() {
        assertEquals(Player.MAX_TOKENS_PER_PLAYER, player.getRemainingTokens());

        player.removeToken();

        assertEquals(Player.MAX_TOKENS_PER_PLAYER - 1, player.getRemainingTokens());
    }

    @Test
    void initializeHand_ShouldInitializeThePlayerHand() {
        assertTrue(player.hasEmptyHand());

        player.initializeHand();

        assertFalse(player.hasEmptyHand());
    }
}
