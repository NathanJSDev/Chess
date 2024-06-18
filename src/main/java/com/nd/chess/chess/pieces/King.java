package com.nd.chess.chess.pieces;

import com.nd.chess.MainApplication;
import com.nd.chess.boardgame.Board;
import com.nd.chess.boardgame.Position;
import com.nd.chess.chess.ChessPiece;
import com.nd.chess.chess.Color;

import javafx.scene.image.Image;

public class King extends ChessPiece {
    
    public King(Board board, Color color){
        super(board, color, new Image(MainApplication.class.getResource(String.format("images/pieces/king-%s.png",color.toString())).toString()));
    }

    @Override
    public String toString() {
        return "King";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] m = new boolean[getBoard().getRows()][getBoard().getColumns()];

        // left
        for (int i = -1; i < 2; i++) {
            Position pos = new Position(position.getRow() - 1, position.getColumn() + i);
            if ((getBoard().positionExists(pos) && pos.getColumn() < getBoard().getColumns())
                    && (!getBoard().ThereIsAPiece(pos) || isThereAnOpponentPiece(pos))) {
                m[pos.getRow()][pos.getColumn()] = true;
            }
        }
        // right
        for (int i = -1; i < 2; i++) {
            Position pos = new Position(position.getRow() + 1, position.getColumn() + i);
            if ((getBoard().positionExists(pos) && pos.getColumn() < getBoard().getColumns()
                    && pos.getRow() < getBoard().getRows())
                    && (!getBoard().ThereIsAPiece(pos) || isThereAnOpponentPiece(pos))) {
                m[pos.getRow()][pos.getColumn()] = true;
            }
        }
        // above
        Position pos = new Position(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(pos) && (!getBoard().ThereIsAPiece(pos) || isThereAnOpponentPiece(pos))) {
            m[pos.getRow()][pos.getColumn()] = true;
        }
        // below
        pos = new Position(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(pos) && pos.getColumn() < getBoard().getColumns()) {
            if (!getBoard().ThereIsAPiece(pos) || isThereAnOpponentPiece(pos)) {
                m[pos.getRow()][pos.getColumn()] = true;
            }
        }

        return m;
    }

}
