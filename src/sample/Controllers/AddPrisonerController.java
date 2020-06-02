package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.DbConnect;
import sample.Models.Prisoner;
import sample.SceneChanger;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddPrisonerController implements Initializable {
    private ArrayList<Prisoner> prisoners = new ArrayList<>();
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());

    @FXML
    private TextField addPFirstNameTextField;

    @FXML
    private TextField addPLastNameTextField;

    @FXML
    private TextField addPCivicNumberTextField;

    @FXML
   private void menuButton(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
    }

    @FXML
   private void addPrisonerButtonOnAction() {
        final String regexOne = "[a-zA-Z]";
        final String regexTwo = "[0-9]";
        try {
            if (Pattern.matches(regexOne, addPFirstNameTextField.getText())) {
                if (Pattern.matches(regexOne, addPLastNameTextField.getText())) {
                    if (Pattern.matches(regexTwo, addPCivicNumberTextField.getText())) {
                        Prisoner prisoner = new Prisoner(addPFirstNameTextField.getText(), addPLastNameTextField.getText(), addPCivicNumberTextField.getText(), 0);
                        dbc.addInformation(prisoner);
                        addPFirstNameTextField.clear();
                        addPLastNameTextField.clear();
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Success!");
                        alert.setHeaderText("Prisoner has been added");
                        alert.setContentText("The prisoner has been added to the system");
                        alert.showAndWait();
                    } else {
                        throw new Exception();
                    }
                } else {
                    throw new Exception();
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Wrong input");
            alert.setContentText("Please enter a valid input");
            alert.show();
        }
    }

    private void fillList() {
        Prisoner prisoner = new Prisoner(null, null, null, 0);
        prisoners.add(prisoner);
        dbc.getInfo(prisoners);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillList();
    }
}
