package ReversiGameProject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ReversiGameController implements Initializable {
    private final HashMap<Color, String> colorMap = new HashMap<Color, String>() {{
        put(Color.BLACK,"black");
        put(Color.WHITE,"white");
        put(Color.BLUE,"blue");
        put(Color.PINK,"pink");
        put(Color.RED,"red");
        put(Color.ORANGE,"orange");
        put(Color.GREEN,"green");
        put(Color.YELLOW,"yellow");
    }};
    private Board board = new Board(8,8);
    private ReversiBoardController reversiBoard;

    @FXML
    private HBox root;
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
        draw(new HumanPlayer(Board.disk.firstPlayer,reversiBoard ,Color.RED));
    }

    void draw(Player hisTurn){
        currentPlayer.setText("Current player:  " + this.colorMap.get(hisTurn.getColor()));
        firstPlayerScore.setText("First player score:  " + Integer.toString(board.numOfPlayerDisks(Board.disk.firstPlayer)));
        secondPlayerScore.setText("Second player score:  " + Integer.toString(board.numOfPlayerDisks(Board.disk.secondPlayer)));
        reversiBoard.draw(this);
    }

}
