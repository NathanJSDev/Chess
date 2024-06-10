package com.nd.chess.views;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable{

    @FXML
    private BorderPane screen;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Main");
    }

}
