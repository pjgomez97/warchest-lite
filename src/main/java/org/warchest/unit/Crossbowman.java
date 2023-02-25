package org.warchest.unit;

public class Crossbowman implements Unit, StandardUnit {

    private final UnitType type = UnitType.CROSSBOWMAN;

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
