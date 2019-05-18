package zchance;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AutoComboTest extends Application implements Initializable
{
   @FXML
   ComboBox<String> comboInput;
   ComboBoxListViewSkin comboSkin;

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
      comboSkin = new ComboBoxListViewSkin(comboInput);
      comboSkin.getPopupContent().addEventFilter(KeyEvent.ANY, (event) ->
      {
         if (event.getCode() == KeyCode.SPACE)
         {
            event.consume();
         }
      });
      comboInput.setVisibleRowCount(5);
      comboInput.getEditor().setOnKeyReleased(this::autoComplete);
      comboInput.setSkin(comboSkin);
   }

   private void autoComplete(KeyEvent k)
   {
      int length = comboInput.getEditor().getLength();
      if (length > 2 && length % 2 == 1)
      {
         FetchMapBox f = new FetchMapBox(comboInput.getEditor().getText());
         f.fetch();
         comboInput.getItems().clear();
         for (int i = 0; i < 5; i++)
         {
            comboInput.getItems().add(f.getPlaceName(i));
         }
         if (!comboInput.isShowing())
         {
            comboInput.show();
         }
      }
   }
}
