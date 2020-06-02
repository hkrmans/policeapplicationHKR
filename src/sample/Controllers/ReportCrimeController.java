package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import sample.DbConnect;
import sample.SceneChanger;
import sample.Models.CrimeReport;
import sample.Models.Person;

public class ReportCrimeController {
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());

    @FXML
    private TextArea reportCrimeArea;

    @FXML
    private void menuButton(ActionEvent event) {
        if (LoginController.isPolice()) {
            SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
        } else {
            SceneChanger.changeScene(event, "fxmlFiles/CivilianMenu.fxml");
        }
    }

    @FXML
    private void UploadCrimeButtonOnAction() {
        String rapport = reportCrimeArea.getText();
        try {
            Person writer = LoginController.getLoggedInAccount().getOwner();
            CrimeReport crimeReport = new CrimeReport(rapport, writer, 0);
            dbc.addInformation(crimeReport);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to upload the report");
            alert.showAndWait();
        }
    }
}
