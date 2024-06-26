package com.nd.chess.views;

import java.net.URL;
import java.util.ResourceBundle;

import com.nd.chess.MainApplication;
import com.nd.chess.UI;

import javafx.event.ActionEvent;
import javafx.fxml.*;

public class MenuController implements Initializable{

    @FXML
    void credits(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void open_game(ActionEvent event) {

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
        
    }

}
