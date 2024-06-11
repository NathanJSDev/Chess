package com.nd.chess.views;

import java.net.URL;
import java.util.ResourceBundle;

import com.nd.chess.MainApplication;
import com.nd.chess.UI;
import com.nd.chess.chess.ChessMatch;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class MainController implements Initializable {
    @FXML
    private VBox screen;

    /**
     * If it is setted to {@code true} the screen will be updated and it will be automatically setted to {@code false}
    */
    public static boolean needsUpdate = false;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        /**
         * The main chess match on the game
        */
        ChessMatch cm = new ChessMatch();

        // This code first convert the chess board to a grid pane and after
        // adds it to the 'main frame' at the child position 0.
        screen.getChildren().add(0, UI.printBoard(cm.getPieces()));

        // It only publish the current chess match on the main class to be used by any other classes like UI
        MainApplication.runningMatch = cm;

        // Thats the game´s main loop
        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                // This variable are updated on a board tile is clicked
                if(needsUpdate){
                    // This code convert the modified chess board to a grid pane and set it on
                    // the child position 0 on the 'main frame' (screen).
                    screen.getChildren().set(0, UI.printBoard(MainApplication.runningMatch.getPieces()));

                    // The screen has been updated, now the 'needs update' needs to be setted to false.
                    needsUpdate = false;
                }
            }
        };

        at.start();
    }

}
