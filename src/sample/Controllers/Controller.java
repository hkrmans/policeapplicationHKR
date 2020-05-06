package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private void loginButtonOnAction(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/sampleLogin.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void registerButtonOnAction(ActionEvent event){
       try {
           Node node = (Node) event.getSource();
           Stage stage = (Stage) node.getScene().getWindow();

           FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/Register.fxml"));
           Parent root = loader.load();

           Scene scene = new Scene(root);
           stage.setScene(scene);
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
