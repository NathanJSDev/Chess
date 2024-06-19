package com.nd.chess.views;

import java.net.URL;
import java.util.ResourceBundle;

import com.nd.chess.MainApplication;

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
            MainApplication.main.setScene(MainApplication.scenes[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }

}
