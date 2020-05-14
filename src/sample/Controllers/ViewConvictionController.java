package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.*;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
    private void searchButtonOnAction(){
        final String regex = "[2][0][\\d]{2}[-]([0][\\d]|([1][0-2]))[-]([0][1-9]|[1-2][\\d]|[3][0-1])";
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            convictions = Singleton.getConvictionInstance().getConvictionList();
            prisoners = Singleton.getPrisonerInstance().getPrisonerList();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        for (Conviction c : convictions) {
            convictionsArea.appendText(c.getPrisoner() + " | " + c.getSentence() + " | "
                                        + c.getConviction() + "|" + c.getRelease());
        }


    }
}
