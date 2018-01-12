package sample;

import java.util.Vector;

public interface GameLogic {

    Vector<Point> returnValidMoves(HumanPlayer p , Board b);
    void flipCells(HumanPlayer player, Point newPoint, Board b, boolean updateMap);
}
