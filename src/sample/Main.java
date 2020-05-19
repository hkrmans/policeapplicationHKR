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
        Sec sec = new Sec();
        DbConnect connect = DbConnect.getInstance(sec.decrypter("!)!AY!U!!Q!@b!R!`!`!T#T$"));


    }

    public static void main(String[] args) {
        launch(args);
    }
}
