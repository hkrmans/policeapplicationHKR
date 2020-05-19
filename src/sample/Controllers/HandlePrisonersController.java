package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import sample.DbConnect;
import sample.Prisoner;
import sample.SceneChanger;
import sample.Sec;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class HandlePrisonersController {
    Prisoner prisoner;
    Sec sec = new Sec();

    @FXML
    private TextField ID, firstname, lastname, CN, RD;
    @FXML
    private Label firstnameLabel, lastnameLabel, CNLabel, RDLabel;

    @FXML
    private void changeCN(ActionEvent event) {

    }

    @FXML
    private void changeFirstname(ActionEvent event) {
        
    }

    @FXML
    private void changeLastname(ActionEvent event) {

    }

    @FXML
    private void changeRD(ActionEvent event) {

    }


    @FXML
    private void enterID(ActionEvent event) {
        if (searchForPrisoner()) {
            setLabels();
        }else{
            System.out.println("no pris with that id");
        }
    }

    private void setLabels() {
        firstnameLabel.setText(prisoner.getFirstName());
        lastnameLabel.setText(prisoner.getLastName());
        CNLabel.setText(prisoner.getCivicNumber());
        RDLabel.setText(prisoner.getReleaseDate().toString());
    }

    private boolean searchForPrisoner() {
        boolean check = false;
        ArrayList<Prisoner> prisoners = null;
        try {
            prisoners = DbConnect.getInstance(sec.decrypter("!)!AY!U!!Q!@b!R!`!`!T#T$")).getPrisoner();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Prisoner p : prisoners) {
            if (p.getPrisonerId() == Integer.parseInt(ID.getText())) {
                prisoner = p;
                check = true;
            }
        }
        return check;
    }

    @FXML
    private void GoBackHandlePrisonerButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");

    }
}
