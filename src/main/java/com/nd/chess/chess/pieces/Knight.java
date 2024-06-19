package com.nd.chess.chess.pieces;

import com.nd.chess.MainApplication;
import com.nd.chess.boardgame.Board;
import com.nd.chess.boardgame.Position;
import com.nd.chess.chess.ChessPiece;
import com.nd.chess.chess.Color;

import javafx.scene.image.Image;

public class Knight extends ChessPiece{

    public Knight(Board board, Color color) {
        super(board, color, new Image(MainApplication.class.getResource(String.format("images/pieces/knight-%s.png", color.toString())).toString()));
    }

    public boolean canMove(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] m = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(getPosition().getRow(), getPosition().getColumn());

        // above left
        p.setValues(position.getRow() - 1, position.getColumn() - 2);
        if(getBoard().positionExists(p) && canMove(p)){
            m[p.getRow()][p.getColumn()]=true;
        }

        // above right
        p.setValues(position.getRow() + 1, position.getColumn() - 2);
        if(getBoard().positionExists(p) && canMove(p)){
            m[p.getRow()][p.getColumn()]=true;
        }

        // left above
        p.setValues(position.getRow() - 2, position.getColumn() - 1);
        if(getBoard().positionExists(p) && canMove(p)){
            m[p.getRow()][p.getColumn()]=true;
        }

        // left below
        p.setValues(position.getRow() - 2, position.getColumn() + 1);
        if(getBoard().positionExists(p) && canMove(p)){
            m[p.getRow()][p.getColumn()]=true;
        }

        // right above
        p.setValues(position.getRow() + 2, position.getColumn() - 1);
        if(getBoard().positionExists(p) && canMove(p)){
            m[p.getRow()][p.getColumn()]=true;
        }

        // right below
        p.setValues(position.getRow() + 2, position.getColumn() + 1);
        if(getBoard().positionExists(p) && canMove(p)){
            m[p.getRow()][p.getColumn()]=true;
        }

        // below left
        p.setValues(position.getRow() - 1, position.getColumn() + 2);
        if(getBoard().positionExists(p) && canMove(p)){
            m[p.getRow()][p.getColumn()]=true;
        }

        // below right
        p.setValues(position.getRow() + 1, position.getColumn() + 2);
        if(getBoard().positionExists(p) && canMove(p)){
            m[p.getRow()][p.getColumn()]=true;
        }

        return m;
    }

    @Override
    public String toString() {
        return "Knight";
    }
    
}
