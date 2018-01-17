package ReversiGameProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class extends Application.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            VBox root = (VBox)FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));
            primaryStage.setTitle("Reversi Game");
            primaryStage.setScene(new Scene(root, 600, 480));
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Main function.
     * @param args - arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
