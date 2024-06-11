package com.nd.chess.chess;

import com.nd.chess.boardgame.Position;

public class ChessPosition {
    private char column;
    private int row;

    public ChessPosition() {
    }

    public ChessPosition(char column, int row) {
        if (column < 'a' || column > 'h' || row < 1 || row > 8) {
            throw new ChessException("Error creating new ChessPosition: Valid values are from 'a1' to 'h8'");
        }
        this.column = column;
        this.row = column;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    protected Position toPosition() {
        // System.out.println("ROW:" + (int) ('h' - row) + ", COL:" + (column - 'a'));
        return new Position((int) ('h' - row), column - 'a');
    }

    public static ChessPosition fromPosition(Position position) {
        char column = (char) ('h' - position.getRow());
        int row = 8 - position.getColumn();

        System.out.println("ROW: " + position.getColumn() + ", " + (8 - position.getColumn()));
        System.out.println("COL: " + position.getRow() + ", " + (char) ('h' - position.getRow()));

        return new ChessPosition(column, row);
    }

    @Override
    public String toString() {
        return "Pos [" + column + "," + (int) row + "]";
    }

}
