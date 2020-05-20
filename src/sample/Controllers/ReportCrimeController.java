package sample.Controllers;

import com.mysql.cj.log.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import sample.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportCrimeController implements Initializable {
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());

    @FXML
    private TextArea reportCrimeArea;

    @FXML
    void ReportCrimeLogOutButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/sample.fxml");

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
