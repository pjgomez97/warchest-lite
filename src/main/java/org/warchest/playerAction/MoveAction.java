package org.warchest.playerAction;

import org.warchest.board.Board;
import org.warchest.board.Square;
import org.warchest.player.Player;
import org.warchest.round.Turn;
import org.warchest.unit.Unit;

public class MoveAction extends PlayerAction {

    private final Square origin;

    private final Square destination;

    public MoveAction(Player player, ActionType actionType, Unit unit, Square origin, Square destination) {
        super(player, actionType, unit);
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void perform(Turn playerTurn, Board board) {
        if (!this.origin.getOccupiedBy().hasFreeMove(playerTurn.getPlayerActions())) {
            playerTurn.decreaseMovesLeft();
        }
        playerTurn.addPlayerAction(this);
    }
}
