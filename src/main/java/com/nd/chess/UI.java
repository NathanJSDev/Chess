package com.nd.chess;

import com.nd.chess.chess.ChessPiece;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public final class UI {
    public static GridPane printBoard(ChessPiece[][] pieces) {
        GridPane pane = new GridPane();
        // pane.setGridLinesVisible(true);
        pane.setStyle("-fx-background-color:#acd700;");
        String style = "#00acd7";

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (j % 2 == 0) {
                    style = "-fx-background-color:#ffd599;";
                    if (i % 2 == 0) {
                        style = "-fx-background-color:#b16e41;";
                    }
                } else {
                    style = "-fx-background-color:#b16e41;";
                    if (i % 2 == 0) {
                        style = "-fx-background-color:#ffd599;";
                    }
                }

                if (pieces[i][j] != null && pieces[i][j].getColor() == com.nd.chess.chess.Color.WHITE) {
                    style += "-fx-text-fill:#fff;";
                } else if (pieces[i][j] != null && pieces[i][j].getColor() == com.nd.chess.chess.Color.WHITE) {
                    style += "-fx-text-fill:#000;";
                }

                pane.add(printPiece(pieces[i][j], style), i, j);
            }
        }
        return pane;
    }

    private static Pane printPiece(ChessPiece piece, String style) {
        Pane r = new Pane();
        if (piece != null) {
            Button rt = new Button();
            rt.setStyle(style);
            rt.alignmentProperty().set(Pos.CENTER);
            rt.setBorder(new Border(
                    new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            rt.setPrefWidth(100);
            rt.setPrefHeight(100);
            rt.setOnMouseClicked(e -> {
                System.out.println("im clicked");
            });

            if (piece.getImg() != null && piece.getImg().isError()) {
                r.getChildren().add(rt);
            } else {
                ImageView iv = new ImageView(piece.getImg());
                iv.setFitWidth(100);
                iv.setFitHeight(100);

                rt.setGraphic(iv);
                rt.setContentDisplay(ContentDisplay.LEFT);
                r.getChildren().add(rt);
            }
        } else {
            Button rt = new Button();
            rt.setStyle(style);
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
