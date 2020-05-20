package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import sample.DbConnect;
import sample.Prisoner;
import sample.SceneChanger;
import sample.Sec;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewPrisonersController implements Initializable {
    private ArrayList<Prisoner> prisoners = new ArrayList<>();
    private Sec sec = new Sec();
    @FXML
    private TextArea showPrisoner;

    @FXML
    void GoBackViewPrisonersButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Prisoner prisoner = new Prisoner(null, null, null, 0, null);
            prisoners.add(prisoner);
            DbConnect.getInstance(sec.decrypter("!)!AY!U!!Q!@b!R!`!`!T#T$")).getInfo(prisoners);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Prisoner p : prisoners) {
            showPrisoner.appendText(p.getFirstName() + " | " + p.getLastName() + " | CN:" + p.getCivicNumber()
                    + " | ID:" + p.getPrisonerId() + " | Release Date:" + p.getReleaseDate() + "\n");
        }
    }
}
