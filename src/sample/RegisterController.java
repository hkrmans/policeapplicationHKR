package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static sample.Singleton.getAccountInstance;

public class RegisterController {
    Civilian civilianToReg;
    DbConnect dbc;


    @FXML
    private TextField emailTextfield,usernameTextfield, CNTextfield;
    @FXML
    private Label usernameLabel, emailLabel, civicNumberLabel;

    @FXML
    private void keyReleaseUsername(){
       if (checkUsername()){
           usernameLabel.setText("Passed");
       }else{
           usernameLabel.setText("Username unavailable");
       }

    }
    private boolean checkUsername(){
        boolean check= true;
        ArrayList<Account> accounts = Singleton.getAccountInstance().getAccountList();
        for (Account a: accounts) {
            if (a.getUsername().equals(usernameTextfield.getText())){
                check = false;
            }
        }
        return check;
    }
    @FXML
    private void keyReleaseEmail(){
        if (checkEmail()){
            emailLabel.setText("Passed");
        }else{
            emailLabel.setText("Email unavailable");
        }
    }
    private boolean checkEmail(){
        boolean check= true;
        ArrayList<Account> accounts = Singleton.getAccountInstance().getAccountList();
        for (Account a: accounts) {
            if (a.getEmail().equals(emailTextfield.getText())){
                check = false;
            }
        }
        return check;
    }
    @FXML
    private void keyReleaseCivicNumber() throws SQLException {
        if (checkCivicNumber()){
            civicNumberLabel.setText("Passed");
        }else{
            civicNumberLabel.setText("Invalid civic number");
        }
    }
    private boolean checkCivicNumber() throws SQLException {
        boolean check= false;
        ArrayList<Civilian> civilians = Singleton.getCivilianInstance().getCivilianList();
        for (Civilian c: civilians) {
            if (c.getCivicNumber().equals(CNTextfield.getText())){
                civilianToReg = c;
                check = true;
            }
        }
        return check;
    }

    @FXML
    private void registerButtonOnAction(ActionEvent event) {

        Account a = new Account(civilianToReg,usernameTextfield.getText(),"Jb84raA1??10",emailTextfield.getText());
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
    private void backButtonOnAction(ActionEvent event) {

    }
}
