package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sample.SceneChanger;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportCrimeController implements Initializable {

    @FXML
    void ReportCrimeLogOutButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/sample.fxml");

    }

    @FXML
    void UploadCrimeButtonOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
