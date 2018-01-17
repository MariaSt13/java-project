package ReversiGameProject;

import java.util.Vector;

/**
 * Point class.
 * Points represented by x and y value.
 */
public class Point implements Comparable{

    private int x;
    private int y;

    /**
     * constructor.
     * @param x - x value of the point
     * @param y - y value of the point
     */
    public Point(int x, int y) {
        this.x =x;
        this.y =y;
    }

    /**
     * This function gets a vector of points and
     * returns 'true' if this point is exist in
     * the vector. else return 'false'.
     * @param v -  vector of points.
     * @return boolean.
     */
    public boolean ifThePointIsInVector(Vector<Point> v) {

        for (int i = 0; i < v.size(); i++) {
            int x1 = v.elementAt(i).getX();
            int y1 = v.elementAt(i).getY();

            if(x == x1 && y ==y1){
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the points are equal.
     * @param p - other point that we are comparing to.
     * @return return true is the points are equal, false otherwise.
     */
    public boolean equals(Object p) {
        if (p.getClass() != this.getClass()) {
            return false;
        }
        Point other = (Point)p;
        if ((this.x == other.x) && (this.y == other.y)) {
            return true;
        }
        return false;
    }

    /**
     * @return - x value of the point
     */
    public int getX() {
        return x;
    }

    /**
     * @return - y value of the point
     */
    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Object other) {
        Point p = (Point)other;
        int returnVal =0;

        if (p.x > x)
            returnVal = -1;
        else if (x > p.x)
            returnVal = 1;
        else if (p.y > y)
            returnVal = -1;
        else if (y > p.y)
            returnVal = 1;
        else if((this.x == p.x) && (this.y == p.y))
            returnVal = 0;
        return  returnVal;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y +")";
    }
}
