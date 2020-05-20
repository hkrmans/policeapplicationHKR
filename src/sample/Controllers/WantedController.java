package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import sample.DbConnect;
import sample.SceneChanger;
import sample.WantedCriminal;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WantedController implements Initializable {
    private ArrayList<WantedCriminal> wantedCriminals = new ArrayList<>();
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());

    @FXML
    private TextArea showWanteds;

    @FXML
    void GoBackMostWantedButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
    }

    private void fillWantedList(){
        try {
            WantedCriminal wantedCriminal = new WantedCriminal(null, null, null, 0, 0, 0);
            wantedCriminals.add(wantedCriminal);
            dbc.getInfo(wantedCriminals);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showWanteds(){
        for (WantedCriminal wc : wantedCriminals) {
            showWanteds.appendText(wc.getFirstName() + " | " + wc.getLastName() + " | CN:" + wc.getCivicNumber()
                    + " | Bounty:" + wc.getBounty() + " | Ranking:" + wc.getRanking() + "\n");
        }
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillWantedList();
        showWanteds();
    }
}
