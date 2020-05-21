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


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddWantedCriminalController implements Initializable {
    private ArrayList<WantedCriminal> wantedCriminals = new ArrayList<>();
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());

    @FXML
    private TextField firstNameTextField, lastNameTextField, civicTextField, rankingTextField, bountyTextField;
    @FXML
    private TextArea wantedTextArea;

    @FXML
    private void goBackButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/ReadRegisteredCrimes.fxml");
    }

    @FXML
    private void logOutButtonOnAction(ActionEvent event) throws IOException{
        SceneChanger.changeScene(event, "fxmlFiles/FirstPage.fxml");
    }

    @FXML
    private void addCriminalButtonOnAction(ActionEvent event){
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String civicNumber = civicTextField.getText();
        String ranking = rankingTextField.getText();
        String bounty = bountyTextField.getText();
        try {
            WantedCriminal wantedCriminal = new WantedCriminal(firstName, lastName, civicNumber, Integer.parseInt(ranking), Integer.parseInt(bounty), 0);
            dbc.addInformation(wantedCriminal);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("You have not entered a valid input");
            alert.setContentText("Please look over you inputs and try again!");
            alert.showAndWait();
        }
    }

    private void fillList(){
        try{
            wantedCriminals.add(new WantedCriminal(null,null,null,0,0,0));
            dbc.getInfo(wantedCriminals);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void fillScene(){
        try{
            for (WantedCriminal w : wantedCriminals){
                wantedTextArea.appendText(w.getFirstName() + " | " + w.getLastName() + " | " + w.getCivicNumber()
                                          + w.getRanking() + " | " + w.getBounty());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillList();
        fillScene();
    }
}
