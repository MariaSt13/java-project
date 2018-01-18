package RevrsiGame;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This class extends player class and represent
 * a human player who plays on this computer.
 */
public class HumanPlayer extends Player {
    private GridPane grid;

    /**
     * constructor.
     * @param d - disk of the player.
     * @param grid - GridPane object.
     * @param diskColor - color of the disk.
     */
    public HumanPlayer(Board.disk d, GridPane grid, Color diskColor) {
        super(d,diskColor);
        this.grid = grid;
    }

    /**
     * draw player disk.
     * @param cellWidth - width of the cell.
     * @param cellHeight - height of the cell.
     * @param row - the row position of the circle
     * @param col - the col position of the circle
     */
    public void draw(double cellWidth, double cellHeight,int row,int col){
        double radius = Math.min(cellWidth,cellHeight) / 2;
        Circle circle = new Circle(radius,this.diskColor);
        this.grid.add(circle,row,col);
        GridPane.setHalignment(circle, HPos.CENTER);
    }
}
