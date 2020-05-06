package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.SceneChanger;

import java.io.IOException;

public class HandlePrisonersController {
    @FXML
    void GoBackHandlePrisonerButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "PoliceMenu.fxml");

    }
}
