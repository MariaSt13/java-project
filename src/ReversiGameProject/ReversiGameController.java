package ReversiGameProject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ReversiGameController implements Initializable {
    private final HashMap<Color, String> colorMap = new HashMap<Color, String>() {{
        put(Color.BLACK,"Black");
        put(Color.WHITE,"White");
        put(Color.BLUE,"Blue");
        put(Color.PINK,"Pink");
        put(Color.RED,"Red");
        put(Color.ORANGE,"Orange");
        put(Color.GREEN,"Green");
        put(Color.YELLOW,"Yellow");
    }};
    private Board board;
    private ReversiBoardController reversiBoard;

    @FXML
    private VBox root;

    @FXML
    private HBox Hbox;
    @FXML
    private Text currentPlayer;
    @FXML
    private Text firstPlayerScore;
    @FXML
    private Text secondPlayerScore;

    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem settings;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ReadDefinitionFile file = new ReadDefinitionFile();
        Map<String,String> map = file.readFile("DefinitionFile.txt");
        Color firstColor;
        Color secondColor;
        int boardSize;
        if (map.isEmpty()) {
            firstColor = Color.BLACK;
            secondColor = Color.WHITE;
            boardSize = 8;
        }
        else {
            boardSize = Integer.parseInt(map.get("size"));
            firstColor = Color.web(map.get("color1"));
            secondColor = Color.web(map.get("color2"));
        }
        this.board= new Board(boardSize,boardSize);
        reversiBoard = new ReversiBoardController(board, firstColor, secondColor);
        reversiBoard.setPrefWidth(400);
        reversiBoard.setPrefHeight(400);
        reversiBoard.setTranslateX(5);
        reversiBoard.setTranslateY(5);
        Hbox.getChildren().add(0, reversiBoard);
        draw(new HumanPlayer(Board.disk.firstPlayer, reversiBoard , firstColor));
    }

    void draw(Player hisTurn){
        currentPlayer.setText("Current player:  " + this.colorMap.get(hisTurn.getColor()));
        firstPlayerScore.setText("First player score:  " +
                Integer.toString(board.numOfPlayerDisks(Board.disk.firstPlayer)));
        secondPlayerScore.setText("Second player score:  " +
                Integer.toString(board.numOfPlayerDisks(Board.disk.secondPlayer)));
        reversiBoard.draw(this);
    }

    @FXML
    void openSettings(ActionEvent event) {
        try {
            FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("Definition.fxml"));
            Parent parent = settingsLoader.load();
            Scene scene = new Scene(parent, 400, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Settings");
            stage.show();
        } catch (IOException e) {
            System.out.println("Error in opening settings");
            e.printStackTrace();
        }
    }
}
