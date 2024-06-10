package com.nd.chess.chess.pieces;

import com.nd.chess.MainApplication;
import com.nd.chess.boardgame.Board;
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

}
