package zchance;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.*;

/**
 * Test class for autocompletion using MapBox
 */
public class ControlsFXTest extends Application implements Initializable
{
   private FXMLLoader loader;
   private Set<String> suggestions = new HashSet<>();
   private HashMap<String, String> map = new HashMap<>();
   private AutoCompletionBinding<String> auto;

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

   public void initialize(URL url, ResourceBundle rb)
   {
      auto = TextFields.bindAutoCompletion(tfInput, suggestions);

      tfInput.setOnKeyPressed(this::autoComplete);
   }

   void bindAuto(String s)
   {
      suggestions.add(s);
      if (auto != null)
      {
         auto.dispose();
      }
      auto = TextFields.bindAutoCompletion(tfInput, suggestions);
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
               bindAuto(f.getPlaceName(i));
               map.put(f.getPlaceName(i), f.getCenter(i));
            }
         }
         /*TODO This seems to still add multiple autocomplete boxes occasionally*/
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
