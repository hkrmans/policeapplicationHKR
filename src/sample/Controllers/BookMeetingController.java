package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.SceneChanger;

import java.io.IOException;

public class BookMeetingController {

    @FXML
    void BookMeetingButtonOnAction(ActionEvent event) {

    }

    @FXML
    void CheckAvaliableButtonOnAction(ActionEvent event) {

    }

    @FXML
    void CheckDateButtonOnAction(ActionEvent event) {

    }

    @FXML
    void GoBackBookMeetingButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/StandardMenu.fxml");
    }
}