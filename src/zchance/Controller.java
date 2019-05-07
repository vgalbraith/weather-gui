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

   /**
    * Boolean to check whether or not the temperature is in
    * fahrenheit or celsius
    */
   private boolean isFahrenheit = true;

   /**
    * Fahrenheit temp variable
    */
   private String tempF = "0";

   /**
    * Celsius temp variable
    */
   private String tempC = "0";

   /**
    * Feels like fahrenheit variable
    */
   private String feelsLikeF = "0";

   /**
    * Feels like celsius variable
    */
   private String feelsLikeC = "0";

   /**
    * Dewpoint fahrenheit variable
    */
   private String dewpointF = "0";

   /**
    * Dewpoint celsius variable
    */
   private String dewpointC = "0";

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
         feelsLikeF = w.getFromOb("feelslikeF");
         feelsLikeC = w.getFromOb("feelslikeC");
         dewpointF = w.getFromOb("dewpointF");
         dewpointC = w.getFromOb("dewpointC");

         if (isFahrenheit)
         {
            lblTemperature.setText(tempF + "\u00B0F");
            lblFeelsLike.setText(feelsLikeF + "\u00B0F");
            lblDewpoint.setText(dewpointF + "\u00B0F");
         }
         else
         {
            lblTemperature.setText(tempC + "\u00B0C");
            lblFeelsLike.setText(feelsLikeC + "\u00B0C");
            lblDewpoint.setText(dewpointC + "\u00B0C");
         }
         lblLocation.setText(w.getLocationName() + ", " + w.getLocationState());
         lblConditions.setText(w.getFromOb("weather"));
         lblWind.setText(w.getFromOb("windMPH") + " MPH " + w.getFromOb("windDir"));
         lblPressure.setText(w.getFromOb("pressureIN") + " inHg");
         lblHumidity.setText(w.getFromOb("humidity") + "%");
         lblVisibility.setText(w.getFromOb("visibilityMI") + " MI");
         lblPrecip.setText(w.getFromOb("precipIN") + " IN");
         lblSnowDepth.setText(w.getFromOb("snowDepthIN") + " IN");
         weatherImageView.setImage(new Image("file:Images/" + w.getFromOb("icon")));
      }
      else
      {
         lblLocation.setText("Can't pull data for " + w.getQuery());
         lblTemperature.setText("");
         lblConditions.setText("");
         weatherImageView.setImage(new Image("file:Images/na.png")); //puts a question mark on screen when location cannot be pulled
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
         lblFeelsLike.setText(feelsLikeC + "\u00B0C");
         lblDewpoint.setText(dewpointC + "\u00B0C");
         btnTemp.setText("\u00B0C");
         isFahrenheit = false;
      }
      else
      {
         lblTemperature.setText(tempF + "\u00B0F");
         lblFeelsLike.setText(feelsLikeF + "\u00B0F");
         lblDewpoint.setText(dewpointF + "\u00B0F");
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
