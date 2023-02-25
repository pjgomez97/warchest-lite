package org.warchest.unit;

import org.warchest.player.Player;

public interface Unit {

    Player getOwner();

    void setOwner(Player player);

    UnitType getType();
}
