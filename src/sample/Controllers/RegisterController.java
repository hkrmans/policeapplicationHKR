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
    Civilian civilianToReg;
    DbConnect dbc;


    @FXML
    private TextField emailTextfield, usernameTextfield, CNTextfield;
    @FXML
    private Label usernameLabel, emailLabel, civicNumberLabel;
    @FXML
    private Button registerButton;

    @FXML
    private void keyReleaseUsername() {
        if (checkUsername()) {
            usernameLabel.setText("Passed");
            registerButton.setDisable(false);
        } else {
            usernameLabel.setText("Username unavailable");
        }
    }

    private boolean checkUsername() {
        boolean check = true;
        ArrayList<Account> accounts = Singleton.getAccountInstance().getAccountList();
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
            registerButton.setDisable(false);
        } else {
            emailLabel.setText("Email unavailable");
        }
    }

    private boolean checkEmail() {
        boolean check = true;
        ArrayList<Account> accounts = Singleton.getAccountInstance().getAccountList();
        for (Account a : accounts) {
            if (a.getEmail().equals(emailTextfield.getText())) {
                check = false;
            }
        }
        return check;
    }

    @FXML
    private void keyReleaseCivicNumber() throws SQLException {
        if (checkCivicNumber()) {
            civicNumberLabel.setText("Passed");
            registerButton.setDisable(false);
        } else {
            civicNumberLabel.setText("Invalid civic number");
        }
    }

    private boolean checkCivicNumber() throws SQLException {
        boolean check = false;
        ArrayList<Civilian> civilians = Singleton.getCivilianInstance().getCivilianList();
        for (Civilian c : civilians) {
            if (c.getCivicNumber().equals(CNTextfield.getText())) {
                civilianToReg = c;
                check = true;
            }
        }
        return check;
    }

    @FXML
    private void registerButtonOnAction() throws SQLException {

        Account a = new Account(civilianToReg, usernameTextfield.getText(), "Jb84raA1??10", emailTextfield.getText());
        {
            try {
                dbc = new DbConnect("policemanagment", "Jb84raA1??10");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        dbc.addAccount(a);
    }


    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "sample.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerButton.setDisable(true);
    }
}
