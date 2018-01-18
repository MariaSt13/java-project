package Display;

import RevrsiGame.Board;
import RevrsiGame.GameLogic;
import RevrsiGame.HumanPlayer;
import RevrsiGame.Player;
import RevrsiGame.Point;
import RevrsiGame.ReversiGame;
import RevrsiGame.StandardGameLogic;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.IOException;

/**
 * Controller of Reversi game board.
 */
public class ReversiBoardController extends GridPane {
    private Board board;
    private Player firstPlayer;
    private Player secondPlayer;
    private ReversiGame game;

    /**
     * constructor.
     * @param b - board of the game.
     * @param firstColor - color of the first player.
     * @param secondColor - color of the second player.
     */
    public ReversiBoardController(Board b, Color firstColor, Color secondColor){
        //create board and players.
        this.board = b;
        this.firstPlayer = new HumanPlayer(Board.disk.firstPlayer, this, firstColor);
        this.secondPlayer = new HumanPlayer(Board.disk.secondPlayer, this, secondColor);

        //create game logic.
        GameLogic logic = new StandardGameLogic();

        //create a new game.
        game = new ReversiGame(this.board, this.firstPlayer, this.secondPlayer, logic);

        //load fxml.
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("ReversiBoard.fxml"));

        /*
         * The Reversi Board Controller sets itself as both
         * the root and the controller of the FXML document.
         */
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        //load fxmlLoader
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    /**
     * Draw board.
     * @param gameController - Controller of the game.
     */
    public void draw(ReversiGameController gameController) {
        this.getChildren().clear();
        this.setStyle("-fx-border-color: Black;-fx-background-color: black");
        double height = this.getPrefHeight();
        double width = this.getPrefWidth();

        //calculate height and width.
        double cellHeight = height /(double) (board.getRowSize() -1);
        double cellWidth = width / (double)(board.getColSize() -1);

        //loop go over board matrix
        for (int i = 1; i < board.getRowSize(); i++) {
            for (int j = 1; j < board.getColSize(); j++) {

                Board.disk currentDisk = board.getArray()[i][j];

                //draw cell
                Rectangle rec  = new Rectangle(cellWidth, cellHeight, Color.LIGHTGREY);
                //GridPane.setHalignment(rec,HPos.CENTER);
                this.add(rec, j-1, i-1);

                //if the cell is "noPlayer"
                if (currentDisk == Board.disk.noPlayer)
                    rec.setOnMouseClicked(event -> {
                        int y = GridPane.getColumnIndex(rec) + 1;
                        int x = GridPane.getRowIndex(rec) + 1;
                        game.playOneTurn(new Point(x,y),gameController);
                    });

                    //if the cell is not "noPlayer"
                else {
                    if(currentDisk == Board.disk.firstPlayer){
                        this.firstPlayer.draw(cellWidth-2,
                                cellHeight-2,j-1,i-1);
                    }
                    else if(currentDisk == Board.disk.secondPlayer){
                        this.secondPlayer.draw(cellWidth-2,
                                cellHeight-2,j-1,i-1);
                    }
                }
            }
        }
    }
}