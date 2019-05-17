package zchance;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Test class for autocompletion using MapBox
 */
public class ControlsFXTest extends Application implements Initializable
{
   FXMLLoader loader;

   @FXML
   TextField tfInput;

   @Override
   public void start(Stage primaryStage) throws Exception
   {
      loader = new FXMLLoader(getClass().getResource("controlsfx-test.fxml"));
      Parent root = loader.load();
      primaryStage.setTitle("ControlsFX test");
      primaryStage.setScene(new Scene(root));
      primaryStage.show();
   }

   @Override
   public void initialize(URL url, ResourceBundle rb)
   {
      String[] suggestions = {"Alpha", "Alpha2", "Beta", "Charlie"};

      TextFields.bindAutoCompletion(tfInput, suggestions);
   }

   public static void main(String[] args)
   {
      launch(args);
   }
}
