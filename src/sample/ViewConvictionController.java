package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;


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
        SceneChanger.changeScene(event, "sample.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            convictions = Singleton.getConvictionInstance().getConvictionList();
        }catch (Exception e){
            e.printStackTrace();
        }
        for (int i = 0; i <convictions.size(); i++) {
            convictionsArea.setText(convictions.get(i).getPrisoner()+ " | " + convictions.get(i).getConviction() + " | "
                    + convictions.get(i).getRelease() + " | " + convictions.get(i).getSentence());
        }
    }
}
