package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.SceneChanger;

public class CivilianMenuController {

    @FXML
    void CivilianBookMeetingButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/BookMeeting.fxml");
    }

    @FXML
    void CivilianMostWantedButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/ViewMostWanted.fxml");
    }

    @FXML
    void CivilianReportCrimeButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/ReportCrime.fxml");
    }

    @FXML
    void CivilianViewConvictionsButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/ViewConviction.fxml");
    }

    @FXML
    void LogOutCivilianButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/FirstPage.fxml");
    }
}
