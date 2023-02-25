package org.warchest.unit;

import org.warchest.board.Square;
import org.warchest.player.Player;
import org.warchest.playerAction.PlayerAction;

import java.util.List;

public class Mercenary implements Unit, StandardUnit {

    private Player owner;

    private static final UnitType type = UnitType.MERCENARY;

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
        return false;
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
