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

        for (Button button : allFields)
            chooseField(currentValue, button);
    }

    private boolean checkForWinner(){
        int result = 0;
        result += checkFields(0, 1, 2);
        result += checkFields(3, 4, 5);
        result += checkFields(6, 7, 8);

        result += checkFields(0, 3, 5);
        result += checkFields(1, 4, 7);
        result += checkFields(2, 6, 8);

        result += checkFields(0, 4, 8);
        result += checkFields(2, 4, 6);

        return result > 0;
    }

    private int checkFields(int x, int y, int z) {
        if (allFields.get(x).getText().isEmpty()
                || allFields.get(y).getText().isEmpty()
                || allFields.get(z).getText().isEmpty())
            return 0;

        if (allFields.get(x).getTextFill().equals(allFields.get(y).getTextFill())
                && allFields.get(x).getTextFill().equals(allFields.get(z).getTextFill())) {
            centerText.setText("Winner!");
            centerText.setFill(topLeftButton.getTextFill());
            return 1;
        }

        return 0;

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
