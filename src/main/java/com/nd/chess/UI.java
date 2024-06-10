package com.nd.chess;

import com.nd.chess.chess.ChessPiece;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class UI {
    public static GridPane printBoard(ChessPiece[][] pieces) {
        GridPane pane = new GridPane();
        pane.setGridLinesVisible(true);
        pane.setStyle("-fx-background-color:#acd700;");
        String background_color = "#00acd7";

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (j % 2 == 0) {
                    background_color = "#ffd599";
                    if (i % 2 == 0) {
                        background_color = "#b16e41";
                    }
                } else {
                    background_color = "#b16e41";
                    if (i % 2 == 0) {
                        background_color = "#ffd599";
                    }
                }
                pane.add(printPiece(pieces[i][j], background_color), i, j);
            }
        }
        return pane;
    }

    private static Pane printPiece(ChessPiece piece, String background_color) {
        Pane r = new Pane();
        background_color = "-fx-background-color:" + background_color + ";";
        if (piece != null) {
            if (piece.getImg() == null) {
                Label rt = new Label(piece.toString());
                rt.setStyle("-fx-text-fill:#fff;" + background_color);
                rt.alignmentProperty().set(Pos.CENTER);
                rt.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                        BorderWidths.DEFAULT)));
                rt.setPrefWidth(100);
                rt.setPrefHeight(100);
                r.getChildren().add(rt);
            } else {
                // ImageView iv = new ImageView(piece.getImg());
                // iv.setFitWidth(100);
                // iv.setFitHeight(100);
                // r.getChildren().add(iv);
            }
        } else {
            Button rt = new Button();
            rt.setStyle("-fx-text-fill:#fff;" + background_color);
            rt.alignmentProperty().set(Pos.CENTER);
            rt.setBorder(new Border(
                    new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            rt.setPrefWidth(100);
            rt.setPrefHeight(100);
            rt.setOnMouseClicked(e->{System.out.println("im clicked");});
            r.getChildren().add(rt);
        }

        return r;
    }
}
