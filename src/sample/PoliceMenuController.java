package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;


public class PoliceMenuController {

    @FXML
    void LogOutAsPoliceButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "sample.fxml");
    }

    @FXML
    void HandlePrisonerSceneButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "");
    }


    @FXML
    void RegisterCrimeSceneButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "ReadReports.fxml");
    }

    @FXML
    void ViewConvictionsSceneButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "");
    }

    @FXML
    void ViewMostWantedSceneButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "MostWanted.fxml");
    }


    @FXML
    void ViewPrisonerSceneButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "ViewPrisoners.fxml");

    }

    @FXML
    void reportSceneButtonOnAction(ActionEvent event) {


    }

}
