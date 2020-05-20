package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Controllers.LoginController;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main extends Application {

    public Main() throws SQLException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/sample.fxml"));
        primaryStage.setTitle("Police Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Image image = new Image("images/icon.png");
        primaryStage.getIcons().add(image);


    }

    public static void main(String[] args) {
        launch(args);
    }
}
