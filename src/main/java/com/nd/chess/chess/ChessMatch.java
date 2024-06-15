package com.nd.chess.chess;

import com.nd.chess.boardgame.Board;
import com.nd.chess.boardgame.Piece;
import com.nd.chess.boardgame.Position;
import com.nd.chess.chess.pieces.King;
import com.nd.chess.chess.pieces.Rook;

public class ChessMatch {

    private Board board;

    public ChessMatch() {
        this.board = new Board(8, 8);

        initialSetup();
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] matriz = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                matriz[i][j] = (ChessPiece) board.piece(i, j);
            }
        }

        return matriz;
    }

    public boolean[][] possibleMoves(Position origin){
        validateOriginPosition(origin);
        return board.piece(origin).possibleMoves();
    }

    public ChessPiece performChessMove(Position origin, Position target) {
        validateOriginPosition(origin);
        validateTargetPosition(origin, target);

        Piece capturedPiece = makeMove(origin, target);

        return (ChessPiece) capturedPiece;
    }

    private void validateOriginPosition(Position pos) {
        if (!board.ThereIsAPiece(pos)) {
            throw new ChessException("There is no piece on origin position!");
        }

        if(!board.piece(pos).isThereAnyPossibleMove()){
            throw new ChessException("There is no possible moves for the chosen piece!");
        }
    }

    private void validateTargetPosition(Position origin, Position target){
        if(!board.piece(origin).possibleMove(target)){
            throw new ChessException("The chosen piece can´t move to target position.");
        }
    }

    private Piece makeMove(Position origin, Position target) {
        Piece p = board.removePiece(origin);
        Piece c = board.removePiece(target);

        board.placePiece(p, target);
        return c;
    }

    private void initialSetup(){
        // WHITE PIECES
        board.placePiece(new Rook(board, Color.WHITE), new Position(0,7));
        board.placePiece(new King(board, Color.WHITE), new Position(4,5));
        board.placePiece(new Rook(board, Color.WHITE), new Position(7, 7));
        board.placePiece(new Rook(board, Color.WHITE), new Position(1, 7));
        board.placePiece(new Rook(board, Color.WHITE), new Position(2, 7));
        board.placePiece(new Rook(board, Color.WHITE), new Position(3, 7));
        board.placePiece(new Rook(board, Color.WHITE), new Position(5, 7));
        board.placePiece(new Rook(board, Color.WHITE), new Position(6, 7));

        // BLACK PIECES
        board.placePiece(new Rook(board, Color.BLACK), new Position(0, 0));
        board.placePiece(new King(board, Color.BLACK), new Position(4, 0));
        board.placePiece(new Rook(board, Color.BLACK), new Position(7, 0));
        board.placePiece(new Rook(board, Color.BLACK), new Position(1, 0));
        board.placePiece(new Rook(board, Color.BLACK), new Position(2, 0));
        board.placePiece(new Rook(board, Color.BLACK), new Position(3, 0));
        board.placePiece(new Rook(board, Color.BLACK), new Position(5, 0));
        board.placePiece(new Rook(board, Color.BLACK), new Position(6, 0));
    }
}
