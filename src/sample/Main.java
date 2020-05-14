package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends Application {

    public Main() throws SQLException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/sample.fxml"));
        primaryStage.setTitle("Police Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        DbConnect connect = DbConnect.getInstance();
        Sec sec = new Sec();
        /*
        Date date =Date.valueOf("2020-05-05");//converting string into sql date
        Person p = new Civilian("bogge","kung","200010109999");
        Crime c = new Crime(date,"hejsan",p,
                new CrimeRapport("hejsan",p,10));
        connect.addCrime(c);
        
         */

    }

    public static void main(String[] args) {
        launch(args);
    }
}
