package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.DbConnect;
import sample.Models.Conviction;
import sample.Models.Prisoner;
import sample.SceneChanger;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewConvictionsController implements Initializable {

    private ArrayList<Conviction> convictions = new ArrayList<>();
    private ArrayList<Prisoner> prisoners = new ArrayList<>();
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());

    @FXML
    private TextArea convictionsArea;
    @FXML
    private TextField nameTextField, ssnTextField, releaseTextField, indexTextField;

    @FXML
    private void menuButton(ActionEvent event) {
        if (LoginController.isPolice()) {
            SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
        } else {
            SceneChanger.changeScene(event, "fxmlFiles/CivilianMenu.fxml");
        }
    }

    @FXML
    private void viewMoreInfoButtonOnAction() {
        try {
            convictionsArea.clear();
            String index = indexTextField.getText();
            for (int i = 0; i < convictions.size(); i = i + 1) {
                if (index.equals(convictions.get(i).getPrisoner().getCivicNumber())) {
                    convictionsArea.appendText(("\n Convictions \n" + convictions.get(i).getConviction() + convictions.get(i).getSentence()));
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to retrieve more information");
            alert.showAndWait();
        }
    }

    private void searchByFirstName() {
        String searchByFirstName = nameTextField.getText();
        for (int i = 0; i < prisoners.size(); i = i + 1) {
            if (searchByFirstName.equals(prisoners.get(i).getFirstName())) {
                convictionsArea.clear();
                convictionsArea.appendText((" Name | " + prisoners.get(i).getFirstName() + "\n Last name | " + prisoners.get(i).getLastName() + "\n Civic number | " + prisoners.get(i).getCivicNumber() + "\n PrisonerID | " + prisoners.get(i).getPrisonerId() + "\n Release date | "));

            }
        }
    }

    private void searchBySSN() {
        String searchBySsn = ssnTextField.getText();
        for (int i = 0; i < prisoners.size(); i = i + 1) {
            if (searchBySsn.equals(prisoners.get(i).getCivicNumber())) {
                convictionsArea.clear();
                convictionsArea.appendText((" Name | " + prisoners.get(i).getFirstName() + "\n Last name | " + prisoners.get(i).getLastName() + "\n Civic number | " + prisoners.get(i).getCivicNumber() + "\n PrisonerID | " + prisoners.get(i).getPrisonerId() + "\n Release date | "));
            }
        }
    }

    private void searchByLastName() {
        String searchByLastName = releaseTextField.getText();
        for (int i = 0; i < prisoners.size(); i = i + 1) {
            if (searchByLastName.equals(prisoners.get(i).getLastName())) {
                convictionsArea.clear();
                convictionsArea.appendText((" Name | " + prisoners.get(i).getFirstName() + "\n Last name | " + prisoners.get(i).getLastName() + "\n Civic number | " + prisoners.get(i).getCivicNumber() + "\n PrisonerID | " + prisoners.get(i).getPrisonerId() + "\n Release date | "));

            }
        }
    }

    @FXML
    private void searchButtonOnAction() {
        convictionsArea.clear();
        try {
            searchByFirstName();
            searchByLastName();
            searchBySSN();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed the search, try again");
            alert.showAndWait();
        }
    }

    private void FillList() {
        Conviction conviction = new Conviction(null, null, null, null, 0);
        convictions.add(conviction);
        dbc.getInfo(convictions);
        Prisoner prisoner = new Prisoner(null, null, null, 0);
        prisoners.add(prisoner);
        dbc.getInfo(prisoners);

        for (int i = 0; i < convictions.size(); i++) {
            for (int j = 0; j < prisoners.size(); j++) {
                if (convictions.get(i).getPrisoner().getPrisonerId() == (prisoners.get(j).getPrisonerId())){
                    convictions.get(i).setPrisoner(prisoners.get(j));
                }
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FillList();
    }
}
