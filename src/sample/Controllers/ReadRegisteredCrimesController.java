package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import sample.DbConnect;
import sample.Models.Crime;
import sample.SceneChanger;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReadRegisteredCrimesController implements Initializable {
    private ArrayList<Crime> crimes = new ArrayList<>();
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());
    private int index = 0;

    @FXML
    private TextArea readRegisteredCrimesTextField;

    @FXML
    private void backRegisteredCrimeButtonOnAction() {
        index--;
        if (index < 0) {
            index = crimes.size() - 1;
        }
        getCrime();
    }

    @FXML
    private void nextRegisteredCrimeButtonOnAction() {
        index++;
        if (index > crimes.size() - 1) {
            index = 0;
        }
        getCrime();
    }

    @FXML
    private void menuButton(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
    }

    private void fillList() {
        try {
            Crime crime = new Crime(null, null, null, null, 0);
            crimes.add(crime);
            dbc.getInfo(crimes);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to fetch information from the database");
            alert.showAndWait();
        }
    }

    private void getCrime() {
        readRegisteredCrimesTextField.clear();
        readRegisteredCrimesTextField.appendText("Type of crime: " + crimes.get(index).getTypeOfCrime() + "\n");
        readRegisteredCrimesTextField.appendText("Crime ID: " + String.valueOf(crimes.get(index).getCrimeID()) + "\n");
        readRegisteredCrimesTextField.appendText("Date: " + String.valueOf(crimes.get(index).getDateOfCrime()) + "\n");
        readRegisteredCrimesTextField.appendText("-- Report --\n" + crimes.get(index).getRapport().getRapport() + "\n");
        /*if(crimes.get(index).getSuspect() != null) {
            ReadRegisteredCrimesTextField.appendText(crimes.get(index).getSuspect().getFirstName());
            ReadRegisteredCrimesTextField.appendText(crimes.get(index).getSuspect().getLastName());
            ReadRegisteredCrimesTextField.appendText(crimes.get(index).getSuspect().getCivicNumber());
        }

         */
        readRegisteredCrimesTextField.appendText("-- Plaintiff --\n");
        readRegisteredCrimesTextField.appendText("First name: " + crimes.get(index).getRapport().getWriter().getFirstName() + "\n");
        readRegisteredCrimesTextField.appendText("Last name: " + crimes.get(index).getRapport().getWriter().getLastName() + "\n");
        readRegisteredCrimesTextField.appendText("Civic number: " + crimes.get(index).getRapport().getWriter().getCivicNumber() + "\n");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillList();
        getCrime();
    }
}

