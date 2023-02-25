package org.warchest.board;

import org.warchest.player.PlayerName;
import org.warchest.unit.StandardUnit;
import org.warchest.unit.Unit;
import org.warchest.util.Console;

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

    public boolean isZone() {
        return isZone;
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
            return Console.printRed("C");
        } else if (controlledBy == PlayerName.WOLF) {
            return Console.printBlue("W");
        } else if (occupiedBy != null) {
            Unit unit = (Unit) occupiedBy;
            String unitName = unit.getType().toString().substring(0, 2).toUpperCase();
            if (!isZone) {
                return unit.getOwner().getPlayerName() == PlayerName.CROW ? Console.printRed(unitName) : Console.printBlue(unitName);
            } else {
                return unit.getOwner().getPlayerName() == PlayerName.CROW ? Console.printYellow(unitName) : Console.printPurple(unitName);
            }
        } else if (isZone) {
            return Console.printGreen("@");
        } else {
            return "-";
        }
    }
}
