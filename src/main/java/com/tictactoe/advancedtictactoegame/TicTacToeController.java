package com.tictactoe.advancedtictactoegame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class TicTacToeController {

    @FXML
    private Text centerText;

    @FXML
    private Button topLeftButton;

    @FXML
    private Button topMiddleButton;

    @FXML
    private Button topRightButton;

    @FXML
    private Button middleCenterButton;

    @FXML
    private Button middleLeftButton;

    @FXML
    private Button middleRightButton;

    @FXML
    private Button bottomLeftButton;

    @FXML
    private Button bottomMiddleButton;

    @FXML
    private Button bottomRightButton;

    @FXML
    private Button r1;

    @FXML
    private Button r2;

    @FXML
    private Button r3;

    @FXML
    private Button r4;

    @FXML
    private Button r5;

    @FXML
    private Button r6;

    @FXML
    private Button b1;

    @FXML
    private Button b2;

    @FXML
    private Button b3;

    @FXML
    private Button b4;

    @FXML
    private Button b5;

    @FXML
    private Button b6;

    @FXML
    private Button resetButton;

    @FXML
    private Button startButton;

    @FXML
    private AnchorPane root;

    private int playersTurn;

    @FXML
    void onStart(ActionEvent event) {
        System.out.println("Start");
        playersTurn = (int) Math.round(Math.random());
        centerText.setText(nextTurn());
    }

    @FXML
    void onReset() throws IOException {
        Scene scene = root.getScene();
        scene.setRoot(
                FXMLLoader.load(
                        Objects.requireNonNull(
                                GameApp.class.getResource("tic-tac-toe.fxml")
                        )
                ));
    }

    void clearButtons(Button button){
        button.setText("");
    }

    @FXML
    void onClick(ActionEvent event) {
        Button current = (Button)event.getSource();
        if (current.getTextFill().equals(Color.GRAY))
            return;

        topLeftButton.setOnAction(e -> {
            topLeftButton.setText(current.getText());
            topLeftButton.setTextFill(current.getTextFill());
            topLeftButton.setFont(new Font(30));
            current.setTextFill(Color.GRAY);
        });

        System.out.println("Clicked button " + current.getId());
    }

    private String nextTurn(){
        if (playersTurn % 2 == 0)
            return "Blue player's turn";
        else return "Red player's turn";
    }

}
