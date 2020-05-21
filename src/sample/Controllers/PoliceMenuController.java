package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.SceneChanger;

import java.io.IOException;


public class PoliceMenuController {

    @FXML
    void LogOutAsPoliceButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/FirstPage.fxml");
    }

    @FXML
    void HandlePrisonerSceneButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/HandlePrisoners.fxml");
    }


    @FXML
    void RegisterCrimeSceneButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/ReadReports.fxml");
    }

    @FXML
    void ViewConvictionsSceneButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/ViewConviction.fxml");
    }

    @FXML
    void ViewMostWantedSceneButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/MostWanted.fxml");
    }


    @FXML
    void ViewPrisonerSceneButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/ViewPrisoners.fxml");

    }

    @FXML
    void reportSceneButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/ReportCrime.fxml");


    }

}
