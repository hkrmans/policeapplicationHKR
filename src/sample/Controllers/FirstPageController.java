package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import sample.SceneChanger;

public class FirstPageController {

    @FXML
    private void loginButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/Login.fxml");
    }

    @FXML
    private void registerButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/SignUp.fxml");
    }

    @FXML
    private void exitButtonOnAction() {
        System.exit(0);
    }

    @FXML
    private void helpButtonOnAction() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Information");
        alert.setContentText("Hello and welcome to this police application. \n To progress, you need to register and create an account.\n When you register you will receive a password sent to you.");
        alert.showAndWait();

    }
}
