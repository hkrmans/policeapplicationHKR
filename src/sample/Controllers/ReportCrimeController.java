package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import sample.*;
import sample.Models.CrimeRapport;
import sample.Models.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportCrimeController implements Initializable {
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());

    @FXML
    private TextArea reportCrimeArea;

    @FXML
    void ReportCrimeLogOutButtonOnAction(ActionEvent event) throws IOException {
        try {
            if (LoginController.isPolice()) {
                SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
            } else {
                SceneChanger.changeScene(event, "fxmlFiles/CivilianMenu.fxml");
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Scenefail");
            alert.setContentText("Failed to change scene!");
            alert.showAndWait();
        }

    }

    @FXML
    void UploadCrimeButtonOnAction(ActionEvent event) {
        String rapport = reportCrimeArea.getText();
        try{
            Person writer = LoginController.getLoggedInAccount().getOwner();
            CrimeRapport crimeRapport = new CrimeRapport(rapport,writer, 0);
            dbc.addInformation(crimeRapport);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
