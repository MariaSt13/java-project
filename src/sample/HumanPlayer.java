package sample;


public class HumanPlayer extends Player{

    public HumanPlayer(Board.disk d) {
        super(d);
    }

    /**
     * the player choose his next move in his turn .
     * @return - point.
     */
    public Point chooseStep(UserInterface userInterface) {
        Point p = userInterface.choosePoint();
        return p;
    }
}
