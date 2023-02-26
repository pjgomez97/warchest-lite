package org.warchest.unit;

import org.warchest.board.Square;
import org.warchest.player.Player;
import org.warchest.playerAction.PlayerAction;

import java.util.List;

public class Archer implements Unit, StandardUnit {

    private Player owner;

    private final UnitType type = UnitType.ARCHER;

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
        int rowDistance = origin.getPosition().rowDistanceTo(target.getPosition());
        int columnDistance = origin.getPosition().columnDistanceTo(target.getPosition());

        return (rowDistance <= 2 && columnDistance == 0) || (rowDistance == 0 && columnDistance <= 2) || (rowDistance <= 2 && columnDistance == rowDistance);
    }

    @Override
    public boolean hasFreeAttack(List<PlayerAction> playerActions) {
        return false;
    }

    @Override
    public boolean canMove(Square origin, Square target) {
        return origin.getPosition().isNextOrthogonallyTo(target.getPosition());
    }

    @Override
    public boolean hasFreeMove(List<PlayerAction> playerActions) {
        return false;
    }
}
