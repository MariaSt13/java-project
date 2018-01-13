package ReversiGameProject;

import java.util.Vector;

public interface UserInterface {

    //output
    void waiting();
    void currentBoard(Board b);
    void printBoard(Board b);
    void noMoves(Board.disk d);
    void played(Board.disk player, Point step);
    void gameOver();
    void winner(Board.disk d);
    void draw();
    void invalidInput();
    void askForMove() ;
    void yourMove(Board.disk d);
    void noMoves();
    void possibleMoves(Vector<Point> v) ;
    void mainMenu() ;
    void invalidTryAgain() ;
    void printListGames(String list);
    void nameNotEntered() ;

    //get input
     Point choosePoint();
}
