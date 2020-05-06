package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ReadReportsController {

    @FXML
    void ReportGoBackButtonOnAction(ActionEvent event) {

        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("PoliceMenu.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @FXML
    void ReportBackButtonOnAction(ActionEvent event) {

    }


    @FXML
    void ReportNextButtonOnAction(ActionEvent event) {

    }

    @FXML
    void ReportRegisterButtonOnAction(ActionEvent event) {

    }
}
