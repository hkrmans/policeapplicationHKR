package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class CivilianMenuController {

    @FXML
    void CivilianBookMeetingButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "BookMeeting.fxml");
    }


    @FXML
    void CivilianMostWantedButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "MostWanted.fxml");
    }


    @FXML
    void CivilianReportCrimeButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "ReportCrime.fxml");

    }

    @FXML
    void CivilianViewConvictionsButtonOnAction(ActionEvent event) {

    }

    @FXML
    void LogOutCivilianButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "sample.fxml");
    }
}
