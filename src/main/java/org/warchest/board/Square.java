package org.warchest.board;

import org.warchest.player.PlayerName;
import org.warchest.unit.UnitType;

import java.util.Arrays;

public class Square {

    private boolean isZone = false;

    private PlayerName controlledBy;

    private UnitType occupiedBy;

    public static Square buildFromString(String s) {
        Square square = new Square();

        switch (s) {
            case "C": square.controlledBy = PlayerName.CROW;
            break;
            case "W": square.controlledBy = PlayerName.WOLF;
            break;
            case "@": square.isZone = true;
            case "-": break;
            default: square.occupiedBy = Arrays.stream(UnitType.values()).filter(unitType -> unitType.toString().startsWith(s)).findFirst().get();
        }

        return square;
    }

    @Override
    public String toString() {
        if (controlledBy == PlayerName.CROW) {
            return "C";
        } else if (controlledBy == PlayerName.WOLF) {
            return "W";
        } else if (isZone) {
            return "@";
        } else if (occupiedBy != null) {
            return occupiedBy.toString().substring(0, 2).toUpperCase();
        } else {
            return "-";
        }
    }
}
