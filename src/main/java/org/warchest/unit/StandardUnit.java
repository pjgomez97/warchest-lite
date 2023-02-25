package org.warchest.unit;

import org.warchest.board.Square;
import org.warchest.playerAction.PlayerAction;

import java.util.List;

public interface StandardUnit {

    boolean canAttack(Square origin, Square target);

    boolean hasFreeAttack(List<PlayerAction> playerActions);

    boolean canMove(Square origin, Square target);

    boolean hasFreeMove(List<PlayerAction> playerActions);
}
