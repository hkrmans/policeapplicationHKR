package sample.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
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
    private TextField deleteMeetingTextField;

    @FXML
    private TextArea meetingsTextArea;


    @FXML
    void BookMeetingButtonOnAction(ActionEvent event) throws ParseException {
        date = new SimpleDateFormat("yyyy-MM-dd").parse(BookMeetingDateTextField.getText());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Meeting meeting = new Meeting(prisoner,LoginController.getLoggedInAccount().getOwner(),sqlDate,0);
        dbc.addInformation(meeting);
    }

    @FXML
    void CheckAvaliableButtonOnAction(ActionEvent event) {
        String UserInputCivicnumber = BookMeetingCivicNumberTextField.getText();
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

    @FXML
    void deleteMeetingButtonOnAction(ActionEvent event) {
        int CivilianInputMeetingId = Integer.parseInt(deleteMeetingTextField.getText());
        for (int i = 0; i < meetings.size(); i = i + 1) {
            if (CivilianInputMeetingId == (meetings.get(i).getMeetingID()))  {
                dbc.deleteInformation(meetings.get(i));
            }
        }
    }

    @FXML
    void checkMeetingButtonOnAction(ActionEvent event) {
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
    private void fillList(){
        try {
            Prisoner prisoner = new Prisoner(null,null,null,0);
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