package com.nd.chess.views;

import java.net.URL;
import java.util.ResourceBundle;

import com.nd.chess.UI;
import com.nd.chess.chess.ChessMatch;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class MainController implements Initializable{

    @FXML
    private VBox screen;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ChessMatch cm = new ChessMatch();
        screen.getChildren().add(UI.printBoard(cm.getPieces()));
    }

}
