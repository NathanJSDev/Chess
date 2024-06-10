package com.nd.chess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    public static Scene[] scenes;

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