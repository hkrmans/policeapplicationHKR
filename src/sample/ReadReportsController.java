package sample;

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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReadReportsController implements Initializable {
    private ArrayList<CrimeRapport> rapports;
    private int index = 0;
    private Civilian b = new Civilian("a", "b", "12121");
    private CrimeRapport a = new CrimeRapport("Hej", b);
    private CrimeRapport c = new CrimeRapport("jeh", b);


    @FXML
    private TextArea crimeRapportArea;

    @FXML
    void ReportGoBackButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "PoliceMenu.fxml");
    }


    @FXML
    void ReportBackButtonOnAction(ActionEvent event) {
        try {
            if (index <= rapports.size()){
                index--;
                crimeRapportArea.setText(rapports.get(index).getRapport() + " | " + rapports.get(index).getWriter());
            }else if (index < 0){
                index = 0;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("This is the first rapport");
                alert.setContentText("This is the first rapport in the system");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @FXML
    void ReportNextButtonOnAction(ActionEvent event) {
        try {
            if (index < rapports.size()){
                index++;
                crimeRapportArea.setText(rapports.get(index).getRapport() + " | " + rapports.get(index).getWriter());
            }else if (index > rapports.size()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("No rapports left");
                alert.setContentText("There aren't any rapports left");
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
            rapports.add(a);
            rapports.add(c);
            crimeRapportArea.setText(rapports.get(index).getRapport() + " | " + rapports.get(index).getWriter());

    }
}

