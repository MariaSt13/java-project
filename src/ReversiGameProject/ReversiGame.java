package ReversiGameProject;

import java.util.Vector;

/**
 * This class runs the game. The game ends when the entire
 * board is full or when there are no further possible
 * moves for both sides.
 */
public class ReversiGame {

    private Board gameBoard;
    private Player secondPlayer;
    private Player firstPlayer;
    private GameLogic gameLogic;
    private Player hisTurn;

    /**
     * constructor.
     * @param gameBoard - the board game.
     * @param firstPlayer - the first player.
     * @param secondPlayer - the second player.
     * @param gameLogic - the logic of the game.
     */
    public ReversiGame(Board gameBoard, Player firstPlayer, Player secondPlayer, GameLogic gameLogic) {
        this.gameBoard = gameBoard;
        this.secondPlayer = secondPlayer;
        this.firstPlayer = firstPlayer;
        this.gameLogic = gameLogic;
        this.hisTurn = this.firstPlayer;
    }

    /**
     * This function run one turn.
     * @param step - current step.
     * @param gameController - Controller of the game.
     */
    public void playOneTurn(Point step,ReversiGameController gameController) {
        //get a vector of possible points and print it.
        Vector<Point> v = this.gameLogic.returnValidMoves(this.hisTurn, this.gameBoard);

        //if the vector is not empty.
        if(v.size() != 0){

            //check if the step is valid
            if(!v.contains(step)) {
                return;
            }
            //makes the current player's choice and changes the next player's turn.
            this.gameLogic.flipCells(this.hisTurn, step, gameBoard,false);
        }

        changeTurn();
        gameController.draw(this.hisTurn);

        //check if the game is over
        if(isGameOver()){
            gameOver(gameController);
        }
    }

    /**
     *  print the winner.
     * @param controller - game controller.
     */
    private void gameOver(ReversiGameController controller){
        //the function checks which player has more discs on the board.
        int countFirst = this.gameBoard.numOfPlayerDisks(this.firstPlayer.getDisk());
        int countSecond = this.gameBoard.numOfPlayerDisks(this.secondPlayer.getDisk());

        //prints the winning player.
        if(countFirst > countSecond){
           controller.gameOver(Board.disk.firstPlayer);
        }
        else if(countFirst < countSecond){
           controller.gameOver(Board.disk.secondPlayer);
        }
        else if(countFirst == countSecond){
           controller.gameOver(Board.disk.noPlayer);
        }
    }

    /**
     * Checks if the game is over.
     * if the game is over return true, else return false.
     * @return boolean.
     */
    public boolean isGameOver() {
        //if the there is no noPlayer cells return true.
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
     * Switch between the players turn.
     */
    private void changeTurn (){
        if(this.hisTurn == this.firstPlayer){
            this.hisTurn = this.secondPlayer;
        } else{
            this.hisTurn = this.firstPlayer;
        }
    }
}
