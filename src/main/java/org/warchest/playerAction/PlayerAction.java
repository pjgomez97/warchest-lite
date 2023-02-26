package org.warchest.playerAction;

import org.warchest.board.Board;
import org.warchest.player.Player;
import org.warchest.round.Turn;
import org.warchest.unit.Unit;

public abstract class PlayerAction {

    protected final Player player;

    protected final ActionType actionType;

    protected final Unit unit;

    protected PlayerAction(Player player, ActionType actionType, Unit unit) {
        this.player = player;
        this.actionType = actionType;
        this.unit = unit;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Unit getUnit() {
        return unit;
    }

    public void perform(Turn playerTurn, Board board) {
        System.out.println("Not implemented!");
    }
}
