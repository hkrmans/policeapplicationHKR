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
    @FXML
    TextField username, password;

    @FXML
    private void backButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/sample.fxml");
    }

    private boolean checkAccount() {
        loggedInAccount = null;
        boolean check = false;
        ArrayList<Account> accounts = null;
        try {
            accounts = DbConnect.getInstance().getAccount();
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

    @FXML
    private void LogInButtonOnAction(ActionEvent event) throws IOException {
        if (checkAccount()) {
            if (loggedInAccount.getOwner() instanceof Police) {
                SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
            } else if (loggedInAccount.getOwner() instanceof Civilian) {
                SceneChanger.changeScene(event, "fxmlFiles/StandardMenu.fxml");
            }
        } else {
            System.out.println("Pass and user does not match");
        }
    }
}
