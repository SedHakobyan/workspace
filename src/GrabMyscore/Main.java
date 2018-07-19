package GrabMyscore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //FileUtils.forceDelete(FileUtils.getFile("logScraping"));
        Parent root = FXMLLoader.load(getClass().getResource("ContainerUI.fxml"));

        primaryStage.setTitle("Grow");
        String image = Main.class.getResource("pic/mountain.jpg").toExternalForm();


        root.setStyle("-fx-background-image: url('" + image + "'); "  +
                "-fx-background-repeat: stretch;"+"-fx-background-size: cover;");
        primaryStage.setScene(new Scene(root));
      primaryStage.setResizable(false);
        primaryStage.show();

        //primaryStage.setOnCloseRequest(e-> Platform.exit());
        primaryStage.setOnCloseRequest(event -> {
            try {
                Runtime.getRuntime().exec("taskkill /F /IM conhost.exe");
                Runtime.getRuntime().exec("taskkill /F /IM phantomjs.exe");

            }
            catch (IOException er)
            {

            }
            System.exit(0);
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
