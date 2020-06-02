package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.DbConnect;
import sample.SceneChanger;

public class PoliceMenuController {

    @FXML
    private void LogOutAsPoliceButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/FirstPage.fxml");
        LoginController.setIsPolice(false);
    }

    @FXML
    private void HandlePrisonerSceneButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/HandlePrisoners.fxml");
    }

    @FXML
    private void RegisterCrimeSceneButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/ReadReports.fxml");
    }

    @FXML
    private void ViewConvictionsSceneButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/ViewConviction.fxml");
    }

    @FXML
    private void ViewMostWantedSceneButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/ViewMostWanted.fxml");
    }

    @FXML
    private void ViewPrisonerSceneButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/ViewPrisoners.fxml");
    }

    @FXML
    private void reportSceneButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/ReportCrime.fxml");
    }

    @FXML
    private void ReadRegisteredCrimesButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/ReadRegisteredCrimes.fxml");
    }

    @FXML
    private void AddPrisonerButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/AddPrisoner.fxml");
    }

    @FXML
    private void addConvictionScene(ActionEvent event){
        SceneChanger.changeScene(event,"fxmlFiles/AddConviction.fxml");
    }
}
