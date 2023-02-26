package org.warchest.unit;

import org.warchest.board.Square;
import org.warchest.player.Player;
import org.warchest.playerAction.ActionType;
import org.warchest.playerAction.PlayerAction;

import java.util.List;

public class Cavalry implements Unit, StandardUnit {

    private Player owner;

    private final UnitType type = UnitType.CAVALRY;

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Player player) {
        this.owner = player;
    }

    @Override
    public UnitType getType() {
        return type;
    }

    @Override
    public boolean canAttack(Square origin, Square target) {
        return  Math.abs(origin.getPosition().row() - target.getPosition().row()) <= 1 &&
                Math.abs(origin.getPosition().column() - target.getPosition().column()) <= 1;
    }

    @Override
    public boolean hasFreeAttack(List<PlayerAction> playerActions) {
        if (playerActions.size() == 0) {
            return false;
        }

        PlayerAction lastAction = playerActions.get(playerActions.size() - 1);

        return lastAction.getActionType().equals(ActionType.MOVE) && lastAction.getUnit() == this;
    }

    @Override
    public boolean canMove(Square origin, Square target) {
        return  Math.abs(origin.getPosition().row() - target.getPosition().row()) == 1 && Math.abs(origin.getPosition().column() - target.getPosition().column()) == 0 ||
                Math.abs(origin.getPosition().row() - target.getPosition().row()) == 0 && Math.abs(origin.getPosition().column() - target.getPosition().column()) == 1;
    }

    @Override
    public boolean hasFreeMove(List<PlayerAction> playerActions) {
        return false;
    }
}
