package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import sample.DbConnect;
import sample.Models.Police;
import sample.Models.Prisoner;
import sample.SceneChanger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewPrisonersController implements Initializable {
    private ArrayList<Prisoner> prisoners = new ArrayList<>();
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());

    @FXML
    private TextArea showPrisoner;

    @FXML
    void GoBackViewPrisonersButtonOnAction(ActionEvent event) {
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

    private void showPrisoners() {
        for (Prisoner p : prisoners) {
            showPrisoner.appendText(p.getFirstName() + " | " + p.getLastName() + " | CN:" + p.getCivicNumber()
                    + " | ID:" + p.getPrisonerId() + " | Release Date:" + p.getReleaseDate() + "\n");
        }
    }

    private void fillPrisonerList() {
        try {
            Prisoner prisoner = new Prisoner(null, null, null, 0, null);
            prisoners.add(prisoner);
            dbc.getInfo(prisoners);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillPrisonerList();
        showPrisoners();
    }
}
