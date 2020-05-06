package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends Application {

    public Main() throws SQLException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Police Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        DbConnect connect = new DbConnect("policemanagment","Jb84raA1??10");
       // Prisoner person = new Prisoner("Linus","Adolfsson","197807063217",0,null);
        //connect.addPerson(person);
       // Civilian c = new Civilian("Bogge","Nator","200007063217");
        //connect.addPerson(c);
       // Police p = new Police("Manna", "Thompson","199504034432","2516312012");
      //  connect.addPerson(p);
       // WantedCriminal wc = new WantedCriminal("Valter", "Sjo", "200305068799",10,999);
        //connect.addPerson(wc);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
