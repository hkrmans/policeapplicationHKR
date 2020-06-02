package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.DbConnect;
import sample.SceneChanger;
import sample.Security;
import sample.Models.Account;
import sample.Models.Police;

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
    private void backButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/FirstPage.fxml");
    }

    @FXML
    private void exitLoginButtonOnAction() {
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Wrong password");
            alert.showAndWait();
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
            ex.printStackTrace();
        }
        return check;
    }

    private void checkIfPolice() {
        try {
            Police police = new Police(null, null, null, null);
            polices.add(police);
            DbConnect.getInstance(password.getText()).getInfo(polices);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Connection to database failed");
            alert.showAndWait();
        }

        for (Police p : polices) {
            if (p.getCivicNumber().equals(loggedInAccount.getOwner().getCivicNumber())) {
                isPolice = true;
                break;
            }
            isPolice=false;
        }
    }

    private void changeScene(ActionEvent event) {
        if (isPolice) {
            SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
        } else {
            SceneChanger.changeScene(event, "fxmlFiles/CivilianMenu.fxml");
        }
    }

    @FXML
    private void LogInButtonOnAction(ActionEvent event) {
        if (checkAccount()) {
            checkIfPolice();
            changeScene(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Password and Username does not match");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showPassword.setVisible(false);
    }
}

