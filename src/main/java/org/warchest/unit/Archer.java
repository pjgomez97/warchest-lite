package org.warchest.unit;

import org.warchest.round.PlayerAction;

import java.util.List;

public class Archer implements Unit, StandardUnit {

    private final UnitType type = UnitType.ARCHER;

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
