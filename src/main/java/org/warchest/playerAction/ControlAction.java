package org.warchest.playerAction;

import org.warchest.board.Board;
import org.warchest.board.Square;
import org.warchest.player.Player;
import org.warchest.round.Turn;
import org.warchest.unit.Unit;

public class ControlAction extends PlayerAction {

    public ControlAction(Player player, ActionType actionType, Unit unit, Square origin, Square destination) {
        super(player, actionType, unit, origin, destination);
    }

    @Override
    public void perform(Turn playerTurn, Board board) {
        if (unit == null) {
            System.out.println("Error. There are no units of the requested type in your hand");
            return;
        }

        if (destination.getOccupiedBy() == null) {
            System.out.println("You need to place a unit in a square first in order to control it");
            return;
        }

        if (!destination.isZone()) {
            System.out.println("You can use this action only in Control zones");
            return;
        }

        if (((Unit)destination.getOccupiedBy()).getOwner() != player) {
            System.out.println("The unit present on that square belongs to a different player");
            return;
        }

        if (destination.getControlledBy() == player.getPlayerName()) {
            System.out.println("You already control that square");
            return;
        }

        destination.setControlledBy(player.getPlayerName());
        player.discardUnitFromHand(unit);

        playerTurn.decreaseMovesLeft();
        playerTurn.addPlayerAction(this);
    }
}
