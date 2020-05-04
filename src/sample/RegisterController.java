package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class RegisterController {

    @FXML
    private TextField usernameTextfield;

    @FXML
    private TextField passwordTextfield;



    @FXML
    private void registerButtonOnAction(ActionEvent event) {
        String username = usernameTextfield.getText();
        String password = passwordTextfield.getText();

        try {
            if (Pattern.matches("[a-zA-Z1-9]+", username)) {
                if (Pattern.matches("[a-zA-Z1-9]{8,}", password)) {

                } else {
                    throw new Exception();
                }
            } else {
                throw new Exception();
            }


            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("sampleLogin.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong input");
            alert.setContentText("You have entered the wrong input required!");
            alert.showAndWait();
        }
    }


    @FXML
    private void backButtonOnAction(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
