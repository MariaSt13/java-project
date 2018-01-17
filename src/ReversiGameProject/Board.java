package ReversiGameProject;

/**
 * A board class that represents the Reversi game board.
 * Each slot in the table can contain one of the following values:
 * "firstPlayer" A slot of the first Actor.
 * "secondPlayer" A slot of the second Actor.
 * Or an "noPlayer" slot.
 */
public class Board {

    private int rowSize;
    private int colSize;
    public enum disk {firstPlayer ,secondPlayer , noPlayer}
    private disk[][] array;

    /**
     * constructor.
     * @param rowSize - number of rows.
     * @param colSize - number of columns.
     */
    public Board(int rowSize,int colSize) {

        this.rowSize = rowSize + 1;
        this.colSize = colSize + 1;
        this.array = new disk[this.rowSize][this.colSize];

        //Initializes all the cells in the array with a space character.
        for (int i = 1; i < this.rowSize; i++) {
            for (int j = 1 ; j < this.colSize; j++) {
                array[i][j] = disk.noPlayer;
            }
        }

        //Boot the cells of the players.
        int x =((this.rowSize)/2);
        int y = ((this.colSize)/2);
        array[x][y] = disk.secondPlayer;
        array[x+1][y+1] = disk.secondPlayer;
        array[x][y+1] = disk.firstPlayer;
        array[x+1][y] = disk.firstPlayer;
    }

    /**
     * Deep copy constructor.
     * @param board - the board to copy.
     */
    public Board(Board board){
        this.colSize = board.getColSize();
        this.rowSize = board.getRowSize();
        this.array = new disk[rowSize][colSize];

        disk[][] arrToCopy = board.getArray();

        //deep copy
        for (int i = 1; i < this.rowSize; i++) {
            for (int j = 1; j < this.colSize ; j++) {
                array[i][j] = arrToCopy[i][j];
            }
        }
    }

    /**
     * The function get a point and return
     * true if the point is in the board range.
     * else , return false.
     * @param p - point to check.
     * @return - boolean.
     */
    public boolean pointIsInRange(Point p){
        return p.getX() > 0 && p.getX() < this.rowSize &&
                p.getY()>0 && p.getY() < this.colSize;
    }

    /**
     * This function checks if the board is full.
     * If there is no noPlayer cells return true.
     * else return false.
     * @return true or false.
     */
    public boolean ifBoardIsFull(){
        for (int i = 1; i < this.rowSize ; i++) {
            for (int j = 1; j < this.colSize ; j++) {
                if(array[i][j] == disk.noPlayer){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Return the number of disks in the board of the given player.
     * @param d -the type of disk to count.
     * @return - number of disks.
     */
    public int numOfPlayerDisks(disk d){
        int count = 0;
        for (int i = 1; i < this.rowSize ; i++) {
            for (int j = 1; j < this.colSize ; j++) {
                if(array[i][j] == d){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * @return rowSize - number of rows.
     */
    public int getRowSize() {
        return rowSize;
    }

    /**
     * @return colSize - number of columns.
     */
    public int getColSize() {
        return colSize;
    }

    /**
     * @return array - the game matrix.
     */
    public disk[][] getArray() {
        return array;
    }
}