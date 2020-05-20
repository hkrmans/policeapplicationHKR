package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class LoginController {
    private static Account loggedInAccount = null;
    private Sec sec = new Sec();
    private ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<Police> polices = new ArrayList<>();
    @FXML
    private TextField username, password;

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/sample.fxml");
    }


    private boolean checkAccount() {
        loggedInAccount = null;
        boolean check = false;
        try {
            Account account = new Account(null, null, null, null);
            accounts.add(account);
            DbConnect.getInstance(sec.decrypter(password.getText())).getInfo(accounts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            for (Account a : accounts) {
                if (a.getUsername().equalsIgnoreCase(username.getText()) &&
                        a.getPassword().equals(sec.hashPassword(password.getText()))) {
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
                DbConnect.getInstance(sec.decrypter(password.getText())).getInfo(polices);
            } catch (Exception e) {
                e.printStackTrace();
            }

            boolean checkPolice = false;

            for (Police p : polices) {
                if (p.getCivicNumber().equals(loggedInAccount.getOwner().getCivicNumber())) {
                    checkPolice = true;
                    break;
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

