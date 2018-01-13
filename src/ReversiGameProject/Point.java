package ReversiGameProject;

import java.util.Vector;

public class Point implements Comparable{

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

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x =x;
        this.y =y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

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

    @Override
    public String toString() {
        return "(" + x + "," + y +")";
    }

    /**
     * check if the points are equal.
     * @param other - other point that we are comparing to.
     * @return return true is the points are equal, false otherwise.
     */
    // equals -- return true is the points are equal, false otherwise
    public boolean equals(Object p) {
        Point other = (Point)p;
        if ((this.x == other.x) && (this.y == other.y)) {
            return true;
        }
        return false;
    }
}
