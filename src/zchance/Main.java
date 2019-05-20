package zchance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main for weather-gui
 */
public class Main extends Application
{
    FXMLLoader loader;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        loader = new FXMLLoader(getClass().getResource("gui.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Hello Weather");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("zchance/Styles.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void stop()
    {
        Controller c = loader.getController();
        c.stop();
    }
}
