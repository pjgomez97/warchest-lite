package org.warchest.unit;

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
    public void attack(int x, int y) {

    }

    @Override
    public void move(int x, int y) {

    }
}
