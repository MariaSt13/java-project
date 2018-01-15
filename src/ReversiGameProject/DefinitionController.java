package ReversiGameProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class DefinitionController implements Initializable {
    @FXML
    private ChoiceBox<String> choiceBoxPlayer1;
    @FXML
    private ChoiceBox<String> choiceBoxPlayer2;
    @FXML
    private TextField size;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.choiceBoxPlayer1 = new ChoiceBox<>();
        this.choiceBoxPlayer2 = new ChoiceBox<>();
    }
    @FXML
    private void save(ActionEvent event) {
        PrintWriter os = null;
        try {
            os = new PrintWriter( new OutputStreamWriter(new FileOutputStream("DefinitionFile.txt")));
            os.printf("color1: %s\n", choiceBoxPlayer1.getSelectionModel().getSelectedItem());
            os.printf("color2: %s\n", choiceBoxPlayer2.getValue());
            os.printf("size: %s\n", size.getText());
        } catch (IOException e) {
            System.out.println("Error writing to file");
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
}
