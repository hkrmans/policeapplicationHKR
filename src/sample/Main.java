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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
