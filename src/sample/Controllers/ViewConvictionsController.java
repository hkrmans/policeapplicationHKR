package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.*;
import sample.Models.Conviction;
import sample.Models.Person;
import sample.Models.Police;
import sample.Models.Prisoner;
import sample.SceneChanger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewConvictionsController implements Initializable {

    private ArrayList<Conviction> convictions = new ArrayList<>();
    private ArrayList<Prisoner> prisoners = new ArrayList<>();
    private Person person;
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());


    @FXML
    private TextArea convictionsArea;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField ssnTextField;
    @FXML
    private TextField releaseTextField;
    @FXML
    private TextField indexTextField;

    @FXML
    private void goBackMenuButtonOnAction(ActionEvent event) {
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

    @FXML
    void viewMoreInfoButtonOnAction(ActionEvent event) {
        convictionsArea.clear();
        String index = indexTextField.getText();
        for (int i = 0; i < convictions.size(); i = i + 1) {
            if (index.equals(convictions.get(i).getPrisoner().getCivicNumber())) {
                convictionsArea.appendText(("\n Convictions \n" + convictions.get(i).getConviction() + convictions.get(i).getSentence()));
            }
        }
    }

    @FXML
    private void searchButtonOnAction() {
        convictionsArea.clear();
        String searchByFirstName = nameTextField.getText();
        String searchBySsn = ssnTextField.getText();
        String searchByLastName = releaseTextField.getText();
        Prisoner prisoner = new Prisoner(null, null, null, 0, null);
        prisoners.add(prisoner);

        for (int i = 0; i < prisoners.size(); i = i + 1) {
            if (searchByFirstName.equals(prisoners.get(i).getFirstName())) {
                convictionsArea.clear();
                convictionsArea.appendText((" Name | " + prisoners.get(i).getFirstName() + "\n Last name | " + prisoners.get(i).getLastName() + "\n Civic number | " + prisoners.get(i).getCivicNumber() + "\n PrisonerID | " + prisoners.get(i).getPrisonerId() + "\n Release date | " + prisoners.get(i).getReleaseDate()));

            }
        }

        for (int i = 0; i < prisoners.size(); i = i + 1) {
            if (searchBySsn.equals(prisoners.get(i).getCivicNumber())) {
                convictionsArea.clear();
                convictionsArea.appendText((" Name | " + prisoners.get(i).getFirstName() + "\n Last name | " + prisoners.get(i).getLastName() + "\n Civic number | " + prisoners.get(i).getCivicNumber() + "\n PrisonerID | " + prisoners.get(i).getPrisonerId() + "\n Release date | " + prisoners.get(i).getReleaseDate()));
            }
        }

        for (int i = 0; i < prisoners.size(); i = i + 1) {
            if (searchByLastName.equals(prisoners.get(i).getLastName())) {
                convictionsArea.clear();
                convictionsArea.appendText((" Name | " + prisoners.get(i).getFirstName() + "\n Last name | " + prisoners.get(i).getLastName() + "\n Civic number | " + prisoners.get(i).getCivicNumber() + "\n PrisonerID | " + prisoners.get(i).getPrisonerId() + "\n Release date | " + prisoners.get(i).getReleaseDate()));

            }
        }
    }

    private void FillList() {
        try {
            Conviction conviction = new Conviction(null, null, null, null, 0);
            convictions.add(conviction);
            dbc.getInfo(convictions);
            Prisoner prisoner = new Prisoner(null, null, null, 0, null);
            prisoners.add(prisoner);
            dbc.getInfo(prisoners);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FillList();
    }
}
