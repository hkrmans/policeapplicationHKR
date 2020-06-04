package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.DbConnect;
import sample.SceneChanger;
import sample.Models.Civilian;
import sample.Models.Meeting;
import sample.Models.Prisoner;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookMeetingController implements Initializable {
    private ArrayList<Prisoner> prisoners = new ArrayList<>();
    private ArrayList<Meeting> meetings = new ArrayList<>();
    private ArrayList<Civilian> civilians = new ArrayList<>();
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());
    private Prisoner prisoner = null;
    private java.util.Date date = null;
    private ObservableList list = FXCollections.observableList(prisoners);

    @FXML
    private DatePicker DatePicker;

    @FXML
    private TextField BookMeetingPrisonerIdTextField;

    @FXML
    private TextField deleteMeetingTextField;

    @FXML
    private TextArea meetingsTextArea;

    @FXML
    private javafx.scene.control.TableView<Prisoner> TableView;

    @FXML
    private TableColumn<Prisoner, String> FNColumn;

    @FXML
    private TableColumn<Prisoner, String> LNColumn;

    @FXML
    private TableColumn<Prisoner, Integer> CNColumn;

    @FXML
    private TableColumn<Prisoner, Integer> PNColumn;

    @FXML
    void PickButtonOnAction(MouseEvent event) {
        int index = -1;
        index = TableView.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Prisoner information");
        alert.setContentText("Name: " + prisoners.get(index).getFirstName() + " | Last name: " + prisoners.get(index).getLastName()
        + "\nCivic number: " + prisoners.get(index).getCivicNumber() + " | Prisoner ID: " + prisoners.get(index).getPrisonerId());
        alert.showAndWait();
        BookMeetingPrisonerIdTextField.setText(Integer.toString(prisoners.get(index).getPrisonerId()));
    }


    private boolean checkDate(){
        for (Meeting m: meetings) {
            if (m.getPrisoner().getPrisonerId() == Integer.parseInt(BookMeetingPrisonerIdTextField.getText())
                    && String.valueOf(m.getDate()).equals(String.valueOf(DatePicker.getValue()))){
                return false;
            }
        }
        return true;
    }

    @FXML
    private void bookMeetingButtonOnAction() {
        try {
            LocalDate date = DatePicker.getValue();
            if (checkDate()) {
                String prisonerID = BookMeetingPrisonerIdTextField.getText();
                for (Prisoner p : prisoners) {
                    if (p.getPrisonerId() == Integer.parseInt(prisonerID)) {
                        prisoner = p;
                    }
                }
                Meeting meeting = new Meeting(prisoner, LoginController.getLoggedInAccount().getOwner(), Date.valueOf(date), 0);
                dbc.addInformation(meeting);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Booking confirmation");
                alert.setContentText("Your meeting has been booked");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Prisoner already got a meeting that day!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to convert the inserted date");
            alert.showAndWait();
        }
    }

    @FXML
    private void menuButton(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/CivilianMenu.fxml");
    }

    @FXML
    private void deleteMeetingButtonOnAction() {
        int CivilianInputMeetingId = Integer.parseInt(deleteMeetingTextField.getText());
        for (int i = 0; i < meetings.size(); i = i + 1) {
            if (CivilianInputMeetingId == (meetings.get(i).getMeetingID())) {
                dbc.deleteInformation(meetings.get(i));
            }
        }
    }

    @FXML
    private void checkMeetingButtonOnAction() {
        meetingsTextArea.clear();
        String CivilianInputCivicNumber = LoginController.getLoggedInAccount().getOwner().getCivicNumber();
        for (int i = 0; i < meetings.size(); i = i + 1) {
            if (CivilianInputCivicNumber.equals(meetings.get(i).getVisitor().getCivicNumber())) {
                meetingsTextArea.appendText("---- Meetings ---- \n");
                meetingsTextArea.appendText("Meeting ID \n");
                meetingsTextArea.appendText(String.valueOf(meetings.get(i).getMeetingID()) + "\n");
                meetingsTextArea.appendText("Date \n");
                meetingsTextArea.appendText(String.valueOf(meetings.get(i).getDate() + "\n"));
                meetingsTextArea.appendText("Prisoner ID \n");
                meetingsTextArea.appendText(meetings.get(i).getPrisoner().getPrisonerId() + "\n");

            }
        }
    }

    private void fillList() {
        Prisoner prisoner = new Prisoner(null, null, null, 0);
        prisoners.add(prisoner);
        dbc.getInfo(prisoners);
        Meeting meeting = new Meeting(null, null, null, 0);
        meetings.add(meeting);
        dbc.getInfo(meetings);
        Civilian civilian = new Civilian(null, null, null);
        civilians.add(civilian);
        dbc.getInfo(civilians);
        for (int i = 0; i < meetings.size(); i++) {
            for (int j = 0; j < prisoners.size(); j++) {
                if (meetings.get(i).getPrisoner().getPrisonerId() == (prisoners.get(j).getPrisonerId())){
                    meetings.get(i).setPrisoner(prisoners.get(j));
                }
            }
        }
    }

    private void fillTable(){
        try {
            FNColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            LNColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            CNColumn.setCellValueFactory(new PropertyValueFactory<>("civicNumber"));
            PNColumn.setCellValueFactory(new PropertyValueFactory<>("prisonerId"));
            TableView.setItems(list);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to fill the table");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillList();
        fillTable();
    }
}