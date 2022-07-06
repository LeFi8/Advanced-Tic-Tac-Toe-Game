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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private AnchorPane root;

    @FXML
    private Text redPlayer;

    @FXML
    private Text bluePlayer;

    private int playersTurn;

    private final List<Button> allFields = new ArrayList<>();

    private final List<Button> redPlayerButtons = new ArrayList<>();

    private final List<Button> bluePlayerButtons = new ArrayList<>();

    @FXML
    void initialize() {
        allFields.addAll(Arrays.asList(
                topLeftButton, topMiddleButton, topRightButton,
                middleLeftButton, middleCenterButton, middleRightButton,
                bottomLeftButton, bottomMiddleButton, bottomRightButton
        ));

        redPlayerButtons.addAll(Arrays.asList(r1, r2, r3, r4, r5, r6));
        bluePlayerButtons.addAll(Arrays.asList(b1, b2, b3, b4, b5, b6));

        changeButtonsState(redPlayerButtons, false);
        changeButtonsState(bluePlayerButtons, false);
    }

    @FXML
    void onStart() {
        playersTurn = (int) Math.round(Math.random());
        nextTurn();
    }

    @FXML
    void onReset() throws IOException {
        Scene scene = root.getScene();
        scene.setRoot(
                FXMLLoader.load(
                        Objects.requireNonNull(
                                GameApp.class.getResource("tic-tac-toe.fxml")
                        )
                )
        );
    }

    @FXML
    void onClick(ActionEvent event) {
        Button currentValue = (Button)event.getSource();
        if (currentValue.getTextFill().equals(Color.GRAY))
            return;

        chooseField(currentValue, topLeftButton);
        chooseField(currentValue, topMiddleButton);
        chooseField(currentValue, topRightButton);
        chooseField(currentValue, middleLeftButton);
        chooseField(currentValue, middleCenterButton);
        chooseField(currentValue, middleRightButton);
        chooseField(currentValue, bottomLeftButton);
        chooseField(currentValue, bottomMiddleButton);
        chooseField(currentValue, bottomRightButton);
    }

    //TODO: other winning patterns
    private boolean checkForWinner(){
        if (!topLeftButton.getText().isEmpty() && !topMiddleButton.getText().isEmpty() && !topRightButton.getText().isEmpty()) {
            if (topLeftButton.getTextFill().equals(topMiddleButton.getTextFill())
                    && topMiddleButton.getTextFill().equals(topRightButton.getTextFill())) {
                centerText.setText("Winner!");
                centerText.setFill(topLeftButton.getTextFill());
                return true;
            }
        }
        return false;
    }

    private void chooseField(Button value, Button field){
        field.setOnAction(e -> {
            if (!field.getText().isEmpty())
                if (Integer.parseInt(value.getText()) <= Integer.parseInt(field.getText()))
                    return;

            if (value.getTextFill().equals(Color.GRAY))
                return;

            field.setText(value.getText());
            field.setTextFill(value.getTextFill());
            field.setFont(new Font(30));
            value.setTextFill(Color.GRAY);
            nextTurn();
        });
    }

    private void nextTurn(){
        if (checkForWinner()) return;

        playersTurn++;
        if (playersTurn % 2 == 0) {
            changeButtonsState(redPlayerButtons, false);
            changeButtonsState(bluePlayerButtons, true);
            centerText.setText("Blue player's turn");
            centerText.setFill(bluePlayer.getFill());
        } else {
            changeButtonsState(bluePlayerButtons, false);
            changeButtonsState(redPlayerButtons, true);
            centerText.setText("Red player's turn");
            centerText.setFill(redPlayer.getFill());
        }
    }

    private void changeButtonsState(List<Button> player, boolean state){
        for (Button button : player)
            button.setDisable(!state);
    }

}
