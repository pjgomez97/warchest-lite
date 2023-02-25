package org.warchest.unit;

import org.warchest.player.Player;

public class Royal implements Unit {

    private Player owner;

    private final UnitType type = UnitType.ROYAL;

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
}
