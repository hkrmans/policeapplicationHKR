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
import sample.Models.WantedCriminal;
import sample.SceneChanger;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HandlePrisonersController implements Initializable {
    private Prisoner prisoner;
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());
    private ArrayList<Prisoner> prisoners = new ArrayList<>();
    private ObservableList list= FXCollections.observableList(prisoners);

    @FXML
    private TextField ID, firstname, lastname;
    @FXML
    private TableView<Prisoner> tableView;
    @FXML
    private TableColumn<Prisoner,String> FN;
    @FXML
    private TableColumn<Prisoner, String> LN;
    @FXML
    private TableColumn<Prisoner, String> CN;
    @FXML
    private TableColumn<Prisoner, Integer> PID;

    @FXML
    private void update(ActionEvent event) throws IOException {
        prisoner.setFirstName(firstname.getText());
        prisoner.setLastName(lastname.getText());
        dbc.updateInfo(prisoner);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Update successful!");
        alert.showAndWait();
        MenuButton(event);
    }

    @FXML
    private void reset(ActionEvent event) {
        setText();
    }

    @FXML
    private void enterID(ActionEvent event) {
        if (searchForPrisoner()) {
            setText();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("There is no prisoner with that id.");
            alert.showAndWait();
        }
        ID.clear();
    }

    private void setText() {
        firstname.setText(prisoner.getFirstName());
        lastname.setText(prisoner.getLastName());
    }

    private void fillPrisonerList(){
        try {
            Prisoner prisoner = new Prisoner(null,null,null,0);
            prisoners.add(prisoner);
            dbc.getInfo(prisoners);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private boolean searchForPrisoner() {
        boolean check = false;

        for (Prisoner p : prisoners) {
            if (p.getPrisonerId() == Integer.parseInt(ID.getText())) {
                prisoner = p;
                check = true;
            }
        }
        return check;
    }

    @FXML
    private void MenuButton(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
    }
    private void fillTable(){
        FN.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        LN.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        CN.setCellValueFactory(new PropertyValueFactory<>("civicNumber"));
        PID.setCellValueFactory(new PropertyValueFactory<>("prisonerId"));
        tableView.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillPrisonerList();
        fillTable();
    }
}
