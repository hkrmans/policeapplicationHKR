package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginController {
    private static Account loggedInAccount = null;
    private Sec sec = new Sec();
    @FXML
    private TextField username, password;

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/sample.fxml");
    }


    private boolean checkAccount() {
        loggedInAccount = null;
        boolean check = false;
        ArrayList<Account> accounts = null;
        accounts = DbConnect.getInstance(sec.decrypter(password.getText())).getAccount();
        try {
            for (Account a : accounts) {
                if (a.getUsername().equalsIgnoreCase(username.getText()) &&
                        a.getPassword().equals(sec.hashPassword(password.getText()))) {
                    loggedInAccount = a;
                    check = true;
                }
            }
        } catch (NullPointerException | NoSuchAlgorithmException ex) {

        }
        return check;

    }


    @FXML
    private void LogInButtonOnAction(ActionEvent event) throws IOException {
        ArrayList<Police> police = null;
        if (checkAccount()) {
            police = DbConnect.getInstance(sec.decrypter(password.getText())).getPolice();
            boolean checkPolice = false;

            for (Police p : police) {
                if (p.getCivicNumber().equals(loggedInAccount.getOwner().getCivicNumber())) {
                    checkPolice = true;
                }
            }
            if (checkPolice) {
                SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
            } else {
                SceneChanger.changeScene(event, "fxmlFiles/StandardMenu.fxml");
            }
        } else {
            System.out.println("Pass and user does not match");
        }
    }
}

