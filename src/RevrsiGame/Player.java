package RevrsiGame;

import javafx.scene.paint.Color;

/**
 * Player abstract class.
 * a player can draw his disks.
 */
abstract public class Player {
    protected Board.disk playerDisk;
    protected Color diskColor;

    /**
     * Constructor.
     * @param d - disk of the player.
     * @param diskColor - color of the disk.
     */
    public Player(Board.disk d, Color diskColor){
        this.playerDisk = d;
        this.diskColor = diskColor;
    }

    /**
     * @return playerDisk.
     */
    public Board.disk getDisk() {
        return this.playerDisk;
    }

    /**
     * draw player disk.
     * @param cellWidth - width of the cell.
     * @param cellHeight - height of the cell.
     * @param row - the row position of the circle
     * @param col - the col position of the circle
     */
    public abstract void draw(float cellWidth, float cellHeight,int row,int col);

    /**
     * @return diskColor - color of the disk.
     */
    public Color getColor(){ return this.diskColor;}
}
