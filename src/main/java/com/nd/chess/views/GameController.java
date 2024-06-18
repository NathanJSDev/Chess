package com.nd.chess.views;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;

import com.nd.chess.MainApplication;
import com.nd.chess.UI;
import com.nd.chess.chess.ChessMatch;
import com.nd.chess.chess.ChessPiece;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * @author <a href=https://github.com/NathanSDC>NathanSDC</a>
 */
public class GameController implements Initializable {

    @FXML
    private VBox screen;

    @FXML
    private Label currentPlayer;

    @FXML
    private Label turnCount;

    @FXML
    private VBox capturedPieces_Black;

    @FXML
    private VBox capturedPieces_White;

    /**
     * When it will settle to {@code true}, the screen will be updated, and it will
     * be automatically settled to {@code false}.
     */
    public static boolean needsUpdate = false;

    public static boolean showPossibleTiles = false;

    public static List<ChessPiece> captured = new ArrayList<>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        /**
         * The main chess match on the game
         */
        ChessMatch cm = new ChessMatch();

        // This code first convert the chess board to a grid pane and after
        // adds it to the 'main frame' at the child position 0.
        screen.getChildren().add(0, UI.printBoard(cm.getPieces()));

        // It only publish the current chess match on the main class to be used by any
        // other classes like UI
        MainApplication.runningMatch = cm;

        // Sets the first data on the displays.
        turnCount.setText("" + cm.getTurn());
        currentPlayer.setText(cm.getCurrentPlayer().toString());

        // Thats the game´s main loop
        AnimationTimer at = new AnimationTimer() {

            @Override
            public void handle(long arg0) {
                try {
                    // This variable are updated on a board tile is clicked
                    if (needsUpdate) {

                        turnCount.setText("" + cm.getTurn());
                        currentPlayer.setText(cm.getCurrentPlayer().toString());

                        GridPane board;
                        // This code convert the modified chess board to a grid pane
                        if (showPossibleTiles) {
                            board = UI.printBoard(MainApplication.runningMatch.getPieces(),
                                    MainApplication.runningMatch.possibleMoves(UI.selectedOriginPiece));
                        } else
                            board = UI.printBoard(MainApplication.runningMatch.getPieces());

                        // This code set it the child position 0 on the 'main frame' (screen).
                        screen.getChildren().set(0, board);

                        // The screen has been updated, now the variables 'needsUpdate' and
                        // 'showPossibleTiles' needs to be setted to false.
                        needsUpdate = false;
                        showPossibleTiles = false; // Set to False, perhaps not necessary!

                        capturedPieces_White.getChildren().setAll(UI.printWhiteCapturedPieces(captured));
                        capturedPieces_Black.getChildren().setAll(UI.printBlackCapturedPieces(captured));
                    }
                } catch (Exception e) {
                    needsUpdate = false;
                    showPossibleTiles = false;
                    System.out.println(e.getMessage());
                }
            }
        };

        at.start();
    }

}
