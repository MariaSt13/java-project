package ReversiGameProject;

import javafx.scene.paint.Color;

abstract public class Player {
    protected Board.disk color;
    protected Color diskColor;

    // the player choose his next move in his turn .
    abstract Point chooseStep(UserInterface userInterface);

    //contructor.
    public Player(Board.disk d,Color diskColor){
        this.color = d;
        this.diskColor = diskColor;
    }

    //get disk
    public Board.disk getDisk() {
        return this.color;
    }

    public abstract void draw(float cellWidth, float cellHeight,int row,int col);

    public Color getColor(){ return this.diskColor;}
}
