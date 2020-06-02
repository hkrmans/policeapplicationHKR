package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import sample.DbConnect;
import sample.Models.Crime;
import sample.Models.WantedCriminal;
import sample.SceneChanger;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReadRegisteredCrimesController implements Initializable {
    private ArrayList<Crime> crimes = new ArrayList<>();
    private ArrayList<WantedCriminal> wantedCriminals = new ArrayList<>();
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
        Crime crime = new Crime(null, null, null, null, 0);
        crimes.add(crime);
        dbc.getInfo(crimes);

        WantedCriminal wantedCriminal = new WantedCriminal(null, null, null, 0, 0, 0);
        wantedCriminals.add(wantedCriminal);
        dbc.getInfo(wantedCriminals);

    }

    private void getCrime() {
        if (crimes.size() != 0) {
            readRegisteredCrimesTextField.clear();
            readRegisteredCrimesTextField.appendText("Type of crime: " + crimes.get(index).getTypeOfCrime() + "\n");
            readRegisteredCrimesTextField.appendText("Crime ID: " + String.valueOf(crimes.get(index).getCrimeID()) + "\n");
            readRegisteredCrimesTextField.appendText("Date: " + String.valueOf(crimes.get(index).getDateOfCrime()) + "\n");
            readRegisteredCrimesTextField.appendText("-- Report --\n" + crimes.get(index).getRapport().getRapport() + "\n");

            for (int i = 0; i < crimes.size(); i++) {
                for (int j = 0; j < wantedCriminals.size(); j++) {
                    if (crimes.get(i).getSuspect().getCivicNumber().equals(wantedCriminals.get(j).getCivicNumber())) {
                        readRegisteredCrimesTextField.appendText("--Suspect--\n");
                        readRegisteredCrimesTextField.appendText("Firstname: " + wantedCriminals.get(j).getFirstName() + "\n");
                        readRegisteredCrimesTextField.appendText("Lastname: " + wantedCriminals.get(j).getLastName() + "\n");
                        readRegisteredCrimesTextField.appendText("Civicnumber: " + wantedCriminals.get(j).getCivicNumber() + "\n");
                    }
                }

            }

            readRegisteredCrimesTextField.appendText("-- Plaintiff --\n");
            readRegisteredCrimesTextField.appendText("First name: " + crimes.get(index).getRapport().getWriter().getFirstName() + "\n");
            readRegisteredCrimesTextField.appendText("Last name: " + crimes.get(index).getRapport().getWriter().getLastName() + "\n");
            readRegisteredCrimesTextField.appendText("Civic number: " + crimes.get(index).getRapport().getWriter().getCivicNumber() + "\n");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillList();
        getCrime();
    }
}

