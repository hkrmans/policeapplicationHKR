package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import sample.SceneChanger;
import java.io.IOException;

public class FirstPageController {

    @FXML
    private void loginButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/Login.fxml");
    }

    @FXML
    private void registerButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/SignUp.fxml");
    }

    @FXML
    private void exitButtonOnAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void helpButtonOnAction(ActionEvent event) throws IOException {
        try {
            throw new NumberFormatException();
        } catch (NumberFormatException e) {
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            errorAlert.setHeaderText("Information");
            errorAlert.setContentText("Hello and welcome to this police application. \n To progress, you need to register and create an account.\n When you register you will receive a password sent to you.");
            errorAlert.showAndWait();
        }
    }
}
