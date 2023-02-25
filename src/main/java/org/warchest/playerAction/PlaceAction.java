package org.warchest.playerAction;

import org.warchest.board.Board;
import org.warchest.board.Square;
import org.warchest.player.Player;
import org.warchest.round.Turn;
import org.warchest.unit.StandardUnit;
import org.warchest.unit.Unit;
import org.warchest.unit.UnitType;

public class PlaceAction extends PlayerAction {

    public PlaceAction(Player player, ActionType actionType, Unit unit, Square origin, Square destination) {
        super(player, actionType, unit, origin, destination);
    }

    @Override
    public void perform(Turn playerTurn, Board board) {
        if (unit == null) {
            System.out.println("Error. There are no units of the requested type in your hand");
            return;
        }

        if (unit.getType().equals(UnitType.ROYAL)) {
            System.out.println("Error. Royal units cannot be placed in the board");
            return;
        }

        if (board.getSquaresAdjacentTo(destination).stream().noneMatch(square -> square.getControlledBy() == player.getPlayerName())) {
            System.out.println("Error. There are no squares controlled by the current player adjacent to the destination one");
            return;
        }

        destination.setOccupiedBy((StandardUnit) unit);

        player.removeUnitFromHand(unit);

        playerTurn.decreaseMovesLeft();
        playerTurn.addPlayerAction(this);
    }
}
