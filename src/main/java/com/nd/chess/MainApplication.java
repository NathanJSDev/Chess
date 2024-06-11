package com.nd.chess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import com.nd.chess.chess.ChessMatch;

public class MainApplication extends Application {

    public static Scene[] scenes;

    public static ChessMatch runningMatch;

    // background tile colors
    public static String bg0 = "#ffffff"; // recomended "#ffd599", "#ffffff", "#ffffff", "#ffffff", "#ffffff"
    public static String bg1 = "#c7670c"; // recomended "#b16e41", "#24a540", "#cea863", "#787878", "#c76f0c"
    // background tile color : on selected
    public static String bgs = "#75b2e9"; // recomended "#00acd7", "#de0033", "#00acd7", "#00acd7", "#00acd7"
    // on tile text colors
    public static String c0 = "#ffffff"; // recomended "#ffffff"
    public static String c1 = "#000000"; // recomended "#000000"

    public Scene getScene(String name) throws IOException{
        return new Scene(FXMLLoader.load(MainApplication.class.getResource(name)));
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Chess");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/logo.jpg")));
        stage.setScene(getScene("views/Main.fxml"));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}