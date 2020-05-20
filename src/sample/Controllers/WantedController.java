package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import sample.DbConnect;
import sample.SceneChanger;
import sample.Sec;
import sample.WantedCriminal;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WantedController implements Initializable {
    private ArrayList<WantedCriminal> wantedCriminals;
    @FXML
    private TextArea showWanteds;
    
    @FXML
    void GoBackMostWantedButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Sec sec = new Sec();
            ArrayList<WantedCriminal> wantedCriminals = new ArrayList<>();
            WantedCriminal wantedCriminal = new WantedCriminal(null,null,null,0,0,0);
            wantedCriminals.add(wantedCriminal);
            DbConnect.getInstance(sec.decrypter("!)!AY!U!!Q!@b!R!`!`!T#T$")).getInfo(wantedCriminals);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (WantedCriminal wc : wantedCriminals) {
            showWanteds.appendText(wc.getFirstName() + " | " + wc.getLastName() + " | CN:" + wc.getCivicNumber()
                    + " | Bounty:" + wc.getBounty() + " | Ranking:" + wc.getRanking()+"\n");
        }
    }

}
