package org.warchest.board;

import org.warchest.player.PlayerName;
import org.warchest.unit.StandardUnit;
import org.warchest.unit.Unit;

public class Square {

    private Position position;

    private boolean isZone = false;

    private PlayerName controlledBy;

    private StandardUnit occupiedBy;

    public static Square buildFromString(String s, Position position) {
        Square square = new Square();

        switch (s) {
            case "C": square.controlledBy = PlayerName.CROW;
            break;
            case "W": square.controlledBy = PlayerName.WOLF;
            break;
            case "@": square.isZone = true;
            default: break;
        }

        square.setPosition(position);

        return square;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public PlayerName getControlledBy() {
        return controlledBy;
    }

    public void setControlledBy(PlayerName controlledBy) {
        this.controlledBy = controlledBy;
    }

    public StandardUnit getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(StandardUnit occupiedBy) {
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
            return ((Unit) occupiedBy).getType().toString().substring(0, 2).toUpperCase();
        } else {
            return "-";
        }
    }
}
