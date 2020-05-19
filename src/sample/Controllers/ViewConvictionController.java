package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.*;
import sample.Conviction;
import sample.SceneChanger;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewConvictionController implements Initializable {
    private ArrayList<Conviction> convictions;
    private ArrayList<Prisoner> prisoners;
    private Person person;

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
    private void goBackButtonOnAction(ActionEvent event){
        try {
            if (person instanceof Police) {
                SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
            } else {
                SceneChanger.changeScene(event, "fxmlFiles/StandardMenu.fxml");
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }

    }

    @FXML
    void LogOutViewConvictionButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/sample.fxml");

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

       for (int i = 0; i < prisoners.size(); i = i + 1) {
            if (searchByFirstName.equals(prisoners.get(i).getFirstName())) {
               convictionsArea.clear();
               convictionsArea.appendText((" Name | " + prisoners.get(i).getFirstName() + "\n Last name | " + prisoners.get(i).getLastName() + "\n Civic number | " + prisoners.get(i).getCivicNumber() + "\n PrisonerID | " + prisoners.get(i).getPrisonerId() + "\n Release date | " + prisoners.get(i).getReleaseDate()));

            }
        }

        for (int i = 0; i < prisoners.size(); i = i + 1) {
            if (searchBySsn.equals(prisoners.get(i).getCivicNumber())){
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

    /*final String regex = "[2][0][\\d]{2}[-]([0][\\d]|([1][0-2]))[-]([0][1-9]|[1-2][\\d]|[3][0-1])";
    final String regexTwo = "[2][0][\\d]{2}[-]([0][\\d]|([1][0-2]))";
    final String regexThree = "[2][0][\\d]{2}";

        try{
        if (Pattern.matches("[a-zA-Z]+", nameTextField.getText())){

        }else {
            throw new Exception();
        }

        if (Pattern.matches("[0-9]{8}$", ssnTextField.getText())){

        }else{
            throw new Exception();
        }

        if (Pattern.matches(regex, releaseTextField.getText()) || Pattern.matches(regexTwo, releaseTextField.getText()) || Pattern.matches(regexThree, releaseTextField.getText())){

        }else {
            throw new Exception();
        }


    }catch (Exception e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Wrong input or the prisoner doesn't exist");
        alert.setContentText("Please enter the right information again!");
        alert.showAndWait();
    }
}


     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Sec sec = new Sec();
            convictions =  DbConnect.getInstance(sec.decrypter("!)!AY!U!!Q!@b!R!`!`!T#T$")).getConviction();
            prisoners = DbConnect.getInstance(sec.decrypter("!)!AY!U!!Q!@b!R!`!`!T#T$")).getPrisoner();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        /*for (Conviction c : convictions) {
            convictionsArea.appendText(c.getPrisoner() + " | " + c.getSentence() + " | "
                                         + c.getConviction() + "|" + c.getRelease());
        }

         */

    }
}
