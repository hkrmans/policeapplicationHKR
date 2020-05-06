package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewPrisonersController implements Initializable {
    private ArrayList<Prisoner> prisoners;
    @FXML
    private TextArea showPrisoner;

    @FXML
    void GoBackViewPrisonersButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "PoliceMenu.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            prisoners = Singleton.getPrisonerInstance().getPrisonerList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Prisoner p: prisoners) {
            showPrisoner.appendText(p.getFirstName() +" | "+ p.getLastName()+" | CN:"+ p.getCivicNumber()
                    +" | ID:"+p.getPrisonerId()+" | Release Date:"+ p.getReleaseDate()+ "\n");
        }
    }
}
