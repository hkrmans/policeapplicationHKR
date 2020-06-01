package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.*;
import sample.Models.Civilian;
import sample.Models.Meeting;
import sample.Models.Prisoner;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookMeetingController implements Initializable {
    private ArrayList<Prisoner> prisoners = new ArrayList<>();
    private ArrayList<Meeting> meetings = new ArrayList<>();
    private ArrayList<Civilian> civilians = new ArrayList<>();
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());
    private Prisoner prisoner = null;
    private java.util.Date date = null;

    @FXML
    private TextField BookMeetingCivicNumberTextField;

    @FXML
    private TextField BookMeetingDateTextField;

    @FXML
    private TextField AvialiableTextField;

    @FXML
    void BookMeetingButtonOnAction() {
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(BookMeetingDateTextField.getText());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            Meeting meeting = new Meeting(prisoner, LoginController.getLoggedInAccount().getOwner(), sqlDate, 0);
            dbc.addInformation(meeting);
        } catch (ParseException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to convert the inserted date");
            alert.showAndWait();
        }
    }

    @FXML
    void CheckAvaliableButtonOnAction() {
        String UserInputCivicnumber = BookMeetingCivicNumberTextField.getText();
        String userInputDate = BookMeetingDateTextField.getText();
        for (int i = 0; i < prisoners.size(); i = i + 1) {
            if (UserInputCivicnumber.equals(prisoners.get(i).getCivicNumber())) {
                AvialiableTextField.appendText("Found!");
                prisoner = prisoners.get(i);

            }
        }
    }

    @FXML
    private void menuButton(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/CivilianMenu.fxml");
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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillList();
    }
}