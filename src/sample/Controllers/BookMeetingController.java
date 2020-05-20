package sample.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sample.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class BookMeetingController implements Initializable {
    private ArrayList<Prisoner> prisoners = new ArrayList<>();
    private ArrayList<Meeting> meetings = new ArrayList<>();
    private ArrayList<Civilian> civilians = new ArrayList<>();
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    @FXML
    private TextField BookMeetingCivicNumberTextField;

    @FXML
    private TextField BookMeetingDateTextField;

    @FXML
    private TextField AvialiableTextField;

    @FXML
    void BookMeetingButtonOnAction(ActionEvent event) {

    }

    @FXML
    void CheckAvaliableButtonOnAction(ActionEvent event) {
        String UserInputCivicnumber = BookMeetingCivicNumberTextField.getText();
        String userInputDate = BookMeetingDateTextField.getText();
        for (int i = 0; i < prisoners.size(); i = i + 1) {
            if (UserInputCivicnumber.equals(prisoners.get(i).getCivicNumber())) {
                    AvialiableTextField.appendText("Found!");

            }
        }
    }
    
    @FXML
    void GoBackBookMeetingButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/StandardMenu.fxml");
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
