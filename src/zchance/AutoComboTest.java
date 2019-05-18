package zchance;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AutoComboTest extends Application implements Initializable
{
   @FXML
   ComboBox<String> comboInput;

   @Override
   public void start(Stage primaryStage) throws Exception
   {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("auto-combo.fxml"));
      Parent root = loader.load();
      primaryStage.setTitle("Auto combo box test");
      primaryStage.setScene(new Scene(root));
      primaryStage.show();
   }

   public static void main(String[] args)
   {
      launch(args);
   }

   public void initialize(URL url, ResourceBundle rb)
   {
      comboInput.setVisibleRowCount(5);
      comboInput.getEditor().setOnKeyPressed(this::autoComplete);
   }

   private void autoComplete(KeyEvent k)
   {
      if (comboInput.getEditor().getLength() > 2)
      {
         FetchMapBox f = new FetchMapBox(comboInput.getEditor().getText());
         f.fetch();
         comboInput.getItems().clear();
         for (int i = 0; i < 5; i++)
         {
            comboInput.getItems().add(f.getPlaceName(i));
         }
         comboInput.show();
      }
   }
}
