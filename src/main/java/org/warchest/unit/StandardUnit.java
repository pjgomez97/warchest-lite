package org.warchest.unit;

import org.warchest.playerAction.PlayerAction;

import java.util.List;

public interface StandardUnit {

    boolean hasFreeAttack(List<PlayerAction> playerActions);

    boolean hasFreeMove(List<PlayerAction> playerActions);
}
