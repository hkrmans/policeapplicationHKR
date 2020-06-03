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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private DatePicker DatesPicker;

    @FXML
    private TextField BookMeetingCivicNumberTextField;

    @FXML
    private TextField BookMeetingDateTextField;

    @FXML
    private TextField AvailableTextField;

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

    /*@FXML
    void datePickerButtonOnAction(ActionEvent event) {
        LocalDate today = LocalDate.now();
        LocalDate meetingdate = showDatesPicker.getValue();
        for (int i = 0; i <prisoners.size(); i = i + 1) {
            for (int j = 0; j < meetings.size(); j = j + 1)
                if (meetingdate.equals(meetings.get(i).getPrisoner().getCivicNumber() == String.valueOf(meetings.get(j).getDate()))) {
                    if (meetings.get(i).getPrisoner().getCivicNumber() == String.valueOf(prisoners.get(j).getPrisonerId())) {
                        System.out.println("Same date");

                    }
                }
        }
    }

     */


    @FXML
    void PickButtonOnAction(MouseEvent event) {
        int index = -1;
        index = TableView.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        BookMeetingCivicNumberTextField.setText(Integer.toString(prisoners.get(index).getPrisonerId()));
    }

    @FXML
    void MouseDateButtonOnAction(MouseEvent event) {

    }

    private boolean checkDate(){
        for (Meeting m: meetings) {
            if (m.getPrisoner().getPrisonerId() == Integer.parseInt(BookMeetingCivicNumberTextField.getText())
                    && m.getDate().equals(BookMeetingDateTextField.getText())){
                return false;
            }
        }
        return true;
    }

    @FXML
    private void bookMeetingButtonOnAction() {
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(BookMeetingDateTextField.getText());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            if (checkDate()) {
                String prisonerID = BookMeetingCivicNumberTextField.getText();
                for (Prisoner p : prisoners) {
                    if (p.getPrisonerId() == Integer.parseInt(prisonerID)) {
                        prisoner = p;
                    }
                }
                Meeting meeting = new Meeting(prisoner, LoginController.getLoggedInAccount().getOwner(), sqlDate, 0);
                dbc.addInformation(meeting);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("--");
                alert.showAndWait();
            }
        } catch (ParseException e) {
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