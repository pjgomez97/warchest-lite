package org.warchest.unit;

import org.warchest.round.PlayerAction;

import java.util.List;

public class Knight implements Unit, StandardUnit {

    private final UnitType type = UnitType.KNIGHT;

    private final int totalUnits = 5;

    @Override
    public UnitType getType() {
        return type;
    }

    @Override
    public int getTotalUnits() {
        return totalUnits;
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
