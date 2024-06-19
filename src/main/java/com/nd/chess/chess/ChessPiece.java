package com.nd.chess.chess;

import com.nd.chess.boardgame.Board;
import com.nd.chess.boardgame.Piece;
import com.nd.chess.boardgame.Position;

import javafx.scene.image.Image;

public abstract class ChessPiece extends Piece {
    
    private Color color;

    private Image img;

    private int moveCount;

    public ChessPiece() {
    }

    public ChessPiece(Board board, Color color, Image img) {
        super(board);
        this.img = img;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Image getImg() {
        return img;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void increaseMoveCount(){
        moveCount++;
    }

    public void decreaseMoveCount(){
        moveCount--;
    }

    public Position getPosition(){
        return position;
    }

    protected boolean isThereAnOpponentPiece(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p!=null&&p.getColor()!=getColor();
    }

}
