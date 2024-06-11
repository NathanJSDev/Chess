package com.nd.chess.boardgame;

public abstract class Piece {
    
    protected Position position;

    private Board board;

    public Piece() {
    }

    public Piece(Board board) {
        this.board = board;
        this.position = null;
    }

    protected Board getBoard() {
        return board;
    }

    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove(){
        boolean[][] m = possibleMoves();
        for (boolean[] y : m) {
            for (Boolean x : y) {
                if(x) return true;
            }
        }
        return false;
    }

}
