package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import sample.*;
import sample.Models.CrimeReport;
import sample.Models.Person;
import sample.Models.Police;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportCrimeController implements Initializable {
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());

    @FXML
    private TextArea reportCrimeArea;

    @FXML
    void ReportCrimeMenuButtonOnAction(ActionEvent event) throws IOException {
        if(LoginController.getLoggedInAccount().getOwner() instanceof Police) {
            SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
        } else {
            SceneChanger.changeScene(event,"fxmlFiles/CivilianMenu.fxml");
        }
    }

    @FXML
    void UploadCrimeButtonOnAction(ActionEvent event) {
        String rapport = reportCrimeArea.getText();
        try{
            Person writer = LoginController.getLoggedInAccount().getOwner();
            CrimeReport crimeReport = new CrimeReport(rapport,writer, 0);
            dbc.addInformation(crimeReport);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
