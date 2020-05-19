package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import sample.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportCrimeController implements Initializable {
    private Person writer;

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
            CrimeRapport crimeRapport = new CrimeRapport(rapport, writer, 0);
            DbConnect.getInstance("!)!AY!U!!Q!@b!S\"a\"d%V%U%").addCrimeRapport(crimeRapport);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
