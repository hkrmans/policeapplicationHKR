package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.DbConnect;
import sample.Models.Conviction;
import sample.Models.Prisoner;
import sample.SceneChanger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AddConvictionController {
    private ArrayList<Prisoner> prisoners = new ArrayList<>();
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());
    private Prisoner prisoner;

    @FXML
    private TextField releaseDate, convictionDate, sentence, prisonerID;

    @FXML
    private void addConviction() {
        fillList();

        try {
            java.util.Date date;
            date = new SimpleDateFormat("yyyy-MM-dd").parse(releaseDate.getText());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            java.util.Date date2;
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(convictionDate.getText());
            java.sql.Date sqlDate2 = new java.sql.Date(date.getTime());

            if (checkPrisoner()) {
                Conviction c = new Conviction(sqlDate, sqlDate2, sentence.getText(), prisoner, 0);
                dbc.addInformation(c);
            } else {
                System.out.println("wrong id");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private boolean checkPrisoner() {
        int id = Integer.parseInt(prisonerID.getText());
        for (Prisoner p : prisoners) {
            if (p.getPrisonerId() == id) {
                prisoner = p;
                return true;
            }
        }
        return false;
    }

    private void fillList() {
        Prisoner prisoner = new Prisoner(null, null, null, 0);
        prisoners.add(prisoner);
        dbc.getInfo(prisoners);
    }

    @FXML
    private void menuButton(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
    }

}
