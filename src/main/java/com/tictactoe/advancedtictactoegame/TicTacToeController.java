package com.tictactoe.advancedtictactoegame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private int numberOfTurns = 0;

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
    void displayRules(){
        Alert rules = new Alert(Alert.AlertType.INFORMATION);
        rules.setTitle("Rules");
        rules.setHeaderText("About the game");

        String gameDescription = "Welcome to different version of the Tic Tac Toe \n" +
                "game. The goal of the game is to match three of \n" +
                "your colored pieces before your opponent does.\n" +
                "\n" +
                "The first player is determined randomly. \n" +
                "The starting player may place any piece of any size\n" +
                "from their characters to any spot on the board.\n" +
                "\n" +
                "From here players will take turns placing their \n" +
                "characters onto the board. Bigger pieces can \n" +
                "always cover smaller pieces that means you can \n" +
                "place larger pieces over yours or your \n" +
                "opponent’s smaller ones. \n" +
                "\n" +
                "The game ends when a player \n" +
                "manages to get 3 of their colored pieces in a row. \n" +
                "The player to first complete this goal is the winner.";

        rules.setContentText(gameDescription);
        rules.show();
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
            gameFinished("Winner!");
            centerText.setFill(allFields.get(x).getTextFill());
            return 1;
        }

        return 0;
    }

    private void gameFinished(String text){
        centerText.setText(text);
        changeButtonsState(redPlayerButtons, false);
        changeButtonsState(bluePlayerButtons, false);
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
        if (checkForWinner())
            return;

        if (numberOfTurns++ == 12) {
            centerText.setFill(Color.WHITE);
            gameFinished("Draw!");
            return;
        }

        if (++playersTurn % 2 == 0) {
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
