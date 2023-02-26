package org.warchest.playerAction;

import org.warchest.board.Board;
import org.warchest.board.Square;
import org.warchest.player.Player;
import org.warchest.round.Turn;
import org.warchest.unit.StandardUnit;
import org.warchest.unit.Unit;

public class AttackAction extends PlayerAction {

    private final Square origin;

    private final Square destination;

    public AttackAction(Player player, ActionType actionType, Unit unit, Square origin, Square destination) {
        super(player, actionType, unit);
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void perform(Turn playerTurn, Board board) {
        if (unit == null) {
            System.out.println("There are no units of the requested type in your hand");
            return;
        }

        StandardUnit boardUnit = origin.getOccupiedBy();

        if (boardUnit == null) {
            System.out.println("There are no units on the origin square");
            return;
        }

        if (!((Unit)boardUnit).getType().equals(unit.getType())) {
            System.out.println("The square unit is not of type " + unit.getType());
            return;
        }

        if (destination.getOccupiedBy() == null) {
            System.out.println("There are no units on the target square");
            return;
        }

        if (!boardUnit.canAttack(origin, destination)) {
            System.out.println("This unit cannot attack the destination square");
            return;
        }

        destination.setOccupiedBy(null);

        if (!boardUnit.hasFreeAttack(playerTurn.getPlayerActions())) {
            playerTurn.decreaseMovesLeft();
        }

        player.discardUnitFromHand(unit);

        playerTurn.addPlayerAction(this);
    }
}
