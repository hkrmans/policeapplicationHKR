package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.DbConnect;
import sample.Models.Prisoner;
import sample.SceneChanger;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class HandlePrisonersController implements Initializable {
    private Prisoner prisoner;
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());
    private ArrayList<Prisoner> prisoners = new ArrayList<>();
    private ObservableList list = FXCollections.observableList(prisoners);

    @FXML
    private TextField ID, firstname, lastname;
    @FXML
    private TableView<Prisoner> tableView;
    @FXML
    private TableColumn<Prisoner, String> FN;
    @FXML
    private TableColumn<Prisoner, String> LN;
    @FXML
    private TableColumn<Prisoner, String> CN;
    @FXML
    private TableColumn<Prisoner, Integer> PID;

    @FXML
    private void update(ActionEvent event) {
        if (Pattern.matches("[a-zA-Z]", firstname.getText())) {
            if (Pattern.matches("[a-zA-Z]", lastname.getText())) {
                prisoner.setFirstName(firstname.getText());
                prisoner.setLastName(lastname.getText());
                dbc.updateInfo(prisoner);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Update successful!");
                alert.showAndWait();

                menuButton(event);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Firstname and Lastname may only contain letters!");
            alert.showAndWait();
        }

    }

    @FXML
    private void reset() {
        setText();
    }

    @FXML
    private void enterID() {
        if (searchForPrisoner()) {
            setText();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("There is no prisoner with that ID!");
            alert.showAndWait();
        }
        ID.clear();
    }

    private void setText() {
        firstname.setText(prisoner.getFirstName());
        lastname.setText(prisoner.getLastName());
    }

    private void fillPrisonerList() {
        Prisoner prisoner = new Prisoner(null, null, null, 0);
        prisoners.add(prisoner);
        dbc.getInfo(prisoners);
    }

    private boolean searchForPrisoner() {
        for (Prisoner p : prisoners) {
            if (p.getPrisonerId() == Integer.parseInt(ID.getText())) {
                prisoner = p;
                return true;
            }
        }
        return false;
    }

    @FXML
    private void menuButton(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
    }

    private void fillTable() {
        try {
            FN.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            LN.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            CN.setCellValueFactory(new PropertyValueFactory<>("civicNumber"));
            PID.setCellValueFactory(new PropertyValueFactory<>("prisonerId"));
            tableView.setItems(list);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to fill the table");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillPrisonerList();
        fillTable();
    }
}
