package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/FirstPage.fxml"));
        primaryStage.setTitle("Police Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Image image = new Image("images/icon.png");
        primaryStage.getIcons().add(image);
        mail mail = new mail();
        mail.sendValidationEmail("mats_06_20@hotmail.com","password");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
