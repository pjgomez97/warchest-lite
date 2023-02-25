package org.warchest.unit;

public class Archer implements Unit, StandardUnit {

    private final UnitType type = UnitType.ARCHER;

    private final int totalUnits = 4;

    @Override
    public UnitType getType() {
        return type;
    }

    @Override
    public int getTotalUnits() {
        return totalUnits;
    }

    public void attack(int x, int y) {

    }

    public void move(int x, int y) {

    }
}
