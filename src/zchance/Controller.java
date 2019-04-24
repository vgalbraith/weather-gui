package zchance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller
{
   @FXML
   TextField tfInput;

   @FXML
   Label lblLocation, lblTemperature, lblConditions;

   @FXML
   Button btnGo;

   public void handleGo(ActionEvent ae)
   {
      String location = tfInput.getText();

      FetchWeather w = new FetchWeather(location);

      lblLocation.setText("Location: " + w.getLocationName());
      lblTemperature.setText("Temperature: " + w.getTemperature() + "F");
      lblConditions.setText("Conditions: " + w.getConditions());
   }
}
