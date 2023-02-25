package org.warchest.unit;

public class Mercenary implements Unit, StandardUnit {

    private static final UnitType type = UnitType.MERCENARY;

    private static final int totalUnits = 5;

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
