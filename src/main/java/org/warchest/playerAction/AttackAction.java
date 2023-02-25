package org.warchest.playerAction;

import org.warchest.board.Square;
import org.warchest.player.Player;
import org.warchest.round.Turn;
import org.warchest.unit.Unit;

public class AttackAction extends PlayerAction {

    public AttackAction(Player player, ActionType actionType, Unit unit, Square origin, Square destination) {
        super(player, actionType, unit, origin, destination);
    }

    @Override
    public void perform(Turn playerTurn) {
        if (!this.origin.getOccupiedBy().hasFreeAttack(playerTurn.getPlayerActions())) {
            playerTurn.decreaseMovesLeft();
        }
        playerTurn.addPlayerAction(this);
    }
}
