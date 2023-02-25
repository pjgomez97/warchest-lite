package org.warchest.board;

import org.warchest.player.PlayerName;
import org.warchest.unit.Unit;

public class Square {

    private boolean isZone = false;

    private PlayerName controlledBy;

    private Unit occupiedBy;

    public static Square buildFromString(String s) {
        Square square = new Square();

        switch (s) {
            case "C": square.controlledBy = PlayerName.CROW;
            break;
            case "W": square.controlledBy = PlayerName.WOLF;
            break;
            case "@": square.isZone = true;
            default: break;
        }

        return square;
    }

    public PlayerName getControlledBy() {
        return controlledBy;
    }

    public void setControlledBy(PlayerName controlledBy) {
        this.controlledBy = controlledBy;
    }

    public Unit getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(Unit occupiedBy) {
        this.occupiedBy = occupiedBy;
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
