package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.SceneChanger;

import java.io.IOException;

public class ViewConvictionController {

    @FXML
    void LogOutViewConvictionButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "sample.fxml");

    }
}
