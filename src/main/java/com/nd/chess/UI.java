package com.nd.chess;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.nd.chess.boardgame.Position;
import com.nd.chess.chess.ChessPiece;
import com.nd.chess.chess.ChessPosition;
import com.nd.chess.views.MainController;

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

    public static Position selectedOriginPiece;
    public static ChessPiece currentCapturedPiece;

    public static GridPane lastUpdatedPane;

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

                Position position = new Position(i, j);
                // System.out.println(position);

                pane.add(printPiece(pieces[i][j], style, position), i, j);
            }
        }
        lastUpdatedPane = pane;
        return pane;
    }

    private static Pane printPiece(ChessPiece piece, String style, Position position) {
        Pane r = new Pane();
        if (piece != null) {
            Button rt = new Button();
            rt.setStyle(style);
            rt.alignmentProperty().set(Pos.CENTER);
            rt.setBorder(new Border(
                    new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            rt.setPrefWidth(100);
            rt.setPrefHeight(100);
            rt.setMaxWidth(100);
            rt.setMaxHeight(100);
            rt.setOnMouseClicked(e -> {
                if (selectedOriginPiece == null || selectedOriginPiece == position) {
                    selectedOriginPiece = position;
                    rt.setStyle("-fx-background-color:#00acd7;");
                } else {
                    rt.setStyle(style);

                    try {
                        ChessPiece p = MainApplication.runningMatch.performChessMove(selectedOriginPiece, position);
                        System.out.println(p);
                    } catch (Exception error) {
                        System.out.println(error.getMessage());
                    }
                    selectedOriginPiece = null;
                    System.out.println(piece.getColor() + ", " + piece.toString() + ", " + position);
                    MainController.needsUpdate = true;
                }
            });
            
            if(piece.getImg() == null){
                rt.setText(piece.toString());
                r.getChildren().add(rt);
            }else if (piece.getImg() != null && piece.getImg().isError()) {
                r.getChildren().add(rt);
            } else {
                ImageView iv = new ImageView(piece.getImg());
                iv.setFitWidth(80);
                iv.setFitHeight(80);

                rt.setGraphic(iv);
                rt.setContentDisplay(ContentDisplay.CENTER);
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
            rt.setMaxWidth(100);
            rt.setMaxHeight(100);
            rt.setOnMouseClicked(e -> {
                if (selectedOriginPiece != null) {
                    rt.setStyle(style);
                    
                    try {
                        ChessPiece p = MainApplication.runningMatch.performChessMove(selectedOriginPiece, position);
                        System.out.println(p);
                    } catch (Exception error) {
                        System.out.println(error.getMessage());
                    }
                    selectedOriginPiece = null;
                    MainController.needsUpdate = true;
                }
            });
            r.getChildren().add(rt);
        }

        return r;
    }

    public static ChessPosition readChessPosition(Scanner sc) {
        try {
            String s = sc.nextLine();

            char column = s.charAt(0);
            int row = (int) s.charAt(1);

            return new ChessPosition(column, row);
        } catch (Exception e) {
            throw new InputMismatchException("Error reading ChessPosition: Valid values are form 'a1' to 'h8'");
        }
    }
}
