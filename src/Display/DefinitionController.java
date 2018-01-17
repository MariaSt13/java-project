package Display;

import RevrsiGame.ReadDefinitionFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controller of the definitions.
 */
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

        //if the map is empty, set default settings.
        if (map.isEmpty()) {
            choiceBoxPlayer1.setValue("Black");
            choiceBoxPlayer2.setValue("White");
            choiceBoxSize.setValue(8);
        }

        //if the map is not empty, set settings from the file.
        else {
            choiceBoxPlayer1.setValue(map.get("firstPlayerColor"));
            choiceBoxPlayer2.setValue(map.get("secondPlayerColor"));
            choiceBoxSize.setValue(Integer.parseInt(map.get("size")));
        }
    }


    /**
     * This function write definitions to file.
     * @param event - ActionEvent object.
     */
    @FXML
    private void save(ActionEvent event) {
        PrintWriter os = null;
        try {
            os = new PrintWriter( new OutputStreamWriter(new FileOutputStream("DefinitionFile.txt")));

            //writes definitions
            os.printf("firstPlayerColor: %s\n", choiceBoxPlayer1.getValue());
            os.printf("secondPlayerColor: %s\n", choiceBoxPlayer2.getValue());
            os.printf("size: %d\n", choiceBoxSize.getValue());

        //catch error
        } catch (IOException e) {
            System.out.println("Error writing to file");

        //close file
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
}
