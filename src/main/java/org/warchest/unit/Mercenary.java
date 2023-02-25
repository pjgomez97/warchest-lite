package org.warchest.unit;

import org.warchest.playerAction.PlayerAction;

import java.util.List;

public class Mercenary implements Unit, StandardUnit {

    private static final UnitType type = UnitType.MERCENARY;

    @Override
    public UnitType getType() {
        return type;
    }

    @Override
    public boolean hasFreeAttack(List<PlayerAction> playerActions) {
        return false;
    }

    @Override
    public boolean hasFreeMove(List<PlayerAction> playerActions) {
        return false;
    }
}
