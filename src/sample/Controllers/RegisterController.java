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
import java.nio.file.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;


public class RegisterController implements Initializable {
    private String password = null;
    private Person personToReg;
    private Sec sec = new Sec();
    private DbConnect dbc = DbConnect.getInstance(sec.decrypter("!)!AY!U!!Q!@b!R!`!`!T#T$"));
    ArrayList<Account> accounts;
    ArrayList<Civilian> civilians;
    ArrayList<Police> polices;

    {
        try {
            Police police = new Police(null,null,null,null);
            polices.add(police);
            polices = dbc.getInfo(polices);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Civilian civilian = new Civilian(null,null,null);
        try {
            civilians.add(civilian);
            civilians = dbc.getInfo(civilians);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Account account = new Account(civilian,null,null,null);
            accounts.add(account);
            accounts = dbc.getInfo(accounts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private TextField emailTextfield, usernameTextfield, CNTextfield;
    @FXML
    private Label usernameLabel, emailLabel, civicNumberLabel;
    @FXML
    private Button registerButton;

    private void setButton() {
        if (checkCivicNumber() && checkUsername() && checkEmail()) {
            registerButton.setDisable(false);
        } else {
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
        for (Police p : polices) {
            if (p.getCivicNumber().equals(CNTextfield.getText())) {
                personToReg = p;
                check = true;
            }
        }
        return check;
    }

    private void removeAndGetFirstPassword() {
        Path path = Paths.get("src/pass.txt");
        if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            try {
                List<String> lines = Files.readAllLines(path);
                password = lines.get(0);

                ArrayList<String> lines2 = new ArrayList<>();
                for (int i = 1; i < lines.size(); i++) {
                    lines2.add(lines.get(i));
                }
                Files.write(path, lines2);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void registerButtonOnAction(ActionEvent event) {
        Account a = new Account(personToReg, usernameTextfield.getText(), "Jb84raA1??10", emailTextfield.getText());
        try {
            dbc.addInformation(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        removeAndGetFirstPassword();
        if (password != null) {
            System.out.println(a.getOwner().getCivicNumber());
            System.out.println(a.getOwner().getFirstName());
            System.out.println(a.getOwner().getLastName());
            System.out.println(a.getUsername());
            System.out.println(a.getEmail());
            System.out.println(a.getPassword());
 //           DbConnect.addInformation(a);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmed");
            alert.setContentText("You are now registered");
            alert.showAndWait();
        } else {
            System.out.println("Error");
        }
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
