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
   Label lblLocation, lblTemperature, lblConditions, lblWind, lblPressure, lblHumidity,
         lblFeelsLike, lblDewpoint, lblVisibility, lblPrecip, lblSnowDepth;

   @FXML
   Button btnGo, btnTemp;

   boolean isFahrenheit = true;

   /**
    * Handles the go button
    * @param ae ActionEvent
    */
   public void handleGo(ActionEvent ae)
   {
      clearLabels();

      String location = tfInput.getText();
      FetchWeather w = new FetchWeather(location);

      if (w.isSuccessful())
      {
         lblLocation.setText(w.getLocationName() + ", " + w.getLocationState());
         lblTemperature.setText(w.getFromOb("tempF") + "F");
         lblConditions.setText(w.getFromOb("weather"));
         lblWind.setText(w.getFromOb("windMPH") + " MPH " + w.getFromOb("windDir"));
         lblPressure.setText(w.getFromOb("pressureIN") + " inHg");
         lblHumidity.setText(w.getFromOb("humidity") + "%");
         lblDewpoint.setText(w.getFromOb("dewpointF") + "F");
         lblVisibility.setText(w.getFromOb("visibilityMI") + " MI");
         lblPrecip.setText(w.getFromOb("precipIN") + " IN");
         lblSnowDepth.setText(w.getFromOb("snowDepthIN") + " IN");
         lblFeelsLike.setText(w.getFromOb("feelslikeF") + "F");
      }
      else
      {
         lblLocation.setText("Can't pull data for " + w.getQuery());
         lblTemperature.setText("");
         lblConditions.setText("");
      }
   }

   /**
    * Handles the btnTemp button
    * displays temperature in degrees Celsius
    */
   public void handleBTNTemp()
   {
      String location = tfInput.getText();
      FetchWeather w = new FetchWeather(location);

      if (isFahrenheit)
      {
         lblTemperature.setText(w.getFromOb("tempC") + "C");
         isFahrenheit = false;
      }
      else
      {
         lblTemperature.setText(w.getFromOb("tempF") + "F");
         isFahrenheit = true;
      }
   }

   /**
    * Clears all labels
    */
   private void clearLabels()
   {
      lblLocation.setText("");
      lblTemperature.setText("");
      lblConditions.setText("");
      lblWind.setText("");
      lblPressure.setText("");
      lblHumidity.setText("");
      lblDewpoint.setText("");
      lblVisibility.setText("");
      lblPrecip.setText("");
      lblSnowDepth.setText("");
      lblFeelsLike.setText("");
   }
}
