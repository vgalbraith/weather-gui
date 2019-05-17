package zchance;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Test class for autocompletion using MapBox
 */
public class AutoCompleteTest extends Application
{
   FXMLLoader loader;

   @FXML
   TextField tfInput;

   @Override
   public void start(Stage primaryStage) throws Exception
   {
      loader = new FXMLLoader(getClass().getResource("auto-complete.fxml"));
      Parent root = loader.load();
      primaryStage.setTitle("Autocompletion test");
      primaryStage.setScene(new Scene(root));
      primaryStage.show();
   }

   public static void main(String[] args)
   {
      launch(args);
   }

   public void autoCompleteField(KeyEvent k)
   {
      int charLength = tfInput.getLength();
      if (charLength > 2)
      {
         FetchMapBox f = new FetchMapBox(tfInput.getText());
         f.fetch();
         tfInput.setText(f.getPlaceName(0));
         tfInput.positionCaret(charLength);
         tfInput.selectRange(charLength, tfInput.getLength());
      }
      if (k.getCode() == KeyCode.BACK_SPACE || k.getCode() == KeyCode.DELETE)
      {
         tfInput.deleteNextChar();
      }
   }
}
