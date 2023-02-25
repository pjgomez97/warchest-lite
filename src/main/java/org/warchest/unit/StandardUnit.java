package org.warchest.unit;

import org.warchest.round.PlayerAction;

import java.util.List;

public interface StandardUnit {

    boolean hasFreeAttack(List<PlayerAction> playerActions);

    boolean hasFreeMove(List<PlayerAction> playerActions);
}
