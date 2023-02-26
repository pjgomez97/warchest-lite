package org.warchest.playerAction;

import org.warchest.board.Board;
import org.warchest.player.Player;
import org.warchest.round.Turn;
import org.warchest.unit.Unit;
import org.warchest.unit.UnitType;

public class RecruitAction extends PlayerAction {

    private final UnitType unitType;

    public RecruitAction(Player player, ActionType actionType, Unit unit) {
        super(player, actionType, unit);
        this.unitType = null;
    }

    public RecruitAction(Player player, ActionType actionType, Unit unit, UnitType unitType) {
        super(player, actionType, unit);
        this.unitType = unitType;
    }

    @Override
    public void perform(Turn playerTurn, Board board) {
        if (unit == null) {
            System.out.println("There are no units of the requested type or Royal in your hand");
            return;
        }

        Unit recruitmentUnit = unitType == null ? player.getUnitFromRecruitmentByType(unit.getType()) : player.getUnitFromRecruitmentByType(unitType);

        if (recruitmentUnit == null) {
            System.out.printf("There are no units of type %s left in the recruitment section\n", unit.getType());
            return;
        }

        player.recruitUnit(unit, recruitmentUnit);
        playerTurn.decreaseMovesLeft();
        playerTurn.addPlayerAction(this);
    }
}
