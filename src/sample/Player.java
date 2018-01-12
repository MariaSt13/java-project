package sample;

abstract public class Player {
    private Board.disk color;

    // the player choose his next move in his turn .
    abstract Point chooseStep(UserInterface userInterface);

    //contructor.
    public Player(Board.disk d){
        this.color = d;
    }

    //get disk
    public Board.disk getDisk() {
        return this.color;
    }
}
