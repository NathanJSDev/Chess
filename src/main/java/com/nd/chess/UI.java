package com.nd.chess;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.nd.chess.boardgame.Position;
import com.nd.chess.chess.ChessPiece;
import com.nd.chess.chess.pieces.King;
import com.nd.chess.views.GameController;

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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public final class UI {

    public static Position selectedOriginPiece;
    public static ChessPiece currentCapturedPiece;

    public static GridPane printBoard(ChessPiece[][] pieces) {
        GridPane pane = new GridPane();
        String style = "";

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if(pieces[i][j]!=null && ((pieces[i][j].getColor()==MainApplication.runningMatch.getCurrentPlayer()) && pieces[i][j] instanceof King) && MainApplication.runningMatch.isCheck()){
                    style = setStyle(MainApplication.bgma_, MainApplication.c0);
                }else if(pieces[i][j]!=null){
                    style = setStyle(i, j, MainApplication.bg0, MainApplication.bg1, MainApplication.c0, MainApplication.c1, pieces[i][j].getColor());
                }else style = setStyle(i, j, MainApplication.bg0, MainApplication.bg1, MainApplication.c0, MainApplication.c1, com.nd.chess.chess.Color.BLACK);
                Position position = new Position(i, j);

                pane.add(printPiece(pieces[i][j], style, position), i, j);
            }
        }
        return pane;
    }

    public static GridPane printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        GridPane pane = new GridPane();

        String style = "";

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                Position position = new Position(i, j);

                if(pieces[i][j]!=null && (pieces[i][j].getColor()==MainApplication.runningMatch.getCurrentPlayer()) && MainApplication.runningMatch.isCheck()){
                    style = setStyle(MainApplication.bgma_, MainApplication.c0);
                }else if (pieces[i][j] != null && !possibleMoves[i][j]) {
                    if (position == selectedOriginPiece) {
                        style = setStyle(MainApplication.bgs, MainApplication.c0);
                    } else{
                        style = setStyle(i, j, MainApplication.bg0, MainApplication.bg1, MainApplication.c0,MainApplication.c1, pieces[i][j].getColor());
                    }
                } else if (pieces[i][j] == null && !possibleMoves[i][j]) {
                    style = setStyle(i, j, MainApplication.bg0, MainApplication.bg1, MainApplication.c0, MainApplication.c1, com.nd.chess.chess.Color.BLACK);
                } else if (pieces[i][j] != null && possibleMoves[i][j]) {
                    style = setStyle(MainApplication.bgma_, MainApplication.c0);
                } else {
                    style = setStyle(MainApplication.bgma, MainApplication.c0);
                }

                pane.add(printPiece(pieces[i][j], style, position), i, j);
            }
        }
        return pane;
    }

    private static String setStyle(String bgma, String c0) {
        return "-fx-background-color:" + bgma + ";-fx-text-fill:" + c0 + ";";
    }

    private static String setStyle(int i, int j, String bg0, String bg1, String c0, String c1, com.nd.chess.chess.Color c){
        String style = "";
        if (j % 2 == 0) {
            style = "-fx-background-color:"+bg0+";";
            if (i % 2 == 0) {
                style = "-fx-background-color:"+bg1+";";
            }
        } else {
            style = "-fx-background-color:"+bg1+";";
            if (i % 2 == 0) {
                style = "-fx-background-color:"+bg0+";";
            }
        }

        if (c == com.nd.chess.chess.Color.WHITE) {
            style += "-fx-text-fill:"+c0+";";
        } else if (c == com.nd.chess.chess.Color.WHITE) {
            style += "-fx-text-fill:"+c1+";";
        }

        return style;
    }

    private static Pane printPiece(ChessPiece piece, String style, Position position) {
        Pane r = new Pane();
        if (piece != null) {
            Button rt = new Button();
            rt.setStyle(style);
            rt.alignmentProperty().set(Pos.CENTER);
            rt.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            rt.setPrefWidth(100);
            rt.setPrefHeight(100);
            rt.setMaxWidth(100);
            rt.setMaxHeight(100);
            rt.setOnMouseClicked(e -> {
                if (selectedOriginPiece == null || selectedOriginPiece == position) {
                    selectedOriginPiece = position;
                    GameController.showPossibleTiles = true;
                    GameController.needsUpdate = true;
                } else {
                    rt.setStyle(style);
                    try {
                        currentCapturedPiece = MainApplication.runningMatch.performChessMove(selectedOriginPiece,position);
                        if (currentCapturedPiece != null)
                            GameController.captured.add(currentCapturedPiece);
                    } catch (Exception error) {
                        System.out.println(error.getMessage());
                    }finally{
                        selectedOriginPiece = null;
                        GameController.needsUpdate = true;
                    }
                }
            });

            rt.setOnMouseEntered(e -> {
                if ((selectedOriginPiece == null || selectedOriginPiece == position)&& MainApplication.runningMatch.isYourTurn(position)) {
                    rt.setStyle("-fx-background-color:" + MainApplication.bgs + ";");
                }
            });
            rt.setOnMouseExited(e -> {
                if ((selectedOriginPiece == null || selectedOriginPiece == position)&& MainApplication.runningMatch.isYourTurn(position)) {
                    rt.setStyle(style);
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
            rt.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            rt.setPrefWidth(100);
            rt.setPrefHeight(100);
            rt.setMaxWidth(100);
            rt.setMaxHeight(100);
            rt.setOnMouseClicked(e -> {
                if (selectedOriginPiece != null) {
                    rt.setStyle(style);
                    try {
                        currentCapturedPiece = MainApplication.runningMatch.performChessMove(selectedOriginPiece,position);
                        if (currentCapturedPiece != null)
                            GameController.captured.add(currentCapturedPiece);
                    } catch (Exception error) {
                        System.out.println(error.getMessage());
                        GameController.needsUpdate = true;
                    }finally{
                        selectedOriginPiece = null;
                        GameController.needsUpdate = true;
                    }
                }
            });
            r.getChildren().add(rt);
        }

        return r;
    }

    private static List<ChessPiece>[] filterCapturedPieces(List<ChessPiece> pieces) {
        @SuppressWarnings("unchecked")
        List<ChessPiece>[] capturedPieces = new ArrayList[2];
        capturedPieces[0] = pieces.stream().filter(el -> el.getColor() == com.nd.chess.chess.Color.WHITE).collect(Collectors.toList());
        capturedPieces[1] = pieces.stream().filter(el -> el.getColor() == com.nd.chess.chess.Color.BLACK).collect(Collectors.toList());
        return capturedPieces;
    }

    public static List<VBox> printWhiteCapturedPieces(List<ChessPiece> captured) {
        List<VBox> pieceImages = new ArrayList<>();
        captured = filterCapturedPieces(captured)[0];
        for (int i = 0; i < captured.size(); i++) {
            ImageView iv = new ImageView(captured.get(i).getImg());
            iv.setFitWidth(30);
            iv.setFitHeight(30);
            VBox vb = new VBox(iv);
            vb.setPrefWidth(100);
            vb.setPrefHeight(20);
            pieceImages.add(vb);
        }
        return pieceImages;
    }

    public static List<VBox> printBlackCapturedPieces(List<ChessPiece> captured) {
        List<VBox> pieceImages = new ArrayList<>();
        captured = filterCapturedPieces(captured)[1];
        for (int i = 0; i < captured.size(); i++) {
            ImageView iv = new ImageView(captured.get(i).getImg());
            iv.setFitWidth(30);
            iv.setFitHeight(30);
            VBox vb = new VBox(iv);
            vb.setPrefWidth(100);
            vb.setPrefHeight(20);
            pieceImages.add(vb);
        }
        return pieceImages;
    }
}
