package Display;
import RevrsiGame.Board;
import RevrsiGame.HumanPlayer;
import RevrsiGame.Player;
import RevrsiGame.ReadDefinitionFile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import static java.lang.System.exit;

/**
 * Controller of Reversi game.
 */
public class ReversiGameController implements Initializable {

    private Board board;
    private ReversiBoardController reversiBoard;
    private Map<String,String> settings;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ReadDefinitionFile file = new ReadDefinitionFile();
        this.settings = file.readFile("DefinitionFile.txt");
        if (settings.isEmpty()) {
            settings.put("firstPlayerColor", "Black");
            settings.put("secondPlayerColor","White");
            settings.put("size","8");
        }

        int boardSize = Integer.parseInt(settings.get("size"));
        Color firstColor = Color.web(settings.get("firstPlayerColor"));
        Color secondColor = Color.web(settings.get("secondPlayerColor"));
        this.board= new Board(boardSize,boardSize);
        reversiBoard = new ReversiBoardController(board, firstColor, secondColor);
        reversiBoard.setPrefWidth(400);
        reversiBoard.setPrefHeight(400);
        reversiBoard.setTranslateX(5);
        reversiBoard.setTranslateY(5);
        Hbox.getChildren().add(0, reversiBoard);
        draw(new HumanPlayer(Board.disk.firstPlayer, reversiBoard , firstColor));
    }

    /**
     * The function draws the text with information about the current state
     * of the game and calls the function that draws the board
     * @param hisTurn - The player that it is his turn.
     */
    public void draw(Player hisTurn){
        currentPlayer.setText("Current player:  " + this.colorMap.get(hisTurn.getColor()));
        firstPlayerScore.setText(settings.get("firstPlayerColor") + " player score:  " +
                Integer.toString(board.numOfPlayerDisks(Board.disk.firstPlayer)));
        secondPlayerScore.setText(settings.get("secondPlayerColor") + " player score:  " +
                Integer.toString(board.numOfPlayerDisks(Board.disk.secondPlayer)));
        reversiBoard.draw(this);
    }

    /**
     * When the game is over, a message is displayed with
     * the name of the winning player (or draw).
     * @param diskPlayer - disk of the winner player.
     */
    public void gameOver(Board.disk diskPlayer){
        String message = "The winner is the ";

        switch (diskPlayer){
            case firstPlayer:
                String firstPlayerColor  = settings.get("firstPlayerColor").toLowerCase();
                message =  message + firstPlayerColor + " player!";
                break;
            case secondPlayer:
                String secondPlayerColor  = settings.get("secondPlayerColor").toLowerCase();
                message = message + secondPlayerColor + " player!";
                break;
            case noPlayer:
                message = "It's a draw!";
                break;
        }

        //create alert and show it.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game over");
        alert.setHeaderText(message);
        alert.show();
    }

    /**
     * This function opens settings window.
     * @param event - ActionEvent object.
     */
    @FXML
    private void openSettings(ActionEvent event) {
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

    /**
     * This function closes the game.
     * @param event - ActionEvent object.
     */
    @FXML
    private void exitGame(ActionEvent event) {
        exit(0);
    }
}
