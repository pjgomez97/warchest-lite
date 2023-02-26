package org.warchest.unit;

import org.warchest.board.Square;
import org.warchest.player.Player;
import org.warchest.playerAction.PlayerAction;

import java.util.List;

public class Knight implements Unit, StandardUnit {

    private Player owner;

    private final UnitType type = UnitType.KNIGHT;

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
        return origin.getPosition().isNextTo(target.getPosition());
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
