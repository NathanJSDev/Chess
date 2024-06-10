package com.nd.chess.chess;

import com.nd.chess.MainApplicationLauncher;
import com.nd.chess.boardgame.Board;
import com.nd.chess.boardgame.Position;
import com.nd.chess.chess.pieces.King;
import com.nd.chess.chess.pieces.Rook;

import javafx.scene.image.Image;

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

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup(){
        // WHITE PIECES
        placeNewPiece('c', 5, new Rook(board, Color.WHITE));
        board.placePiece(new ChessPiece(board, Color.WHITE, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/knight-WHITE.png"))), new Position(1, 7));
        board.placePiece(new ChessPiece(board, Color.WHITE, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/bishop-WHITE.png"))), new Position(2, 7));
        board.placePiece(new ChessPiece(board, Color.WHITE, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/queen-WHITE.png"))), new Position(3, 7));
        board.placePiece(new King(board, Color.WHITE), new Position(4,7));
        board.placePiece(new ChessPiece(board, Color.WHITE, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/bishop-WHITE.png"))), new Position(5, 7));
        board.placePiece(new ChessPiece(board, Color.WHITE, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/knight-WHITE.png"))), new Position(6, 7));
        board.placePiece(new Rook(board, Color.WHITE), new Position(7, 7));

        board.placePiece(new ChessPiece(board, Color.WHITE, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/pawn-WHITE.png"))), new Position(0, 6));
        board.placePiece(new ChessPiece(board, Color.WHITE, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/pawn-WHITE.png"))), new Position(1, 6));
        board.placePiece(new ChessPiece(board, Color.WHITE, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/pawn-WHITE.png"))), new Position(2, 6));
        board.placePiece(new ChessPiece(board, Color.WHITE, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/pawn-WHITE.png"))), new Position(3, 6));
        board.placePiece(new ChessPiece(board, Color.WHITE, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/pawn-WHITE.png"))), new Position(4, 6));
        board.placePiece(new ChessPiece(board, Color.WHITE, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/pawn-WHITE.png"))), new Position(5, 6));
        board.placePiece(new ChessPiece(board, Color.WHITE, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/pawn-WHITE.png"))), new Position(6, 6));
        board.placePiece(new ChessPiece(board, Color.WHITE, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/pawn-WHITE.png"))), new Position(7, 6));

        // BLACK PIECES
        board.placePiece(new Rook(board, Color.BLACK), new Position(0, 0));
        board.placePiece(new ChessPiece(board, Color.BLACK, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/knight-BLACK.png"))), new Position(1, 0));
        board.placePiece(new ChessPiece(board, Color.BLACK, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/bishop-BLACK.png"))), new Position(2, 0));
        board.placePiece(new ChessPiece(board, Color.BLACK, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/queen-BLACK.png"))), new Position(3, 0));
        board.placePiece(new King(board, Color.BLACK), new Position(4, 0));
        board.placePiece(new ChessPiece(board, Color.BLACK, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/bishop-BLACK.png"))), new Position(5, 0));
        board.placePiece(new ChessPiece(board, Color.BLACK, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/knight-BLACK.png"))), new Position(6, 0));
        board.placePiece(new Rook(board, Color.BLACK), new Position(7, 0));

        board.placePiece(new ChessPiece(board, Color.BLACK, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/pawn-BLACK.png"))), new Position(0, 1));
        board.placePiece(new ChessPiece(board, Color.BLACK, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/pawn-BLACK.png"))), new Position(1, 1));
        board.placePiece(new ChessPiece(board, Color.BLACK, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/pawn-BLACK.png"))), new Position(2, 1));
        board.placePiece(new ChessPiece(board, Color.BLACK, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/pawn-BLACK.png"))), new Position(3, 1));
        board.placePiece(new ChessPiece(board, Color.BLACK, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/pawn-BLACK.png"))), new Position(4, 1));
        board.placePiece(new ChessPiece(board, Color.BLACK, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/pawn-BLACK.png"))), new Position(5, 1));
        board.placePiece(new ChessPiece(board, Color.BLACK, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/pawn-BLACK.png"))), new Position(6, 1));
        board.placePiece(new ChessPiece(board, Color.BLACK, new Image(MainApplicationLauncher.class.getResourceAsStream("images/pieces/pawn-BLACK.png"))), new Position(7, 1));
    }
}
