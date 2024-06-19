package com.nd.chess.chess;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import com.nd.chess.boardgame.Board;
import com.nd.chess.boardgame.Piece;
import com.nd.chess.boardgame.Position;
import com.nd.chess.chess.pieces.Bishop;
import com.nd.chess.chess.pieces.King;
import com.nd.chess.chess.pieces.Knight;
import com.nd.chess.chess.pieces.Pawn;
import com.nd.chess.chess.pieces.Rook;

public class ChessMatch {

    private int turn;
    private Color currentPlayer;
    private Board board;

    private boolean check = false;
    private boolean checkMate = false;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch() {
        this.board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn(){
        return turn;
    }

    public Color getCurrentPlayer(){
        return currentPlayer;
    }

    public boolean isCheck(){
        return check;
    }

    public boolean isCheckMate() {
        return checkMate;
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

        if(testCheck(currentPlayer)){
            check = false;
            undoMove(origin, target, capturedPiece);
            throw new ChessException("");
        }
        check = testCheck(opponent(currentPlayer)) ? true : false;

        if (testCheckMate(opponent(currentPlayer))) {
            checkMate = true;
        } else
            nextTurn();
        ;

        return (ChessPiece) capturedPiece;
    }

    private void validateOriginPosition(Position pos) {
        if (!board.ThereIsAPiece(pos)) {
            throw new ChessException("There is no piece on origin position!");
        }

        if(currentPlayer != ((ChessPiece)board.piece(pos)).getColor()){
            throw new ChessException("The chosen piece is not yours!");
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

    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer==Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    public boolean isYourTurn(Position position){
        if (!board.ThereIsAPiece(position)) {
            throw new ChessException("There is no piece on origin position!");
        }

        if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()){
            return false;
        }

        return true;
    }

    private Color opponent(Color color){
        return (Color.WHITE == color)? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king(Color color){
        List<Piece> list = piecesOnTheBoard.stream().filter(x->color==((ChessPiece)x).getColor()).collect(Collectors.toList());
        for (Piece piece : list) {
            if (piece instanceof King) {
                return (ChessPiece)piece;
            }
        }
        throw new IllegalStateException("There is no " + color.toString() + " king on the board!");
    }

    private boolean testCheck(Color color){
        Position kingPosition = king(color).getPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x->((ChessPiece)x).getColor()==opponent(color)).collect(Collectors.toList());
        for (Piece piece : opponentPieces) {
            boolean[][] m = piece.possibleMoves();
            if(m[kingPosition.getRow()][kingPosition.getColumn()]){
                return true;
            }
        }
        return false;
    }

    private boolean testCheckMate(Color color) {
        if (!testCheck(color)) {
            return false;
        }
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> color == ((ChessPiece) x).getColor())
                .collect(Collectors.toList());
        for (Piece piece : list) {
            boolean[][] m = piece.possibleMoves();
            for (int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getColumns(); j++) {
                    if (m[i][j]) {
                        Position origin = ((ChessPiece) piece).getPosition();
                        Position target = new Position(i, j);
                        Piece capturedPiece = makeMove(origin, target);
                        boolean testCheck = testCheck(color);
                        undoMove(origin, target, capturedPiece);
                        if (!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    private Piece makeMove(Position origin, Position target) {
        ChessPiece p = (ChessPiece)board.removePiece(origin);
        p.increaseMoveCount();
        Piece c = board.removePiece(target);

        board.placePiece(p, target);

        if(c!=null){
            piecesOnTheBoard.remove(c);
            capturedPieces.add(c);
        }
        return c;
    }

    private void undoMove(Position origin, Position target, Piece capturedPiece){
        ChessPiece p = (ChessPiece)board.removePiece(target);
        board.placePiece(p, origin);
        p.decreaseMoveCount();

        if(capturedPiece!=null){
            board.placePiece(capturedPiece, target);

            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add (capturedPiece);
        }
    }

    private void placePiece(ChessPiece piece, Position pos){
        board.placePiece(piece, pos);
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup(){
        // WHITE PIECES
        placePiece(new Rook(board, Color.WHITE), new Position(0, 7));
        placePiece(new Knight(board, Color.WHITE), new Position(1, 7));
        placePiece(new Bishop(board, Color.WHITE), new Position(2, 7));
        // placePiece(new Queen(board, Color.WHITE), new Position(3, 7));
        placePiece(new King(board, Color.WHITE), new Position(4, 7));
        placePiece(new Bishop(board, Color.WHITE), new Position(5, 7));
        placePiece(new Knight(board, Color.WHITE), new Position(6, 7));
        placePiece(new Rook(board, Color.WHITE), new Position(7, 7));

        placePiece(new Pawn(board, Color.WHITE), new Position(0,6));
        placePiece(new Pawn(board, Color.WHITE), new Position(1,6));
        placePiece(new Pawn(board, Color.WHITE), new Position(2,6));
        placePiece(new Pawn(board, Color.WHITE), new Position(3,6));
        placePiece(new Pawn(board, Color.WHITE), new Position(4,6));
        placePiece(new Pawn(board, Color.WHITE), new Position(5,6));
        placePiece(new Pawn(board, Color.WHITE), new Position(6,6));
        placePiece(new Pawn(board, Color.WHITE), new Position(7,6));

        // BLACK PIECES
        placePiece(new Rook(board, Color.BLACK), new Position(0, 0));
        placePiece(new Knight(board, Color.BLACK), new Position(1, 0));
        placePiece(new Bishop(board, Color.BLACK), new Position(2, 0));
        // placePiece(new Queen(board, Color.BLACK), new Position(3, 0));
        placePiece(new King(board, Color.BLACK), new Position(4, 0));
        placePiece(new Bishop(board, Color.BLACK), new Position(5, 0));
        placePiece(new Knight(board, Color.BLACK), new Position(6, 0));
        placePiece(new Rook(board, Color.BLACK), new Position(7, 0));

        placePiece(new Pawn(board, Color.BLACK), new Position(0,1));
        placePiece(new Pawn(board, Color.BLACK), new Position(1,1));
        placePiece(new Pawn(board, Color.BLACK), new Position(2,1));
        placePiece(new Pawn(board, Color.BLACK), new Position(3,1));
        placePiece(new Pawn(board, Color.BLACK), new Position(4,1));
        placePiece(new Pawn(board, Color.BLACK), new Position(5,1));
        placePiece(new Pawn(board, Color.BLACK), new Position(6,1));
        placePiece(new Pawn(board, Color.BLACK), new Position(7,1));
    }
}
