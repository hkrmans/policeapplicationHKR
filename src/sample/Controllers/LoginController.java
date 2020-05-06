package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.SceneChanger;

import java.io.IOException;

public class LoginController {

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "sample.fxml");
    }


    @FXML
    void LogInToPoliceMenuButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "PoliceMenu.fxml");
    }
}
