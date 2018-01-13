package ReversiGameProject;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class ReversiBoardController extends GridPane {
    private Board board;
    private Player firstPlayer;
    private Player secondPlayer;

    /**
     * constructor.
     * @param b - board game.
     */
    public ReversiBoardController(Board b, Player firstPlayer, Player secondPlayer){
        this.board = b;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
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
    public void draw() {
        this.getChildren().clear();

        int height = (int)this.getPrefHeight();
        int width = (int)this.getPrefWidth();

        int cellHeight = height / board.getRowSize();
        int cellWidth = width / board.getColSize();

        //loop go over board matrix
        for (int i = 0; i < board.getRowSize(); i++) {
            for (int j = 0; j < board.getColSize(); j++) {

                Board.disk currentDisk = board.getArray()[i][j];

                //draw cell
                this.add(new Rectangle(cellWidth, cellHeight, Color.WHITE), j, i);

                //if the cell is not empty
                if (currentDisk != Board.disk.empty){
                    if(currentDisk == Board.disk.firstPlayer){
                        this.firstPlayer.draw(cellWidth, cellHeight,i,j);
                    }
                    else{
                        this.secondPlayer.draw(cellWidth, cellHeight,i,j);
                    }
                }
            }
        }
    }
}
