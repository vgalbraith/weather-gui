package zchance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

   @FXML
   ImageView weatherImageView;

   private boolean isFahrenheit = true;
   private String tempF = "0";
   private String tempC = "0";

   /**
    * Handles the go button
    * @param ae ActionEvent
    */
   public void handleGo(ActionEvent ae)
   {
      clearLabels();
      FetchWeather w;

      String location = tfInput.getText();
      if (location.isEmpty())
      {
         w = new FetchWeather(":auto");
      }
      else
      {
         w = new FetchWeather(location);
      }

      if (w.isSuccessful())
      {
         tempF = w.getFromOb("tempF");
         tempC = w.getFromOb("tempC");

         if (isFahrenheit)
         {
            lblTemperature.setText(tempF + "\u00B0F");
         }
         else
         {
            lblTemperature.setText(tempC + "\u00B0C");
         }
         lblLocation.setText(w.getLocationName() + ", " + w.getLocationState());
         lblConditions.setText(w.getFromOb("weather"));
         lblWind.setText(w.getFromOb("windMPH") + " MPH " + w.getFromOb("windDir"));
         lblPressure.setText(w.getFromOb("pressureIN") + " inHg");
         lblHumidity.setText(w.getFromOb("humidity") + "%");
         lblDewpoint.setText(w.getFromOb("dewpointF") + "\u00B0F");
         lblVisibility.setText(w.getFromOb("visibilityMI") + " MI");
         lblPrecip.setText(w.getFromOb("precipIN") + " IN");
         lblSnowDepth.setText(w.getFromOb("snowDepthIN") + " IN");
         lblFeelsLike.setText(w.getFromOb("feelslikeF") + "\u00B0F");
         weatherImageView.setImage(new Image("file:Images/" + w.getFromOb("icon")));
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


      if (isFahrenheit)
      {
         lblTemperature.setText(tempC + "\u00B0C");
         btnTemp.setText("\u00B0C");
         isFahrenheit = false;
      }
      else
      {
         lblTemperature.setText(tempF + "\u00B0F");
         btnTemp.setText("\u00B0F");
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
