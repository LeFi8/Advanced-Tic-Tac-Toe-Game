module com.tictactoe.advancedtictactoegame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tictactoe.advancedtictactoegame to javafx.fxml;
    exports com.tictactoe.advancedtictactoegame;
}