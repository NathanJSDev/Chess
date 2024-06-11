package com.nd.chess.views;

import java.net.URL;
import java.util.ResourceBundle;

import com.nd.chess.MainApplication;
import com.nd.chess.UI;
import com.nd.chess.chess.ChessMatch;

import javafx.animation.AnimationTimer;
// import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class MainController implements Initializable {
    @FXML
    private VBox screen;

    public static boolean needsUpdate = false;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ChessMatch cm = new ChessMatch();

        screen.getChildren().add(0, UI.printBoard(cm.getPieces()));

        MainApplication.runningMatch = cm;

        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                try {
                    if(needsUpdate){
                        screen.getChildren().set(0, UI.printBoard(MainApplication.runningMatch.getPieces()));
                        needsUpdate = false;
    
                        System.err.println(UI.selectedOriginPiece);
                        System.err.println(UI.currentCapturedPiece);
                        System.out.println("--------------------------------------------------------");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        };

        at.start();
    }

}
