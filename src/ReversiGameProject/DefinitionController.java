package ReversiGameProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import static java.lang.System.exit;

public class DefinitionController implements Initializable {
    @FXML
    private ChoiceBox<String> choiceBoxPlayer1;
    @FXML
    private ChoiceBox<String> choiceBoxPlayer2;
    @FXML
    private ChoiceBox<Integer> choiceBoxSize;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ReadDefinitionFile file = new ReadDefinitionFile();
        Map<String,String> map = file.readFile("DefinitionFile.txt");

        if (map.isEmpty()) {
            choiceBoxPlayer1.setValue("Black");
            choiceBoxPlayer2.setValue("White");
            choiceBoxSize.setValue(8);
        }
        else {
            choiceBoxPlayer1.setValue(map.get("color1"));
            choiceBoxPlayer2.setValue(map.get("color2"));
            choiceBoxSize.setValue(Integer.parseInt(map.get("size")));
        }
    }
    @FXML
    private void save(ActionEvent event) {
        PrintWriter os = null;
        try {
            os = new PrintWriter( new OutputStreamWriter(new FileOutputStream("DefinitionFile.txt")));
            os.printf("color1: %s\n", choiceBoxPlayer1.getValue());
            os.printf("color2: %s\n", choiceBoxPlayer2.getValue());
            os.printf("size: %d\n", choiceBoxSize.getValue());
        } catch (IOException e) {
            System.out.println("Error writing to file");
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
}
