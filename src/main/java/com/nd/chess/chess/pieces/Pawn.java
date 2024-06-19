package com.nd.chess.chess.pieces;

import com.nd.chess.MainApplication;
import com.nd.chess.boardgame.Board;
import com.nd.chess.boardgame.Position;
import com.nd.chess.chess.ChessPiece;
import com.nd.chess.chess.Color;

import javafx.scene.image.Image;

public class Pawn extends ChessPiece {

    public Pawn(Board board, Color color) {
        super(board, color, new Image(MainApplication.class
                .getResource(String.format("images/pieces/pawn-%s.png", color.toString())).toString()));
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] m = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(getPosition().getRow(), getPosition().getColumn());

        if (getColor() == Color.WHITE) {
            // above
            p.setValues(position.getRow(), position.getColumn() - 1);
            if (getBoard().positionExists(p) && !getBoard().ThereIsAPiece(p)) {
                m[p.getRow()][p.getColumn()] = true;
            }

            // above up
            p.setValues(position.getRow(), position.getColumn() - 2);
            if (getBoard().positionExists(p) && !getBoard().ThereIsAPiece(p) && getMoveCount() == 0) {
                Position p2 = new Position(p.getRow(), p.getColumn() + 1);
                if (getBoard().positionExists(p2) && !getBoard().ThereIsAPiece(p2)) {
                    m[p.getRow()][p.getColumn()] = true;
                }
            }
            // left
            p.setValues(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().positionExists(p) && isThereAnOpponentPiece(p)) {
                m[p.getRow()][p.getColumn()] = true;
            }
            // right
            p.setValues(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().positionExists(p) && isThereAnOpponentPiece(p)) {
                m[p.getRow()][p.getColumn()] = true;
            }

        } else if (getColor() == Color.BLACK) {
            // above
            p.setValues(position.getRow(), position.getColumn() + 1);
            if (getBoard().positionExists(p) && !getBoard().ThereIsAPiece(p)) {
                m[p.getRow()][p.getColumn()] = true;
            }

            // above up
            p.setValues(position.getRow(), position.getColumn() + 2);
            if (getBoard().positionExists(p) && !getBoard().ThereIsAPiece(p) && getMoveCount() == 0) {
                Position p2 = new Position(p.getRow(), p.getColumn() - 1);
                if (getBoard().positionExists(p2) && !getBoard().ThereIsAPiece(p2)) {
                    m[p.getRow()][p.getColumn()] = true;
                }
            }
            // left
            p.setValues(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().positionExists(p) && isThereAnOpponentPiece(p)) {
                m[p.getRow()][p.getColumn()] = true;
            }
            // right
            p.setValues(position.getRow() + 1, position.getColumn() + 1);
            if (getBoard().positionExists(p) && isThereAnOpponentPiece(p)) {
                m[p.getRow()][p.getColumn()] = true;
            }
        }

        return m;
    }

    @Override
    public String toString() {
        return "Pawn";
    }

}
