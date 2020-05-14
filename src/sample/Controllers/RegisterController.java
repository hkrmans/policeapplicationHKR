package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.IOException;
import java.util.ResourceBundle;


public class RegisterController implements Initializable {
    private Civilian civilianToReg;
    private DbConnect dbc = DbConnect.getInstance();
    ArrayList<Account> accounts;
    ArrayList<Civilian> civilians;

    {
        try {
            civilians = dbc.getCivilians();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            accounts = dbc.getAccount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField emailTextfield, usernameTextfield, CNTextfield;
    @FXML
    private Label usernameLabel, emailLabel, civicNumberLabel;
    @FXML
    private Button registerButton;
    private void setButton(){
        if (checkCivicNumber() && checkUsername() && checkEmail()){
            registerButton.setDisable(false);
        }
    }
    @FXML
    private void keyReleaseUsername() {
        if (checkUsername()) {
            usernameLabel.setText("Passed");
            setButton();
        } else {
            usernameLabel.setText("Username unavailable");
        }
    }

    private boolean checkUsername() {
        boolean check = true;
        for (Account a : accounts) {
            if (a.getUsername().equals(usernameTextfield.getText())) {
                check = false;
            }
        }
        return check;
    }

    @FXML
    private void keyReleaseEmail() {
        if (checkEmail()) {
            emailLabel.setText("Passed");
            setButton();
        } else {
            emailLabel.setText("Email unavailable");
        }
    }

    private boolean checkEmail() {
        boolean check = true;
        for (Account a : accounts) {
            if (a.getEmail().equals(emailTextfield.getText())) {
                check = false;
            }
        }
        return check;
    }

    @FXML
    private void keyReleaseCivicNumber() {
        if (checkCivicNumber()) {
            civicNumberLabel.setText("Passed");
            setButton();
        } else {
            civicNumberLabel.setText("Invalid civic number");
        }
    }

    private boolean checkCivicNumber() {
        boolean check = false;
        for (Civilian c : civilians) {
            if (c.getCivicNumber().equals(CNTextfield.getText())) {
                civilianToReg = c;
                check = true;
            }
        }
        return check;
    }

    @FXML
    private void registerButtonOnAction() {
        Account a = new Account(civilianToReg, usernameTextfield.getText(), "Jb84raA1??10", emailTextfield.getText());
        try {
            dbc.addAccount(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/sample.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerButton.setDisable(true);
    }
}
