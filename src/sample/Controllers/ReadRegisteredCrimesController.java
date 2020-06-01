package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import sample.DbConnect;
import sample.Models.Crime;
import sample.Models.CrimeReport;
import sample.SceneChanger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReadRegisteredCrimesController implements Initializable {
    private ArrayList<Crime> crimes = new ArrayList<>();
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());
    private int index = 0;

    @FXML
    private TextArea ReadRegisteredCrimesTextField;

    @FXML
    void BackRegisteredCrimeButtonOnAction(ActionEvent event) {
        index --;
        if (index <0){
            index=crimes.size()-1;
        }
        getCrime();
    }


    @FXML
    void NextRegisteredCrimeButtonOnAction(ActionEvent event) {
        index ++;
        if (index > crimes.size()-1){
            index=0;
        }
        getCrime();
    }


    @FXML
    void CrimesMenuButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event,"fxmlFiles/PoliceMenu.fxml");

    }

    private void fillList(){
        try {
            Crime crime = new Crime(null,null,null,null,0);
            crimes.add(crime);
            dbc.getInfo(crimes);

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
    private void getCrime(){
        ReadRegisteredCrimesTextField.clear();
        ReadRegisteredCrimesTextField.appendText("Type of crime: " + crimes.get(index).getTypeOfCrime() + "\n");
        ReadRegisteredCrimesTextField.appendText("Crime ID: " + String.valueOf(crimes.get(index).getCrimeID())+ "\n");
        ReadRegisteredCrimesTextField.appendText("Date: " + String.valueOf(crimes.get(index).getDateOfCrime()) + "\n");
        ReadRegisteredCrimesTextField.appendText("-- Report --\n" + crimes.get(index).getRapport().getRapport() + "\n");
        /*if(crimes.get(index).getSuspect() != null) {
            ReadRegisteredCrimesTextField.appendText(crimes.get(index).getSuspect().getFirstName());
            ReadRegisteredCrimesTextField.appendText(crimes.get(index).getSuspect().getLastName());
            ReadRegisteredCrimesTextField.appendText(crimes.get(index).getSuspect().getCivicNumber());
        }

         */
        ReadRegisteredCrimesTextField.appendText("-- Plaintiff --\n");
        ReadRegisteredCrimesTextField.appendText("First name: " + crimes.get(index).getRapport().getWriter().getFirstName() + "\n");
        ReadRegisteredCrimesTextField.appendText("Last name: " + crimes.get(index).getRapport().getWriter().getLastName() + "\n");
        ReadRegisteredCrimesTextField.appendText("Civic number: " + crimes.get(index).getRapport().getWriter().getCivicNumber() + "\n");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillList();
        getCrime();


    }
}

