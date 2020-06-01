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
import java.io.IOException;
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
    Prisoner prisoner = null;
    java.util.Date date = null;

    @FXML
    private TextField BookMeetingCivicNumberTextField;

    @FXML
    private TextField BookMeetingDateTextField;

    @FXML
    private TextField AvialiableTextField;

    @FXML
    void BookMeetingButtonOnAction(ActionEvent event) throws ParseException {
        date = new SimpleDateFormat("yyyy-MM-dd").parse(BookMeetingDateTextField.getText());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Meeting meeting = new Meeting(prisoner,LoginController.getLoggedInAccount().getOwner(),sqlDate,0);
        dbc.addInformation(meeting);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Meeting booked");
        alert.setContentText("You're meeting has been booked!");
        alert.showAndWait();
    }

    @FXML
    void CheckAvaliableButtonOnAction(ActionEvent event) {
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
    void GoBackBookMeetingButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/CivilianMenu.fxml");
    }
    private void fillList(){
        try {
            Prisoner prisoner = new Prisoner(null,null,null,0, null);
            prisoners.add(prisoner);
            dbc.getInfo(prisoners);

            Meeting meeting = new Meeting(null,null,null,0);
            meetings.add(meeting);
            dbc.getInfo(meetings);
            Civilian civilian = new Civilian(null,null,null);
            civilians.add(civilian);
            dbc.getInfo(civilians);

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       fillList();
    }
}
