package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private ArrayList<Prisoner> prisoners = new ArrayList<>();

    @FXML
    private TextField ID, firstname, lastname, RD;
    @FXML
    private Label firstnameLabel, lastnameLabel, RDLabel;

    @FXML
    private void changeFirstname(ActionEvent event) {
        Prisoner p = prisoner;
        p.setFirstName(firstname.getText());
        dbc.updateInfo(p);
        setLabels();
    }

    @FXML
    private void changeLastname(ActionEvent event) {
        Prisoner p = prisoner;
        p.setLastName(lastname.getText());
        dbc.updateInfo(p);
        setLabels();
    }

    @FXML
    private void changeRD(ActionEvent event) {

        setLabels();
    }


    @FXML
    private void enterID(ActionEvent event) {
        if (searchForPrisoner()) {
            setLabels();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("There is no prisoner with that id.");
            alert.showAndWait();
        }
    }

    private void setLabels() {
        firstnameLabel.setText(prisoner.getFirstName());
        lastnameLabel.setText(prisoner.getLastName());
     //   RDLabel.setText(prisoner.getReleaseDate().toString());
    }

    private boolean searchForPrisoner() {
        boolean check = false;

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
