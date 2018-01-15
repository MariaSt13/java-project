package ReversiGameProject;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.event.MouseEvent;
import java.io.IOException;

public class ReversiBoardController extends GridPane {
    private Board board;
    private Player firstPlayer;
    private Player secondPlayer;
    private ReversiGame game;

    /**
     * constructor.
     * @param b - board game.
     */
    public ReversiBoardController(Board b, Color firstColor, Color secondColor){
        this.board = b;
        this.firstPlayer = new HumanPlayer(Board.disk.firstPlayer, this, firstColor);
        this.secondPlayer = new HumanPlayer(Board.disk.secondPlayer, this, secondColor);
        GameLogic logic = new StandardGameLogic();
        UserInterface display = new GuiUserInterface(this);
        game = new ReversiGame(this.board, this.firstPlayer, this.secondPlayer,
                logic, display);
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("ReversiBoard.fxml"));

        //The ReversiBoardController sets itself as both the root and the controller of the FXML document.
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
     * draw board,
     */
    public void draw(ReversiGameController gameController) {
        this.getChildren().clear();

        int height = (int)this.getPrefHeight();
        int width = (int)this.getPrefWidth();

        int cellHeight = height / (board.getRowSize() -1);
        int cellWidth = width / (board.getColSize() -1);

        //loop go over board matrix
        for (int i = 1; i < board.getRowSize(); i++) {
            for (int j = 1; j < board.getColSize(); j++) {

                Board.disk currentDisk = board.getArray()[i][j];

                //draw cell
                this.add(new Rectangle(cellWidth+1, cellHeight+1, Color.BLACK), j-1,i-1);
                Rectangle rec  = new Rectangle(cellWidth, cellHeight, Color.LIGHTGREY);
                this.add(rec, j-1, i-1);

                //if the cell is empty
                if (currentDisk == Board.disk.empty)
                    rec.setOnMouseClicked(event -> {
                        int y = GridPane.getColumnIndex(rec) + 1;
                        int x = GridPane.getRowIndex(rec) + 1;
                        game.playOneTurn(new Point(x,y),gameController);
                    });
                //if the cell is not empty
                else {
                    if(currentDisk == Board.disk.firstPlayer){
                        this.firstPlayer.draw(cellWidth, cellHeight,j-1,i-1);
                    }
                    else if(currentDisk == Board.disk.secondPlayer){
                        this.secondPlayer.draw(cellWidth, cellHeight,j-1,i-1);
                    }
                }
            }
        }
    }
}
