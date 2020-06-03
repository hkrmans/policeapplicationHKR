package sample.Controllers;

import com.itextpdf.text.DocumentException;
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


import sample.PDF;
import sample.SceneChanger;
import sample.Models.WantedCriminal;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewMostWantedController implements Initializable {
    private ArrayList<WantedCriminal> wantedCriminals = new ArrayList<>();
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());
    private ObservableList list = FXCollections.observableList(wantedCriminals);

    @FXML
    private TableView<WantedCriminal> tableView;
    @FXML
    private TableColumn<WantedCriminal, String> FN;

    @FXML
    private TableColumn<WantedCriminal, String> LN;

    @FXML
    private TableColumn<WantedCriminal, String> CV;

    @FXML
    private TableColumn<WantedCriminal, Integer> RA;

    @FXML
    private TableColumn<WantedCriminal, Integer> BO;

    @FXML
    private void menuButton(ActionEvent event) {
        if (LoginController.isPolice()) {
            SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
        } else {
            SceneChanger.changeScene(event, "fxmlFiles/CivilianMenu.fxml");
        }
    }

    private void fillWantedList() {
        WantedCriminal wantedCriminal = new WantedCriminal(null, null, null, 0, 0, 0);
        wantedCriminals.add(wantedCriminal);
        dbc.getInfo(wantedCriminals);
    }

    @FXML
    private void saveToPdfButtonOnAction() {
        PDF p = new PDF();
        try {
            p.SaveToPdf(wantedCriminals);
        } catch (FileNotFoundException | DocumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to save as PDF, try again");
            alert.showAndWait();
        }
    }

    private void fillTable() {
        try {
            FN.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            LN.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            CV.setCellValueFactory(new PropertyValueFactory<>("civicNumber"));
            RA.setCellValueFactory(new PropertyValueFactory<>("ranking"));
            BO.setCellValueFactory(new PropertyValueFactory<>("bounty"));
            tableView.setItems(list);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to fill the table");
            alert.showAndWait();
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillWantedList();
        fillTable();
    }
}
