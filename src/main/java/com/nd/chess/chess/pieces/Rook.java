package com.nd.chess.chess.pieces;

import com.nd.chess.MainApplication;
import com.nd.chess.boardgame.Board;
import com.nd.chess.boardgame.Position;
import com.nd.chess.chess.ChessPiece;
import com.nd.chess.chess.Color;

import javafx.scene.image.Image;

public class Rook extends ChessPiece {

    public Rook(Board board, Color color) {
        super(board, color, new Image(MainApplication.class.getResource(String.format("images/pieces/rook-%s.png",color.toString())).toString()));
    }

    @Override
    public String toString() {
        return "Rook";
    }

    // @Override
    // public boolean[][] possibleMoves() {
    //     boolean[][] m = new boolean[getBoard().getRows()][getBoard().getColumns()];
    //     Position p = new Position(0, 0);
    //     System.out.println("--------------------------------------------------------------------------------");
    //     System.out.println("--------------------------------------------------------------------------------");
    //     System.out.println("--------------------------------------------------------------------------------");

    //     p.setValues(position.getRow() - 1, position.getColumn());
    //     while (getBoard().positionExists(p) && !getBoard().ThereIsAPiece(p)) {
    //         m[p.getRow()][p.getColumn()] = true;
    //         p.setRow(p.getRow() - 1);
    //         System.out.println(p);
    //     }
    //     if(getBoard().positionExists(p) && isThereAnOpponentPiece(p)){
    //         m[p.getRow()][p.getColumn()] = true;
    //         System.out.println(p);
    //     }

    //     System.out.println("--------------------------------------------------------------------------------");

    //     p.setValues(position.getRow() + 1, position.getColumn());
    //     while (getBoard().positionExists(p) && !getBoard().ThereIsAPiece(p)) {
    //         m[p.getRow()][p.getColumn()] = true;
    //         p.setRow(p.getRow() + 1);
    //         System.out.println(p);
    //     }
    //     if(getBoard().positionExists(p) && isThereAnOpponentPiece(p)){
    //         m[p.getRow()][p.getColumn()] = true;
    //         System.out.println(p);
    //     }

    //     System.out.println("--------------------------------------------------------------------------------");

    //     p.setValues(position.getRow(), position.getColumn() - 1);
    //     while (getBoard().positionExists(p) && !getBoard().ThereIsAPiece(p)) {
    //         m[p.getRow()][p.getColumn()] = true;
    //         p.setColumn(p.getColumn() - 1);
    //         System.out.println(p);
    //     }
    //     if(getBoard().positionExists(p) && isThereAnOpponentPiece(p)){
    //         m[p.getRow()][p.getColumn()] = true;
    //         System.out.println(p);
    //     }

    //     System.out.println("--------------------------------------------------------------------------------");

    //     p.setValues(position.getRow(), position.getColumn() + 1);
    //     while (getBoard().positionExists(p) && !getBoard().ThereIsAPiece(p)) {
    //         m[p.getRow()][p.getColumn()] = true;
    //         p.setColumn(p.getColumn() + 1);
    //         System.out.println(p);
    //     }
    //     if(getBoard().positionExists(p) && isThereAnOpponentPiece(p)){
    //         m[p.getRow()][p.getColumn()] = true;
    //         System.out.println(p);
    //     }

    //     System.out.println("********************************************************************************");

    //     return m;
    // }
    
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] m = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        int leftLimit = 0;
        int rightLimit = getBoard().getRows();
        int aboveLimit = 0;
        int belowLimit = getBoard().getColumns();


        // right
        p.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(p) && p.getRow() < rightLimit) {
            for (int i = p.getRow(); i < rightLimit; i++) {
                if (getBoard().positionExists(p)) {
                    if (getBoard().ThereIsAPiece(p)) break;
                    m[p.getRow()][p.getColumn()] = true;
                    p.setRow(i);
                } else{
                    m[p.getRow()][p.getColumn()] = true;
                    break;
                }
            }
            if (getBoard().positionExists(p) && isThereAnOpponentPiece(p)) {
                m[p.getRow()][p.getColumn()] = true;
            }
        }

        // left
        p.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(p)) {
            for (int i = p.getRow(); i >= leftLimit; i--) {
                if (getBoard().positionExists(p)) {
                    if (getBoard().ThereIsAPiece(p))
                        break;
                    m[p.getRow()][p.getColumn()] = true;
                    p.setRow(i);
                } else
                    break;
            }
            if (getBoard().positionExists(p) && isThereAnOpponentPiece(p)) {
                m[p.getRow()][p.getColumn()] = true;
            }
        }

        p.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(p)) {
            for (int i = p.getColumn(); i >= aboveLimit; i--) {
                if (getBoard().positionExists(p)) {
                    if (getBoard().ThereIsAPiece(p)) break;
                    m[p.getRow()][p.getColumn()] = true;
                    p.setColumn(i);
                } else
                    break;
            }
            if (getBoard().positionExists(p) && isThereAnOpponentPiece(p)) {
                m[p.getRow()][p.getColumn()] = true;
            }
        }

        p.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(p) && p.getColumn() < belowLimit) {
            for (int i = p.getColumn(); i < belowLimit; i++) {
                if (getBoard().positionExists(p)) {
                    if (getBoard().ThereIsAPiece(p)) break;
                    m[p.getRow()][p.getColumn()] = true;
                    p.setColumn(i);
                } else {
                    m[p.getRow()][p.getColumn()] = true;
                    break;
                }
            }
            if (getBoard().positionExists(p) && isThereAnOpponentPiece(p)) {
                m[p.getRow()][p.getColumn()] = true;
            }
        }

        return m;
    }

}
