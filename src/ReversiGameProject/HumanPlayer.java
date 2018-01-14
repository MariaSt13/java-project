package ReversiGameProject;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class HumanPlayer extends Player{
    private GridPane grid;

    public HumanPlayer(Board.disk d,GridPane grid,Color diskColor) {
        super(d,diskColor);
        this.grid = grid;
    }

    /**
     * the player choose his next move in his turn .
     * @return - point.
     */
    public Point chooseStep(UserInterface userInterface) {
        Point p = userInterface.choosePoint();
        return p;
    }

    /**
     * draw player disk.
     */
    public void draw(int cellWidth, int cellHeight,int row,int col){
        double radius = Math.min(cellWidth,cellHeight) / 2;
        Circle circle = new Circle(radius,this.diskColor);
        this.grid.add(circle,row,col);
    }
}
