package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
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
        Image image = new Image("images/icon.png");
        primaryStage.getIcons().add(image);

        Sec sec = new Sec();
        DbConnect connect = new DbConnect(sec.decrypter("!)!AY!U!!Q!@b!S\"b#`!R!Q!"));

<<<<<<< HEAD
        Civilian civilian = new Civilian(null,null,null);
        ArrayList<Civilian> civilians = new ArrayList<>();
        civilians.add(civilian);
=======
        ArrayList<Prisoner> prisoners = new ArrayList<>();
        Prisoner prisoner = new Prisoner(null,null,null,0,null);
        prisoners.add(prisoner);
        connect.getInfo(prisoners);
        System.out.println(prisoners.size());

>>>>>>> 09572ebf2abee52dcaa9819f810ed4cf7a155da0

    }

    public static void main(String[] args) {
        launch(args);
    }
}
