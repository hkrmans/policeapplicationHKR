package sample.Controllers;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.DbConnect;
import sample.SceneChanger;
import sample.Models.WantedCriminal;

import javax.swing.text.Document;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewMostWantedController implements Initializable {
    private ArrayList<WantedCriminal> wantedCriminals = new ArrayList<>();
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());


    @FXML
    private TextArea showWanteds;
    @FXML
    private TableView<WantedCriminal> tableView;
    @FXML
    private TableColumn<WantedCriminal,String> FN;

    @FXML
    private TableColumn<WantedCriminal, String> LN;

    @FXML
    private TableColumn<WantedCriminal, String> CV;

    @FXML
    private TableColumn<WantedCriminal, Integer> RA;

    @FXML
    private TableColumn<WantedCriminal, Integer> BO;


    ObservableList list= FXCollections.observableList(wantedCriminals);

    public ViewMostWantedController() throws FileNotFoundException {
    }

    @FXML
    void GoBackMostWantedButtonOnAction(ActionEvent event) {
        try {
            if (LoginController.isPolice()) {
                SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
            } else {
                SceneChanger.changeScene(event, "fxmlFiles/CivilianMenu.fxml");
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Scenefail");
            alert.setContentText("Failed to change scene!");
            alert.showAndWait();
        }
    }

    private void fillWantedList() {
        try {
            WantedCriminal wantedCriminal = new WantedCriminal(null, null, null, 0, 0, 0);
            wantedCriminals.add(wantedCriminal);
            dbc.getInfo(wantedCriminals);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showWanteds() {
        for (WantedCriminal wc : wantedCriminals) {
            showWanteds.appendText(wc.getFirstName() + " | " + wc.getLastName() + " | CN:" + wc.getCivicNumber()
                    + " | Bounty:" + wc.getBounty() + " | Ranking:" + wc.getRanking() + "\n");
        }
    }

    @FXML
    void saveToPdfButtonOnAction(ActionEvent event) {


    }




    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillWantedList();
        showWanteds();
        FN.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        LN.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        CV.setCellValueFactory(new PropertyValueFactory<>("civicNumber"));
        RA.setCellValueFactory(new PropertyValueFactory<>("ranking"));
        BO.setCellValueFactory(new PropertyValueFactory<>("bounty"));
        tableView.setItems(list);


    }
}
