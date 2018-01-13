package ReversiGameProject;

import java.util.Vector;

public interface GameLogic {

    Vector<Point> returnValidMoves(Player p , Board b);
    void flipCells(Player player, Point newPoint, Board b, boolean updateMap);
}
