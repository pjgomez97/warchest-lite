package org.warchest.playerAction;

import org.warchest.board.Board;
import org.warchest.player.Player;
import org.warchest.round.Turn;
import org.warchest.unit.Unit;

public class InitiativeAction extends PlayerAction {

    public InitiativeAction(Player player, ActionType actionType, Unit unit) {
        super(player, actionType, unit);
    }

    @Override
    public void perform(Turn playerTurn, Board board) {
        if (unit == null) {
            System.out.println("There are no units of the requested type in your hand");
            return;
        }

        if (playerTurn.getPlayerActions().stream().anyMatch(playerAction -> playerAction.getActionType().equals(ActionType.INITIATIVE))) {
            System.out.println("You have already requested initiative during this round");
            return;
        }

        player.discardUnitFromHand(unit);
        playerTurn.decreaseMovesLeft();
        playerTurn.addPlayerAction(this);
    }
}
