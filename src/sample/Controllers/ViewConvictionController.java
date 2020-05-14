package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import sample.Conviction;
import sample.SceneChanger;
import sample.Singleton;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewConvictionController implements Initializable {
    private ArrayList<Conviction> convictions;

    @FXML
    private TextArea convictionsArea;

    @FXML
    void LogOutViewConvictionButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/sample.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            convictions = Singleton.getConvictionInstance().getConvictionList();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        for (Conviction c : convictions) {
            convictionsArea.appendText(c.getPrisoner() + " | " + c.getSentence() + " | "
                                        + c.getConviction() + "|" + c.getRelease());
        }


    }
}
