package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ReadReportsController implements Initializable {
    private ArrayList<CrimeRapport> rapports;
    private ArrayList<WantedCriminal> wantedCriminals;
    private int indexes = 0;

    @FXML
    private TextField wantedCriminalTextField;

    @FXML
    private TextField dateOfCrimeTextField;

    @FXML
    private TextField typeOfCrimeTextField;

    @FXML
    private TextArea wantedCriminalArea;

    @FXML
    private TextArea crimeRapportArea;

    @FXML
    void ReportGoBackButtonOnAction(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
    }


    @FXML
    void ReportBackButtonOnAction(ActionEvent event) {
        try {
            indexes--;
            if (indexes < 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("This is the first rapport");
                alert.setContentText("This is the first rapport in the system");
                alert.showAndWait();
                indexes = 0;
            }else if (indexes <= rapports.size()){
                crimeRapportArea.setText(rapports.get(indexes).getRapport() + " | " + rapports.get(indexes).getWriter());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @FXML
    void ReportNextButtonOnAction(ActionEvent event) {
        try {
            indexes++;
            if (indexes > rapports.size()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("No rapports left");
                alert.setContentText("There aren't any rapports left");
                alert.showAndWait();
                indexes = rapports.size()-1;
            }else if (indexes < rapports.size()){
                crimeRapportArea.setText(rapports.get(indexes).getRapport() + " | " + rapports.get(indexes).getWriter());
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void ReportRegisterButtonOnAction(ActionEvent event) {
        String dateOfCrime = dateOfCrimeTextField.getText();
        String typeOfCrime = typeOfCrimeTextField.getText();
        String index = wantedCriminalTextField.getText();
        final String regex = "[2][0][\\d]{2}[-]([0][\\d]|([1][0-2]))[-]([0][1-9]|[1-2][\\d]|[3][0-1])";
        final String regexTwo = "[a-zA-Z]+";
        final String regexThree = "[0-9]$";
        try{
            if (Pattern.matches(regex, dateOfCrime)){
                if(Pattern.matches(regexTwo, typeOfCrime)){
                    if (Pattern.matches(regexThree, index)){
                        WantedCriminal wantedCriminal = new WantedCriminal(wantedCriminals.get(Integer.parseInt(index)).getFirstName(), wantedCriminals.get(Integer.parseInt(index)).getFirstName(), wantedCriminals.get(Integer.parseInt(index)).getCivicNumber(),
                                wantedCriminals.get(Integer.parseInt(index)).getRanking(), wantedCriminals.get(Integer.parseInt(index)).getBounty(), wantedCriminals.get(Integer.parseInt(index)).getWantedId());
                        CrimeRapport crimeRapport = new CrimeRapport(rapports.get(indexes).getRapport(), rapports.get(indexes).getWriter(), rapports.get(indexes).getRapportID());
                        Crime crime = new Crime(Date.valueOf(dateOfCrime), typeOfCrime, wantedCriminal, crimeRapport);
                        DbConnect.getInstance("!)!AY!U!!Q!@b!S\"a\"d%V%U%").addCrime(crime);
                    }else {
                        throw new Exception();
                    }
                }else {
                    throw new Exception();
                }
            }else {
                throw new Exception();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("You have entered wrong input");
            alert.setContentText("You must enter date, crime and wanted criminal before register a crime or have you entered wrong input");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            rapports = DbConnect.getInstance("!)!AY!U!!Q!@b!S\"a\"d%V%U%").getCrimeRapport();
            wantedCriminals = DbConnect.getInstance("!)!AY!U!!Q!@b!S\"a\"d%V%U%").getWantedCriminals();
            crimeRapportArea.setText(rapports.get(indexes).getRapport() + " | " + rapports.get(indexes).getWriter());

        } catch (Exception e) {
            e.printStackTrace();
        }
        int j = 0;
        for (int i = 0; i < wantedCriminals.size(); i++) {
            wantedCriminalArea.appendText(j + ". | " + wantedCriminals.get(i).getFirstName() + " | "
                                            + wantedCriminals.get(i).getLastName() + " | " + wantedCriminals.get(i).getCivicNumber() + "\n");
            j++;
        }

        for (CrimeRapport e : rapports) {
            crimeRapportArea.setText(e.getRapport() + " | " + e.getWriter());
        }

    }
}

