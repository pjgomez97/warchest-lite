package org.warchest.unit;

public class Royal implements Unit {

    private final UnitType type = UnitType.ROYAL;

    @Override
    public UnitType getType() {
        return type;
    }
}
