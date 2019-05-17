package zchance;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Test class for autocompletion using MapBox
 */
public class ControlsFXTest extends Application
{
   FXMLLoader loader;
   ArrayList<String> suggestions = new ArrayList<>();
   HashMap<String, String> map = new HashMap<>();

   @FXML
   TextField tfInput;

   @FXML
   Label query;

   @FXML
   Button btnGo;

   @Override
   public void start(Stage primaryStage) throws Exception
   {
      loader = new FXMLLoader(getClass().getResource("controlsfx-test.fxml"));
      Parent root = loader.load();
      primaryStage.setTitle("ControlsFX test");
      primaryStage.setScene(new Scene(root));
      primaryStage.show();
   }

   public void autoComplete(KeyEvent k)
   {
      if (tfInput.getLength() > 2)
      {
         FetchMapBox f = new FetchMapBox(tfInput.getText());
         f.fetch();
         for (int i = 0; i < 5; i++)
         {
            if (!suggestions.contains(f.getPlaceName(i)))
            {
               suggestions.add(f.getPlaceName(i));
               map.put(f.getPlaceName(i), f.getCenter(i));
            }
         }
         TextFields.bindAutoCompletion(tfInput, suggestions);
      }
      if (tfInput.getLength() < 2)
      {
         suggestions.clear();
      }
   }

   public void handleGo(ActionEvent ae)
   {
      query.setText(map.get(tfInput.getText()));
   }

   public static void main(String[] args)
   {
      launch(args);
   }
}
