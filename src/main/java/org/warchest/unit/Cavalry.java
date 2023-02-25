package org.warchest.unit;

public class Cavalry implements Unit, StandardUnit {

    private final UnitType type = UnitType.CAVALRY;

    private final int totalUnits = 4;

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
