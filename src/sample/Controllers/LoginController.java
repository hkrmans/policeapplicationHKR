package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginController {
    private static Account loggedInAccount = null;
    Sec sec = new Sec();
    @FXML
    TextField username, password;

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/sample.fxml");
    }
    @FXML
    private void LogInButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event,"fxmlFiles/PoliceMenu.fxml");
    }

    private boolean checkAccount() {
        loggedInAccount = null;
        boolean check = false;
        ArrayList<Account> accounts = null;
        try {
            accounts = DbConnect.getInstance(sec.decrypter("!)!AY!U!!Q!@b!R!`!`!T#T$")).getAccount();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Account a : accounts) {
            if (a.getUsername().equalsIgnoreCase(username.getText()) &&
                    a.getPassword().equals(password.getText())) {
                loggedInAccount = a;
                check = true;
            }
        }
        return check;

    }
}
