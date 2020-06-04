package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.DbConnect;
import sample.Models.WantedCriminal;
import sample.SceneChanger;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddWantedCriminalController implements Initializable {
    private ArrayList<WantedCriminal> wantedCriminals = new ArrayList<>();
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());

    @FXML
    private TextField firstNameTextField, lastNameTextField, civicTextField, rankingTextField, bountyTextField;
    @FXML
    private TextArea wantedTextArea;

    @FXML
    private void goBackButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/ReadReports.fxml");
    }

    @FXML
    private void addCriminalButtonOnAction() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String civicNumber = civicTextField.getText();
        String ranking = rankingTextField.getText();
        String bounty = bountyTextField.getText();
        final String regexOne = "[a-zA-Z]+";
        final String regexTwo = "[0-9]";
        try {
            if (Pattern.matches(regexOne, firstName)) {
                if (Pattern.matches(regexOne, lastName)) {
                    if (Pattern.matches(regexTwo, civicNumber)) {
                        if (Pattern.matches(regexTwo, ranking)) {
                            if (Pattern.matches(regexTwo, bounty)) {
                                WantedCriminal wantedCriminal = new WantedCriminal(firstName, lastName, civicNumber, Integer.parseInt(ranking), Integer.parseInt(bounty), 0);
                                dbc.addInformation(wantedCriminal);
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Success");
                                alert.setHeaderText("Wanted criminal added");
                                alert.setContentText("Wanted criminal has been added to the database!");
                                alert.showAndWait();
                                firstNameTextField.clear();
                                lastNameTextField.clear();
                                civicTextField.clear();
                                rankingTextField.clear();
                                bountyTextField.clear();
                            } else {
                                throw new Exception();
                            }
                        } else {
                            throw new Exception();
                        }
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
            alert.setHeaderText("You have not entered a valid input");
            alert.setContentText("Please look over you inputs and try again!");
            alert.showAndWait();
        }
    }

    private void fillList() {
        wantedCriminals.add(new WantedCriminal(null, null, null, 0, 0, 0));
        dbc.getInfo(wantedCriminals);
    }

    private void fillScene() {
        try {
            for (WantedCriminal w : wantedCriminals) {
                wantedTextArea.appendText(w.getFirstName() + " | " + w.getLastName() + " | " + w.getCivicNumber()
                        + w.getRanking() + " | " + w.getBounty() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillList();
        fillScene();
    }
}
