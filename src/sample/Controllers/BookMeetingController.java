package sample.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sample.*;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Date;



public class BookMeetingController implements Initializable {
    private ArrayList<Prisoner> prisoners;
    private ArrayList<Meeting> meetings;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    @FXML
    private TextField BookMeetingCivicNumberTextField;

    @FXML
    private TextField BookMeetingDateTextField;

    @FXML
    private TextField AvialiableCivicTextField;

    @FXML
    private TextField DateAvialiableTextField;

    @FXML
    void BookMeetingButtonOnAction(ActionEvent event) {

    }

    @FXML
    void CheckAvaliableButtonOnAction(ActionEvent event) {
        String UserInputCivicnumber = BookMeetingCivicNumberTextField.getText();
        String userInputDate = BookMeetingDateTextField.getText();
        for (int i = 0; i < meetings.size(); i = i + 1) {
            for(i = 0; i <prisoners.size(); i = i + 1)
                if (UserInputCivicnumber.equals(meetings.get(i).getPrisoner(i).getCivicNumber())) {
                    AvialiableCivicTextField.appendText("Found!");
                }
            for ( i = 0; i <meetings.size(); i = i +1 ) {

            }
        }
    }


    /*
    @FXML
    void CheckDateButtonOnAction(ActionEvent event) throws ParseException {
        String UserInputDate = BookMeetingCivicNumberTextField.getText();
        for (int i = 0; i < meetings.size(); i = i + 1) {
            for(i = 0; i <prisoners.size(); i = i + 1)
                if (UserInputDate.equals(meetings.get(i).getPrisoner(i).getCivicNumber())) {
                    AvialiableCivicTextField.appendText("Found!");

                }
        }

    }

     */


    @FXML
    void GoBackBookMeetingButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/StandardMenu.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Sec sec = new Sec();
            Meeting meeting = new Meeting(null, null, null,0);
            meetings.add(meeting);
            DbConnect.getInstance(sec.decrypter("!)!AY!U!!Q!@b!R!`!`!T#T$")).getInfo(meetings);
            Prisoner prisoner = new Prisoner(null,null,null,0,null);
            prisoners.add(prisoner);
            DbConnect.getInstance(sec.decrypter("!)!AY!U!!Q!@b!R!`!`!T#T$")).getInfo(prisoners);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
