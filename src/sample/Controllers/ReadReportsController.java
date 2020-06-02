package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.DbConnect;
import sample.SceneChanger;
import sample.Models.Crime;
import sample.Models.CrimeReport;
import sample.Models.Person;
import sample.Models.WantedCriminal;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ReadReportsController implements Initializable {
    private ArrayList<CrimeReport> rapports = new ArrayList<>();
    private ArrayList<WantedCriminal> wantedCriminals = new ArrayList<>();
    private DbConnect dbc = DbConnect.getInstance(LoginController.getLoggedInAccount().getPassword());
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
    private void menuButton(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/PoliceMenu.fxml");
    }

    @FXML
    private void addWantedCriminalButtonOnAction(ActionEvent event) {
        SceneChanger.changeScene(event, "fxmlFiles/AddWantedCriminal.fxml");
    }

    @FXML
    private void ReportBackButtonOnAction() {
        try {
            indexes--;
            if (indexes < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("This is the first rapport");
                alert.setContentText("This is the first rapport in the system");
                alert.showAndWait();
                indexes = 0;
            } else if (indexes <= rapports.size()) {
                crimeRapportArea.setText(rapports.get(indexes).getRapport() + " | " + rapports.get(indexes).getWriter().getFirstName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    private void ReportNextButtonOnAction() {
        try {
            indexes++;
            if (indexes > rapports.size()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("No rapports left");
                alert.setContentText("There aren't any rapports left");
                alert.showAndWait();
                indexes = rapports.size() - 1;
            } else if (indexes < rapports.size()) {
                crimeRapportArea.setText(rapports.get(indexes).getRapport() + " | " + ((Person) rapports.get(indexes).getWriter()).getFirstName());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void ReportRegisterButtonOnAction() {
        String dateOfCrime = dateOfCrimeTextField.getText();
        String typeOfCrime = typeOfCrimeTextField.getText();
        String index = wantedCriminalTextField.getText();
        final String regex = "[2][0][\\d]{2}[-]([0][\\d]|([1][0-2]))[-]([0][1-9]|[1-2][\\d]|[3][0-1])";
        final String regexTwo = "[a-zA-Z]+";
        final String regexThree = "[0-9]";
        try {
            if (Pattern.matches(regex, dateOfCrime)) {
                if (Pattern.matches(regexTwo, typeOfCrime)) {
                    if (Pattern.matches(regexThree, index)) {
                        WantedCriminal wantedCriminal = new WantedCriminal(wantedCriminals.get(Integer.parseInt(index)).getFirstName(), wantedCriminals.get(Integer.parseInt(index)).getFirstName(), wantedCriminals.get(Integer.parseInt(index)).getCivicNumber(),
                                wantedCriminals.get(Integer.parseInt(index)).getRanking(), wantedCriminals.get(Integer.parseInt(index)).getBounty(), wantedCriminals.get(Integer.parseInt(index)).getWantedId());
                        CrimeReport crimeReport = new CrimeReport(rapports.get(indexes).getRapport(), rapports.get(indexes).getWriter(), rapports.get(indexes).getRapportID());
                        Crime crime = new Crime(Date.valueOf(dateOfCrime), typeOfCrime, wantedCriminal, crimeReport, 0);
                        dbc.addInformation(crime);
                    } else {
                        throw new Exception();
                    }
                } else {
                    throw new Exception();
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("You have entered wrong input");
            alert.setContentText("You must enter date, crime and wanted criminal before register a crime or have you entered wrong input");
            alert.showAndWait();
        }
    }

    @FXML
    private void removeReportButtonOnAction(ActionEvent event){
        int firstReport = 0;
        CrimeReport crimeReport = new CrimeReport(rapports.get(indexes).getRapport(), rapports.get(indexes).getWriter(), rapports.get(indexes).getRapportID());
        rapports.remove(crimeReport);
        dbc.deleteInformation(crimeReport);
        crimeRapportArea.setText(rapports.get(firstReport).getRapport() + " | " + rapports.get(firstReport).getWriter().getFirstName());
    }

    private void fillLists() {
            rapports.add(new CrimeReport(null, null, 0));
            dbc.getInfo(rapports);

            wantedCriminals.add(new WantedCriminal(null, null, null, 0, 0, 0));
            dbc.getInfo(wantedCriminals);
    }

    private void oklart() {
        crimeRapportArea.setText(rapports.get(indexes).getRapport() + " | " + rapports.get(indexes).getWriter().getFirstName());

        for (int i = 0; i < wantedCriminals.size(); i++) {
            wantedCriminalArea.appendText(i + ". | " + wantedCriminals.get(i).getFirstName() + " | "
                    + wantedCriminals.get(i).getLastName() + " | " + wantedCriminals.get(i).getCivicNumber() + "\n");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillLists();
        oklart();
    }
}

