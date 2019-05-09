package zchance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


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
         lblFeelsLike, lblDewpoint, lblVisibility, lblPrecip, lblSnowDepth,
         lblForecastHi0, lblForecastLo0, lblForecastHi1, lblForecastLo1, lblForecastHi2, lblForecastLo2,
         lblForecastHi3, lblForecastLo3,lblForecastHi4, lblForecastLo4,lblForecastHi5,
         lblForecastLo5, lblForecastHi6, lblForecastLo6, lblWeekDay1, lblWeekDay2, lblWeekday3, lblWeekDay4,
         lblWeekDay5, lblWeekDay6, lblWeekDay7;

   @FXML
   Button btnGo, btnTemp;

   @FXML
   ImageView weatherImageView, gCatView, radarView;

   @FXML
   Tab weatherTab, radarTab;

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

   /*
    * UNIX timestamp variable
    */
   private long timestamp = 0l;

   /**
    * These arrays create sets of labels representing the different
    * names of each day and their respective high and low temps
    * ex: forecastHi0 = high temp for current day, forecastLo1 =
    * high temp for next day, forecastDay2 = name of third day.
    */
   private String[] forecastHis = new String[7];
   private String[] forecastLos = new String[7];
   private FetchForecast f7Day;

   /**
    * Handles the go button
    * @param ae ActionEvent
    */
   public void handleGo(ActionEvent ae)
   {
      FetchWeather w;
      FetchRadar r;

      String location = tfInput.getText();
      if (location.isEmpty())
      {
         w = new FetchWeather(":auto");
         f7Day = new FetchForecast(":auto");
      }
      else
      {
         location = CityFormatter.format(location);
         w = new FetchWeather(location);
         f7Day = new FetchForecast(location);
      }

      if (w.isSuccessful())
      {
         if (weatherTab.isSelected())
         {
            clearLabels();
            tempF = w.getFromOb("tempF");
            tempC = w.getFromOb("tempC");
            feelsLikeF = w.getFromOb("feelslikeF");
            feelsLikeC = w.getFromOb("feelslikeC");
            dewpointF = w.getFromOb("dewpointF");
            dewpointC = w.getFromOb("dewpointC");

            if (isFahrenheit)
            {
               // Instantiation for forecast elements in degrees Fahrenheit
               for(int index = 0; index < 7; index++)
               {
                  forecastHis[index] = f7Day.getDayForecasts("maxTempF", index) + "\u00B0F";
               }
               for(int index = 0; index < 7; index++)
               {
                  forecastLos[index] = f7Day.getDayForecasts("minTempF", index) + "\u00B0F";
               }

               lblForecastHi0.setText(forecastHis[0]);
               lblForecastHi1.setText(forecastHis[1]);
               lblForecastHi2.setText(forecastHis[2]);
               lblForecastHi3.setText(forecastHis[3]);
               lblForecastHi4.setText(forecastHis[4]);
               lblForecastHi5.setText(forecastHis[5]);
               lblForecastHi6.setText(forecastHis[6]);

               lblForecastLo0.setText(forecastLos[0]);
               lblForecastLo1.setText(forecastLos[1]);
               lblForecastLo2.setText(forecastLos[2]);
               lblForecastLo3.setText(forecastLos[3]);
               lblForecastLo4.setText(forecastLos[4]);
               lblForecastLo5.setText(forecastLos[5]);
               lblForecastLo6.setText(forecastLos[6]);

               lblTemperature.setText(tempF + "\u00B0F");
               lblFeelsLike.setText(feelsLikeF + "\u00B0F");
               lblDewpoint.setText(dewpointF + "\u00B0F");
            }
            else
            {
               // Instantiation for forecast elements in degrees Celsius
               for(int index = 0; index < 7; index++)
               {
                  forecastHis[index] = f7Day.getDayForecasts("maxTempC", index) + "\u00B0C";
               }
               for(int index = 0; index < 7; index++)
               {
                  forecastLos[index] = f7Day.getDayForecasts("minTempC", index) + "\u00B0C";
               }

               lblForecastHi0.setText(forecastHis[0]);
               lblForecastHi1.setText(forecastHis[1]);
               lblForecastHi2.setText(forecastHis[2]);
               lblForecastHi3.setText(forecastHis[3]);
               lblForecastHi4.setText(forecastHis[4]);
               lblForecastHi5.setText(forecastHis[5]);
               lblForecastHi6.setText(forecastHis[6]);

               lblForecastLo0.setText(forecastLos[0]);
               lblForecastLo1.setText(forecastLos[1]);
               lblForecastLo2.setText(forecastLos[2]);
               lblForecastLo3.setText(forecastLos[3]);
               lblForecastLo4.setText(forecastLos[4]);
               lblForecastLo5.setText(forecastLos[5]);
               lblForecastLo6.setText(forecastLos[6]);

               lblTemperature.setText(tempC + "\u00B0C");
               lblFeelsLike.setText(feelsLikeC + "\u00B0C");
               lblDewpoint.setText(dewpointC + "\u00B0C");
            }
            lblLocation.setText(w.getLocation());
            lblConditions.setText(w.getFromOb("weather"));
            lblWind.setText(w.getFromOb("windMPH") + " MPH " + w.getFromOb("windDir"));
            lblPressure.setText(w.getFromOb("pressureIN") + " inHg");
            lblHumidity.setText(w.getFromOb("humidity") + "%");
            lblVisibility.setText(w.getFromOb("visibilityMI") + " MI");
            lblPrecip.setText(w.getFromOb("precipIN") + " IN");
            lblSnowDepth.setText(w.getFromOb("snowDepthIN") + " IN");
            weatherImageView.setImage(new Image("file:Images/" + w.getFromOb("icon")));

            //gCatView.setImage(new Image("file:Images/gCat.gif"));
         }
         else if (radarTab.isSelected())
         {
            if (location.isEmpty())
            {
               r = new FetchRadar(":auto");
            }
            else
            {
               r = new FetchRadar(location);
            }
            radarView.setImage(new Image(r.getImage()));
         }
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
   public void handleBTNTemp(ActionEvent ae)
   {
      if (isFahrenheit)
      {
         lblTemperature.setText(tempC + "\u00B0C");
         lblFeelsLike.setText(feelsLikeC + "\u00B0C");
         lblDewpoint.setText(dewpointC + "\u00B0C");
         btnTemp.setText("\u00B0C");
         // Instantiation for forecast elements in degrees Celsius
         for(int index = 0; index < 7; index++)
         {
            forecastHis[index] = f7Day.getDayForecasts("maxTempC", index) + "\u00B0C";
         }
         for(int index = 0; index < 7; index++)
         {
            forecastLos[index] = f7Day.getDayForecasts("minTempC", index) + "\u00B0C";
         }

         lblForecastHi0.setText(forecastHis[0]);
         lblForecastHi1.setText(forecastHis[1]);
         lblForecastHi2.setText(forecastHis[2]);
         lblForecastHi3.setText(forecastHis[3]);
         lblForecastHi4.setText(forecastHis[4]);
         lblForecastHi5.setText(forecastHis[5]);
         lblForecastHi6.setText(forecastHis[6]);

         lblForecastLo0.setText(forecastLos[0]);
         lblForecastLo1.setText(forecastLos[1]);
         lblForecastLo2.setText(forecastLos[2]);
         lblForecastLo3.setText(forecastLos[3]);
         lblForecastLo4.setText(forecastLos[4]);
         lblForecastLo5.setText(forecastLos[5]);
         lblForecastLo6.setText(forecastLos[6]);

         isFahrenheit = false;
      }
      else
      {
         lblTemperature.setText(tempF + "\u00B0F");
         lblFeelsLike.setText(feelsLikeF + "\u00B0F");
         lblDewpoint.setText(dewpointF + "\u00B0F");
         btnTemp.setText("\u00B0F");

         // Instantiation for forecast elements in degrees Fahrenheit
         for(int index = 0; index < 7; index++)
         {
            forecastHis[index] = f7Day.getDayForecasts("maxTempF", index) + "\u00B0F";
         }
         for(int index = 0; index < 7; index++)
         {
            forecastLos[index] = f7Day.getDayForecasts("minTempF", index) + "\u00B0F";
         }

         lblForecastHi0.setText(forecastHis[0]);
         lblForecastHi1.setText(forecastHis[1]);
         lblForecastHi2.setText(forecastHis[2]);
         lblForecastHi3.setText(forecastHis[3]);
         lblForecastHi4.setText(forecastHis[4]);
         lblForecastHi5.setText(forecastHis[5]);
         lblForecastHi6.setText(forecastHis[6]);

         lblForecastLo0.setText(forecastLos[0]);
         lblForecastLo1.setText(forecastLos[1]);
         lblForecastLo2.setText(forecastLos[2]);
         lblForecastLo3.setText(forecastLos[3]);
         lblForecastLo4.setText(forecastLos[4]);
         lblForecastLo5.setText(forecastLos[5]);
         lblForecastLo6.setText(forecastLos[6]);

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
