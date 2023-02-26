package org.warchest.playerAction;

import org.warchest.board.Board;
import org.warchest.player.Player;
import org.warchest.round.Turn;
import org.warchest.unit.Unit;

public class RecruitAction extends PlayerAction {

    public RecruitAction(Player player, ActionType actionType, Unit unit) {
        super(player, actionType, unit);
    }

    @Override
    public void perform(Turn playerTurn, Board board) {
        if (unit == null) {
            System.out.println("Error. There are no units of the requested type in your hand");
            return;
        }

        Unit recruitmentUnit = player.getUnitFromRecruitmentByType(unit.getType());

        if (recruitmentUnit == null) {
            System.out.printf("There are no units of type %s left in the recruitment section\n", unit.getType());
            return;
        }

        player.recruitUnit(unit, recruitmentUnit);
        playerTurn.decreaseMovesLeft();
        playerTurn.addPlayerAction(this);
    }
}
