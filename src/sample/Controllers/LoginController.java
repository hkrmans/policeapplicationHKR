package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.*;
import sample.Models.Account;
import sample.Models.Police;
import sample.Models.Sorter;
import sample.Models.WantedCriminal;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private static Account loggedInAccount = null;
    private Security security = new Security();
    private ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<Police> polices = new ArrayList<>();
    private static boolean isPolice = false;

    public static boolean isPolice() {
        return isPolice;
    }

    @FXML
    private TextField username, password, showPassword;

    public static Account getLoggedInAccount() {
        return loggedInAccount;
    }

    @FXML
    private void mouseEnter() {
        showPassword.setText(password.getText());
        showPassword.setVisible(true);
    }

    @FXML
    private void mouseExit() {
        showPassword.setVisible(false);
    }

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/FirstPage.fxml");
    }

    @FXML
    void exitLoginButtonOnAction(ActionEvent event) {
        System.exit(0);

    }

    private boolean checkAccount() {
        loggedInAccount = null;
        boolean check = false;
        try {
            Account account = new Account(null, null, null, null);
            accounts.add(account);
            DbConnect.getInstance(password.getText()).getInfo(accounts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            for (Account a : accounts) {
                if (a.getUsername().equalsIgnoreCase(username.getText()) &&
                        a.getPassword().equals(security.hashPassword(password.getText()))) {
                    loggedInAccount = a;
                    check = true;
                    break;
                }
            }
        } catch (NullPointerException | NoSuchAlgorithmException ex) {

        }
        return check;

    }


    @FXML
    private void LogInButtonOnAction(ActionEvent event) throws IOException {
        if (checkAccount()) {
            try {
                Police police = new Police(null, null, null, null);
                polices.add(police);
                DbConnect.getInstance(password.getText()).getInfo(polices);
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (Police p : polices) {
                if (p.getCivicNumber().equals(loggedInAccount.getOwner().getCivicNumber())) {
                    isPolice = true;
                    break;
                }
            }

            if (isPolice) {
                SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
            } else {
                SceneChanger.changeScene(event, "fxmlFiles/CivilianMenu.fxml");
            }
        } else {
            System.out.println("Pass and user does not match");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showPassword.setVisible(false);
    }
}

