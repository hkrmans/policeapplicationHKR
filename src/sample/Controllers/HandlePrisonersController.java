package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import sample.DbConnect;
import sample.Models.Prisoner;
import sample.SceneChanger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class HandlePrisonersController {
    private Prisoner prisoner;
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());
    private ArrayList<Prisoner> prisoners = new ArrayList<>();
    private Alert alert = new Alert(Alert.AlertType.ERROR);
    private Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);

    @FXML
    private TextField ID, firstname, lastname, RD;
    @FXML
    private Label firstnameLabel, lastnameLabel, RDLabel;

    @FXML
    private void changeFirstname(ActionEvent event) {
        Prisoner p = prisoner;
        try {
            if (Pattern.matches("[a-zA-Z]", firstname.getText())) {
                p.setFirstName(firstname.getText());
                dbc.updateInfo(p);
                setLabels();
                confirm.setTitle("Success");
                confirm.setContentText("First name has been changed for the chosen prisoner");
                confirm.showAndWait();
            } else {
                throw new Exception();
            }
        }catch (Exception e){
            alert.setTitle("ERROR");
            alert.setHeaderText("Wrong input");
            alert.setContentText("Please enter a valid input");
            alert.showAndWait();
        }
    }

    @FXML
    private void changeLastname(ActionEvent event) {
        Prisoner p = prisoner;
        try {
            if (Pattern.matches("[a-zA-Z]", lastname.getText())) {
                p.setLastName(lastname.getText());
                dbc.updateInfo(p);
                setLabels();
                confirm.setTitle("Success");
                confirm.setContentText("Last name has been changed for the chosen prisoner");
                confirm.showAndWait();
            }else {
                throw new Exception();
            }
        }catch (Exception ex){
            alert.setTitle("ERROR");
            alert.setHeaderText("Wrong input");
            alert.setContentText("Please enter a valid input");
            alert.showAndWait();
        }
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

    private void fillPrisonerList(){
        try {
            Prisoner prisoner = new Prisoner(null,null,null,0,null);
            prisoners.add(prisoner);
            dbc.getInfo(prisoners);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private boolean searchForPrisoner() {
        fillPrisonerList();
        boolean check = false;

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
