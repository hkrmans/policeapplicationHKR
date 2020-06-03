package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.DbConnect;
import sample.Models.Prisoner;
import sample.SceneChanger;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewPrisonersController implements Initializable {
    private ArrayList<Prisoner> prisoners = new ArrayList<>();
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());
    private ObservableList list = FXCollections.observableList(prisoners);

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
    private void menuButton(ActionEvent event) {
        if (LoginController.isPolice()) {
            SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
        } else {
            SceneChanger.changeScene(event, "fxmlFiles/CivilianMenu.fxml");
        }
    }

    private void fillPrisonerList() {
        Prisoner prisoner = new Prisoner(null, null, null, 0);
        prisoners.add(prisoner);
        dbc.getInfo(prisoners);
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
