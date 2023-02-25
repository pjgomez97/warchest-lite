package org.warchest.unit;

public class Royal implements Unit {

    private final UnitType type = UnitType.ROYAL;

    private final int totalUnits = 4;

    @Override
    public UnitType getType() {
        return type;
    }

    @Override
    public int getTotalUnits() {
        return totalUnits;
    }
}
