package ReversiGameProject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ReversiGameController implements Initializable {
    @FXML
    private HBox root;
    private Board board = new Board(8,8);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ReversiBoardController reversiBoard = new ReversiBoardController(board);
        reversiBoard.setPrefWidth(400);
        reversiBoard.setPrefHeight(400);
        root.getChildren().add(0, reversiBoard);
        reversiBoard.draw();
    }






}
