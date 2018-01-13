package ReversiGameProject;

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;


public class StandardGameLogic implements GameLogic{

    private Map<Point,Vector<Point>> pointsMap;

    public StandardGameLogic() {
        pointsMap = new TreeMap<>();
    }

    /**
     * The function returns a vector with all possible steps for the player.
     */
   public Vector<Point> returnValidMoves(Player p , Board b) {
        Board.disk[][] array = b.getArray();
        Vector<Point> v =  new Vector<>();
        pointsMap.clear();

        //check every point in the matrix
        for (int i = 1; i < b.getRowSize() ; i++) {
            for (int j = 1; j < b.getColSize(); j++) {
                //check if the point is a valid move for the player
                checkPoint(p,new Point(i,j),b ,v);
            }
        }
        return v;
    }
    private Point ifReverseOpponentDisk(Player player, Point p, Board b, int i, int j) {
        Board.disk[][] array = b.getArray();
        Point currentPoint = p;
        Vector<Point> v = new Vector<>();
        int x = currentPoint.getX() + i;
        int y = currentPoint.getY() + j;
        Point point = new Point(x,y);

        /*
         * while the point is not outside the boundaries of the matrix and
         * the point is not empty and not the player color.
         */
        while(b.pointIsInRange(point) && array[point.getX()][point.getY()] != Board.disk.empty &&
                array[point.getX()][point.getY()] != player.getDisk()){

            //if the point is not exist in the vector
            if(!point.ifThePointIsInVector(v)){
                v.add(point);
            }

            x = point.getX() + i;
            y = point.getY() + j;
            point = new Point(x,y);
        }

        //if it is cell empty and in board
        if(b.pointIsInRange(point)){
            if(array[x][y] == Board.disk.empty){
                //if this point key is already exist in the map.
                if(pointsMap.containsKey(point)){
                    pointsMap.get(point).addAll(v);
                }
                else{
                    pointsMap.put(point,v);
                }
                return point;
            }
        }

        return new Point(-1,-1);
    }

    /**
     * The function get a point and check if it is a valid point (possible move) for the player.
     * @param player - current player.
     * @param p - the point that is checked.
     * @param b - current board.
     * @param vec - vector of possible points.
     */
    private void checkPoint(Player player, Point p, Board b, Vector<Point> vec) {
        Board.disk[][] array = b.getArray();
        int x = p.getX();
        int y = p.getY();

        //if the cell is of th current player
        if(array[x][y] == player.getDisk()){

            //the loop goes through all the cell's neighbors.
            for (int i = -1; i < 2 ; i++) {
                for (int j = -1; j < 2 ; j++) {

                    Point cuurentNeighbor = new Point(x+i,y+j);

                    //If the point is not outside the boundaries of the matrix
                    if( b.pointIsInRange(cuurentNeighbor)){

                        //If it is cell of the opposing player
                        if(array[x+i][y+j] != Board.disk.empty && array[x+i][y+j] != player.getDisk()){

                            //if there will be reversion of the opponent's washers
                            Point point = ifReverseOpponentDisk(player,new Point(x,y),b,i,j);

                            if(b.pointIsInRange(point)){
                                if(!point.ifThePointIsInVector(vec)){
                                    vec.add(point);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * The function places a disc where the player has selected
     * and flips the opponents cells by the map.
     * @param player - current player.
     * @param newPoint - the placed point.
     * @param b - board.
     * @param updateMap - first update the map. (when return valid moves was not called before that.)
     */
    public void flipCells(Player player, Point newPoint, Board b, boolean updateMap) {
        if ( updateMap == true) {
            returnValidMoves(player, b);
        }
        Board.disk[][] array = b.getArray();
        array[newPoint.getX()][newPoint.getY()] = player.getDisk();
        Vector<Point> v = pointsMap.get(newPoint);

        //reverse opponent cells.
        for (int i = 0; i < v.size() ; i++) {
            Point pointToReverse = v.elementAt(i);
            array[pointToReverse.getX()][pointToReverse.getY()] = player.getDisk();
        }

        //clear map
        pointsMap.clear();
    }

}
