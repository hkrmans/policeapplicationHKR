package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReadReportsController {

    @FXML
    void ReportGoBackButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "PoliceMenu.fxml");
    }


    @FXML
    void ReportBackButtonOnAction(ActionEvent event) {

    }


    @FXML
    void ReportNextButtonOnAction(ActionEvent event) {

    }

    @FXML
    void ReportRegisterButtonOnAction(ActionEvent event) {

    }
}
