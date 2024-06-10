package com.nd.chess.chess.pieces;

import com.nd.chess.MainApplication;
import com.nd.chess.boardgame.Board;
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

}
