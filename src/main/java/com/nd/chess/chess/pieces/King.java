package com.nd.chess.chess.pieces;

import com.nd.chess.MainApplication;
import com.nd.chess.boardgame.Board;
import com.nd.chess.boardgame.Position;
import com.nd.chess.chess.ChessMatch;
import com.nd.chess.chess.ChessPiece;
import com.nd.chess.chess.Color;

import javafx.scene.image.Image;

public class King extends ChessPiece {

    private ChessMatch match;
    
    public King(Board board, Color color, ChessMatch match){
        super(board, color, new Image(MainApplication.class.getResource(String.format("images/pieces/king-%s.png",color.toString())).toString()));
        this.match = match;
    }

    @Override
    public String toString() {
        return "King";
    }

    private boolean testRookCastling(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return (p != null) && (p instanceof Rook) && (p.getColor() == getColor()) && (p.getMoveCount() == 0);
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

        // special move castling
        if(getMoveCount() == 0 && !match.isCheck()){
            // kingside rook
            Position posT1 = new Position(position.getRow() + 3, position.getColumn());
            if (testRookCastling(posT1)) {
                Position p1 = new Position(position.getRow() + 1, position.getColumn());
                Position p2 = new Position(position.getRow() + 2, position.getColumn());
                if(!getBoard().ThereIsAPiece(p1) && !getBoard().ThereIsAPiece(p2)){
                    m[position.getRow()+2][position.getColumn()] = true;
                }
            }
            // queenside rook
            posT1 = new Position(position.getRow() - 4, position.getColumn());
            if (testRookCastling(posT1)) {
                Position p1 = new Position(position.getRow() - 1, position.getColumn());
                Position p2 = new Position(position.getRow() - 2, position.getColumn());
                Position p3 = new Position(position.getRow() - 3, position.getColumn());
                if(!getBoard().ThereIsAPiece(p1) && !getBoard().ThereIsAPiece(p2) && !getBoard().ThereIsAPiece(p3)){
                    m[position.getRow()-2][position.getColumn()] = true;
                }
            }
        }

        return m;
    }

}
