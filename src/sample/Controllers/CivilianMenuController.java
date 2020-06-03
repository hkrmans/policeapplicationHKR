package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.SceneChanger;

public class CivilianMenuController {

    @FXML
    private void CivilianBookMeetingButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/BookMeeting.fxml");
    }

    @FXML
    private void CivilianMostWantedButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/ViewMostWanted.fxml");
    }

    @FXML
    private void CivilianReportCrimeButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/ReportCrime.fxml");
    }

    @FXML
    private void CivilianViewConvictionsButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/ViewConviction.fxml");
    }

    @FXML
    private void LogOutCivilianButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/FirstPage.fxml");
    }
}
