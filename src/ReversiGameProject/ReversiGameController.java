package ReversiGameProject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ReversiGameController implements Initializable {
    private Board board = new Board(8,8);
    ReversiBoardController reversiBoard;
    @FXML
    private HBox root;
    @FXML
    private Text firstPlayerScoreString;
    @FXML
    private Text secondPlayerScoreString;
    @FXML
    private Text currentPlayer;
    @FXML
    private Text firstPlayerScore;
    @FXML
    private Text secondPlayerScore;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reversiBoard = new ReversiBoardController(board);
        reversiBoard.setPrefWidth(400);
        reversiBoard.setPrefHeight(400);
        root.getChildren().add(0, reversiBoard);
        firstPlayerScoreString.setText("first player score:");
        secondPlayerScoreString.setText("second player score:");
        draw();
    }

    void draw(){
        currentPlayer.setText("first");
        firstPlayerScore.setText(Integer.toString(board.numOfPlayerDisks(Board.disk.firstPlayer)));
        secondPlayerScore.setText(Integer.toString(board.numOfPlayerDisks(Board.disk.firstPlayer)));
        reversiBoard.draw();
    }

}
