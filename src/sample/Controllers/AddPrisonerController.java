package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sample.DbConnect;
import sample.Models.Prisoner;
import sample.SceneChanger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    void AddPrisonerMenuButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event,"fxmlFiles/PoliceMenu.fxml");

    }



    @FXML
    void addPrisonerButtonOnAction(ActionEvent event) {
        Prisoner prisoner = new Prisoner(addPFirstNameTextField.getText(),addPLastNameTextField.getText(),addPCivicNumberTextField.getText(),0);
        dbc.addInformation(prisoner);
        addPFirstNameTextField.clear();
        addPLastNameTextField.clear();
        addPCivicNumberTextField.clear();

    }
    private void FillList(){
        try {
            Prisoner prisoner = new Prisoner(null,null,null,0);
            prisoners.add(prisoner);
            dbc.getInfo(prisoners);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FillList();

    }
}
