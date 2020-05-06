package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.SceneChanger;

import java.io.IOException;

public class ReportCrimeController {

    @FXML
    void ReportCrimeLogOutButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "sample.fxml");

    }

    @FXML
    void UploadCrimeButtonOnAction(ActionEvent event) {

    }

}
