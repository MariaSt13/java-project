package ReversiGameProject;

import java.util.Vector;

/**
 * Interface of game logic.
 */
public interface GameLogic {
    /**
     * The function returns a vector with all possible steps for the player.
     * @param p - player.
     * @param b - board.
     * @return vector of points.
     */
    Vector<Point> returnValidMoves(Player p , Board b);

    /**
     * The function places a disc where the player has selected
     * and flips the opponents cells by the map.
     * @param player - current player.
     * @param newPoint - the placed point.
     * @param b - board.
     * @param updateMap - first update the map (when return valid moves was not called before that.)
     */
    void flipCells(Player player, Point newPoint, Board b, boolean updateMap);
}
