package ReversiGameProject;

import java.util.Vector;

public class ReversiGame {

    private Board gameBoard;
    private Player whitePlayer;
    private Player blackPlayer;
    private GameLogic gameLogic;
    private Player hisTurn;
    private UserInterface userInterface;

    /**
     * This function runs the game.
     */
    public ReversiGame(Board gameBoard, Player blackPlayer, Player whitePlayer, GameLogic gameLogic,
                       UserInterface display) {
        this.gameBoard = gameBoard;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.gameLogic = gameLogic;
        this.hisTurn = this.blackPlayer;
        this.userInterface = display;
    }

    // this function run the game.
    public void playOneTurn(Point step,ReversiBoardController r) {
        boolean firstTry = true;

        //print board and current player.
        printCurrentBoard();

        //get a vector of possible points and print it.
        Vector<Point> v = this.gameLogic.returnValidMoves(this.hisTurn, this.gameBoard);
        if(!v.contains(step)) {
            return;
        }
        //makes the current player's choice and changes the next player's turn.
        this.gameLogic.flipCells(this.hisTurn, step, gameBoard,false);

        changeTurn();
        r.draw();
        if(isGameOver()){
            gameOver();
        }
    }

    /**
     * Gets the step from the player.
     * @param firstTry - true if it is the first try.
     * @param v vector of points.
     * @return Point.
     */
    private Point getStep(boolean firstTry, Vector<Point> v){
        //get input from the player.
        Point step;
        do {
            if(!firstTry){
                userInterface.invalidInput();
            }
            firstTry = false;
            userInterface.askForMove();
            step = this.hisTurn.chooseStep(userInterface);

        } while (!step.ifThePointIsInVector(v));

        return step;
    }

    /**
     * Prints the current board.
     */
    private void printCurrentBoard() {
        //print board and current player.
        userInterface.currentBoard(this.gameBoard);
    }

    // print the final board and the winner.
    private void gameOver(){
        printCurrentBoard();
        userInterface.gameOver();
        int countWhite = 0;
        int countBlack = 0;
        Board.disk[][] array = gameBoard.getArray();

        //the function checks which player has more discs on the board.
        for (int i = 1; i < gameBoard.getColSize() ; i++) {
            for (int j = 1; j < gameBoard.getRowSize(); j++) {
                if(array[i][j] == blackPlayer.getDisk()){
                    countBlack++;
                }
                else if(array[i][j] == whitePlayer.getDisk()){
                    countWhite++;
                }
            }
        }

        //prints the winning player.
        if(countBlack > countWhite){
            userInterface.winner(blackPlayer.getDisk());
        }
        else if(countBlack < countWhite){
            userInterface.winner(whitePlayer.getDisk());
        }
        else if(countBlack == countWhite){
            userInterface.draw();
        }
    }

    /**
     * Checks if the game is over.
     * if the game is over return true, else return false.
     * @return boolean.
     */
    public boolean isGameOver() {
        //if the there is no empty cells return true.
        if(this.gameBoard.ifBoardIsFull()){
            return true;
        }

        Vector<Point> v1;
        Vector<Point> v2;

        v1 = this.gameLogic.returnValidMoves(this.hisTurn,this.gameBoard);

        //if there is no possible moves to current player.
        if(v1.size()==0){
            changeTurn();
            v2 = this.gameLogic.returnValidMoves(this.hisTurn,this.gameBoard);

            //when there are no further possible moves to either side.
            if(v2.size() == 0){
                return true;
            }
            changeTurn();
            return false;
        }
        return false;
    }

    /**
     * Prints the possible moves of the player in his turn.
     * @param v vector of points.
     * @return bool - if there are possible moves.
     */
    boolean printPossibleMoves(Vector<Point> v) {
        Board.disk c = (this.hisTurn.getDisk());
        userInterface.yourMove(c);

        //if there is no possible moves.
        if(v.size() == 0){
            userInterface.noMoves();
            return false;
        }
        userInterface.possibleMoves(v);
        return true;
    }

    /**
     * Switch between the players turn.
     */
    private void changeTurn (){
        if(this.hisTurn == this.blackPlayer){
            this.hisTurn = this.whitePlayer;
        } else{
            this.hisTurn = this.blackPlayer;
        }
    }

}
