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
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class HandlePrisonersController {
    private Prisoner prisoner;
    private Sec sec = new Sec();
    private DbConnect dbc = DbConnect.getInstance(sec.decrypter("!)!AY!U!!Q!@b!R!`!`!T#T$"));

    @FXML
    private TextField ID, firstname, lastname, CN, RD;
    @FXML
    private Label firstnameLabel, lastnameLabel, CNLabel, RDLabel;

    @FXML
    private void changeCN(ActionEvent event) {
        //dbc.changePrisonerCivicNumber(CN.getText(), prisoner.getPrisonerId());
    }

    @FXML
    private void changeFirstname(ActionEvent event) {
        //dbc.changePrisonerFirstname(firstname.getText(), prisoner.getPrisonerId());
    }

    @FXML
    private void changeLastname(ActionEvent event) {
        //dbc.changePrisonerLastname(lastname.getText(), prisoner.getPrisonerId());
    }

    @FXML
    private void changeRD(ActionEvent event) {
        Date date = null;
        try {
            date = Date.valueOf(RD.getText());
            //dbc.changePrisonerReleaseDate(date, prisoner.getPrisonerId());
        } catch (Exception ex) {

        }
    }


    @FXML
    private void enterID(ActionEvent event) {
        if (searchForPrisoner()) {
            setLabels();
        } else {
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
            Prisoner prisoner = new Prisoner(null,null,null,0,null);
            prisoners.add(prisoner);
            DbConnect.getInstance(sec.decrypter("!)!AY!U!!Q!@b!R!`!`!T#T$")).getInfo(prisoners);
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
