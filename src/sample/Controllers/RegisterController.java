package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    private Person personToReg;
    private DbConnect dbc = DbConnect.getInstance();
    ArrayList<Account> accounts;
    ArrayList<Civilian> civilians;
    ArrayList<Police> polices;

    {
        try {
            polices = dbc.getPolice();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        }else{
            registerButton.setDisable(true);
        }
    }
    @FXML
    private void keyReleaseUsername() {
        if (checkUsername()) {
            usernameLabel.setText("Passed");
        } else {
            usernameLabel.setText("Username unavailable");
        }
        setButton();
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
        } else {
            emailLabel.setText("Email unavailable");
        }
        setButton();
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
        } else {
            civicNumberLabel.setText("Invalid civic number");
        }
        setButton();
    }

    private boolean checkCivicNumber() {
        boolean check = false;
        for (Civilian c : civilians) {
            if (c.getCivicNumber().equals(CNTextfield.getText())) {
                personToReg = c;
                check = true;
            }
        }
        for (Police p: polices) {
            if (p.getCivicNumber().equals(CNTextfield.getText())){
                personToReg = p;
                check = true;
            }
        }
        return check;
    }

    @FXML
    private void registerButtonOnAction(ActionEvent event) {
        Account a = new Account(personToReg, usernameTextfield.getText(), "Jb84raA1??10", emailTextfield.getText());
        try {
            dbc.addAccount(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmed");
        alert.setContentText("You are now registered");
        alert.showAndWait();

        try {
            backButtonOnAction(event);
        } catch (IOException e) {
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
