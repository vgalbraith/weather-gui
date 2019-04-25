package zchance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller for weather-gui
 *
 * @author Zed Chance
 */
public class Controller
{
   @FXML
   TextField tfInput;

   @FXML
   Label lblLocation, lblTemperature, lblConditions;

   @FXML
   Button btnGo;

   /**
    * Handles the go button
    * @param ae ActionEvent
    */
   public void handleGo(ActionEvent ae)
   {
      String location = tfInput.getText();

      FetchWeather w = new FetchWeather(location);

      lblLocation.setText("Location: " + w.getLocationName() + ", " + w.getLocationState());
      lblTemperature.setText("Temperature: " + w.getTemperatureF());
      lblConditions.setText("Conditions: " + w.getConditions());
   }
}
