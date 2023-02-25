package org.warchest.playerAction;

import org.warchest.board.Square;
import org.warchest.player.Player;
import org.warchest.round.Turn;
import org.warchest.unit.Unit;

public abstract class PlayerAction {

    protected final Player player;

    protected final ActionType actionType;

    protected final Unit unit;

    protected final Square origin;

    protected final Square destination;

    protected PlayerAction(Player player, ActionType actionType, Unit unit, Square origin, Square destination) {
        this.player = player;
        this.actionType = actionType;
        this.unit = unit;
        this.origin = origin;
        this.destination = destination;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void perform(Turn playerTurn) {
        System.out.println("Not implemented!");
    }
}
