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

    public static Stage main;

    // background tile colors
    public static String bg0 = "#ebeed3"; // recomended "#ffd599", "#ebeed3", "#ffffff", "#ffffff"
    public static String bg1 = "#739654"; // recomended "#b16e41", "#739654", "#787878", "#c76f0c"
    // background tile color : on selected
    public static String bgs = "#75b2e9"; // recomended "#75b2e9", "#75b2e9", "#00acd7", "#00acd7"
    // background tile color : on can move to free tile
    public static String bgma = "#f6f76d"; // recomended "#00acd7", "#00acd7", "#24a540", "#24a540"
    // background tile color : on can move to oponent or the king is checked
    public static String bgma_ = "#e53d3e"; // recomended "#de0033", "#de0033", "#de0033", "#de0033"
    // on tile text colors
    public static String c0 = "#ffffff"; // recomended "#ffffff"
    public static String c1 = "#000000"; // recomended "#000000"

    public Scene getScene(String name) throws IOException{
        return new Scene(FXMLLoader.load(MainApplication.class.getResource(name)));
    }

    @Override
    public void start(Stage stage) throws IOException {
        scenes = new Scene[2];
        scenes[0] = getScene("views/Menu.fxml");
        scenes[1] = getScene("views/Main.fxml");

        stage.setTitle("Chess");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/32.png")));
        stage.setScene(scenes[0]);
        stage.show();

        main = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}