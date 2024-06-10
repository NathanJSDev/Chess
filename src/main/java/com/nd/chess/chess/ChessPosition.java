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
        System.out.println((row - 'a')  + ", " + (column - 'a'));
        return new Position((row - 'a'), column - 'a');
    }

    protected static ChessPosition fromPosition(Position position) {
        System.out.println((char) ('a' - position.getColumn()) + ", " + (8 - position.getRow()));
        return new ChessPosition((char) ('a' - position.getColumn()), 8 - position.getRow());
    }

    @Override
    public String toString() {
        return "Pos [" + column + row + "]";
    }

}
