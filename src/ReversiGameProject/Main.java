package ReversiGameProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            HBox root = (HBox)FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));
            primaryStage.setTitle("Reversi Game");
            primaryStage.setScene(new Scene(root, 600, 420));
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
