package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.Civilian;
import sample.CrimeRapport;
import sample.SceneChanger;
import sample.Singleton;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReadReportsController implements Initializable {
    private ArrayList<CrimeRapport> rapports;
    private int index = 0;


    @FXML
    private TextArea crimeRapportArea;

    @FXML
    void ReportGoBackButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
    }


    @FXML
    void ReportBackButtonOnAction(ActionEvent event) {
        try {
            index--;
            if (index < 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("This is the first rapport");
                alert.setContentText("This is the first rapport in the system");
                alert.showAndWait();
                index = 0;
            }else if (index <= rapports.size()){
                crimeRapportArea.setText(rapports.get(index).getRapport() + " | " + rapports.get(index).getWriter());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @FXML
    void ReportNextButtonOnAction(ActionEvent event) {
        try {
            index++;
            if (index > rapports.size()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("No rapports left");
                alert.setContentText("There aren't any rapports left");
                alert.showAndWait();
                index = rapports.size()-1;
            }else if (index < rapports.size()){
                crimeRapportArea.setText(rapports.get(index).getRapport() + " | " + rapports.get(index).getWriter());
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void ReportRegisterButtonOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            rapports = Singleton.getCrimeRapportInstance().getCrimeRapportList();
        }catch (Exception e){
            e.printStackTrace();
        }
        for (CrimeRapport e : rapports) {
            crimeRapportArea.setText(e.getRapport() + " | " + e.getWriter());
        }

    }
}

