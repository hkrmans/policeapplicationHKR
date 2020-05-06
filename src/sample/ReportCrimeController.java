package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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
