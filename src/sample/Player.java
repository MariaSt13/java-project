package sample;


public class Player {
    private Board.disk disk;

    public Player(Board.disk d) {
        this.disk = d;
    }

    /**
     * the player choose his next move in his turn .
     * @return - point.
     */
    public Point chooseStep(UserInterface userInterface) {
        Point p = userInterface.choosePoint();
        return p;
    }

    public Board.disk getDisk() {
        return disk;
    }
}
