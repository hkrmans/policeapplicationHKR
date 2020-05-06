package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.SceneChanger;

import java.io.IOException;

public class CivilianMenuController {

    @FXML
    void CivilianBookMeetingButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/BookMeeting.fxml");
    }


    @FXML
    void CivilianMostWantedButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/MostWanted.fxml");
    }


    @FXML
    void CivilianReportCrimeButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/ReportCrime.fxml");

    }

    @FXML
    void CivilianViewConvictionsButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/ViewConviction.fxml");

    }

    @FXML
    void LogOutCivilianButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/sample.fxml");
    }
}
