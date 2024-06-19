package com.nd.chess.views;

import java.net.URL;
import java.util.ResourceBundle;

import com.nd.chess.MainApplication;
import com.nd.chess.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class WinnerController implements Initializable{

    @FXML
    private Label winner;

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void menu(ActionEvent event) {
        try {
            MainApplication.main.setScene(MainApplication.getScene("views/Menu.fxml"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void play(ActionEvent event) {
        try {
            MainApplication.runningMatch = null;
            UI.currentCapturedPiece = null;
            UI.selectedOriginPiece = null;

            MainApplication.main.setScene(MainApplication.getScene("views/Main.fxml"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String str = MainApplication.runningMatch.getCurrentPlayer().toString().toLowerCase();
        str = str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
        winner.setText(str);
    }

}
