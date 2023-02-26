package org.warchest.board;

public record Position(int row, int column) {

    public boolean isNextTo(Position position) {
        return rowDistanceTo(position) <= 1 && columnDistanceTo(position) <= 1;
    }

    public boolean isNextOrthogonallyTo(Position position) {
        return orthogonalDistanceTo(position) == 1;
    }

    public int orthogonalDistanceTo(Position position) {
        return rowDistanceTo(position) + columnDistanceTo(position);
    }

    public int rowDistanceTo(Position position) {
        return Math.abs(row - position.row);
    }

    public int columnDistanceTo(Position position) {
        return Math.abs(column - position.column);
    }
}
