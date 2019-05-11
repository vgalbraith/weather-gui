package zchance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
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
   Label lblFeelsLikeTag, lblHumidityTag, lblPressureTag, lblDewpointTag, lblWindTag, lblVisibilityTag,
         lblPrecipTag, lblSnowDepthTag;

   @FXML
   Label lblLocation, lblTemperature, lblConditions, lblFeelsLike, lblHumidity,
         lblPressure, lblDewpoint, lblWind, lblVisibility, lblPrecip, lblSnowDepth,
         lblForecastHi0, lblForecastLo0, lblForecastHi1, lblForecastLo1, lblForecastHi2, lblForecastLo2,
         lblForecastHi3, lblForecastLo3, lblForecastHi4, lblForecastLo4, lblForecastHi5, lblForecastLo5,
         lblForecastHi6, lblForecastLo6, lblWeekday0, lblWeekday1, lblWeekday2, lblWeekday3, lblWeekday4,
         lblWeekday5, lblWeekday6;

   @FXML
   Button btnGo, btnTemp;

   @FXML
   ImageView weatherImageView, radarView, gCatView, forecastIcon0, forecastIcon1, forecastIcon2,
             forecastIcon3, forecastIcon4, forecastIcon5, forecastIcon6;

   @FXML
   Tab weatherTab, radarTab;

   /**
    * Boolean to check whether or not the temperature is in
    * fahrenheit or celsius
    */
   private boolean isFahrenheit = true;

   /**
    * Fahrenheit & celsius variables
    */
   private String tempF = "0";
   private String feelsLikeF = "0";
   private String dewpointF = "0";
   private String tempC = "0";
   private String feelsLikeC = "0";
   private String dewpointC = "0";

   /**
    * These arrays create sets of labels representing the different
    * names of each day and their respective high and low temps
    * ex: forecastHi0 = high temp for current day, forecastLo1 =
    * high temp for next day, forecastDay2 = name of third day.
    */
   private String[] forecastHis = new String[7];
   private String[] forecastLos = new String[7];
   private FetchForecast f;

   /**
    * An array list of the 7day forecast abbreviations
    */
   private String[] weekdays = new String[7];

   /**
    * Handles the go button
    * @param ae ActionEvent
    */
   public void handleGo(ActionEvent ae)
   {
      FetchRadar r;
      FetchWeather w;

      String location = tfInput.getText();
      if (location.isEmpty())
      {
         location = ":auto";
      }
      else
      {
         location = CityFormatter.format(location);
      }

      w = new FetchWeather(location);
      f = new FetchForecast(location);

      if (w.isSuccessful())
      {
         if (weatherTab.isSelected())
         {
            showLabels(true);
            clearLabels();
            showFCButton(true);


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
                  forecastHis[index] = f.getDayForecasts("maxTempF", index) + "\u00B0F";
               }
               for(int index = 0; index < 7; index++)
               {
                  forecastLos[index] = f.getDayForecasts("minTempF", index) + "\u00B0F";
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
                  forecastHis[index] = f.getDayForecasts("maxTempC", index) + "\u00B0C";
               }
               for(int index = 0; index < 7; index++)
               {
                  forecastLos[index] = f.getDayForecasts("minTempC", index) + "\u00B0C";
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

            forecastIcon0.setImage(new Image("file:Images/" + f.getDayForecasts("icon", 0)));
            forecastIcon1.setImage(new Image("file:Images/" + f.getDayForecasts("icon", 1)));
            forecastIcon2.setImage(new Image("file:Images/" + f.getDayForecasts("icon", 2)));
            forecastIcon3.setImage(new Image("file:Images/" + f.getDayForecasts("icon", 3)));
            forecastIcon4.setImage(new Image("file:Images/" + f.getDayForecasts("icon", 4)));
            forecastIcon5.setImage(new Image("file:Images/" + f.getDayForecasts("icon", 5)));
            forecastIcon6.setImage(new Image("file:Images/" + f.getDayForecasts("icon", 6)));

            // Handle weekday abbreviations.
            // Get an abbr list of names from the 7day forecast into weekdays.
            for(int i = 0; i < weekdays.length; i++)
            {
               weekdays[i] = new TimestampMachine().getDay(f.getDayForecasts("timestamp", i));
            }

            // Set weekday labels
            lblWeekday0.setText("Today");
            lblWeekday1.setText(weekdays[1]);
            lblWeekday2.setText(weekdays[2]);
            lblWeekday3.setText(weekdays[3]);
            lblWeekday4.setText(weekdays[4]);
            lblWeekday5.setText(weekdays[5]);
            lblWeekday6.setText(weekdays[6]);

            //gCatView.setImage(new Image("file:Images/gCat.gif"));
         }
         else if (radarTab.isSelected())
         {
            if (location.isEmpty())
            {
               location = ":auto";
            }
            else
            {
               location = CityFormatter.format(location);
            }

            r = new FetchRadar(location);
            radarView.setImage(new Image(r.getImage()));
         }
      }
      else
      {
         lblLocation.setText("Can't pull data for " + w.getQuery());
         lblTemperature.setText("");
         lblConditions.setText("");

         // Displays a question mark when location cannot be pulled
         weatherImageView.setImage(new Image("file:Images/na.png"));
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
            forecastHis[index] = f.getDayForecasts("maxTempC", index) + "\u00B0C";
         }
         for(int index = 0; index < 7; index++)
         {
            forecastLos[index] = f.getDayForecasts("minTempC", index) + "\u00B0C";
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
            forecastHis[index] = f.getDayForecasts("maxTempF", index) + "\u00B0F";
         }
         for(int index = 0; index < 7; index++)
         {
            forecastLos[index] = f.getDayForecasts("minTempF", index) + "\u00B0F";
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

   /**
    * Sets visability of hidden labels
    */
   private void showLabels(boolean b)
   {
      lblTemperature.setVisible(b);
      lblFeelsLikeTag.setVisible(b);
      lblHumidityTag.setVisible(b);
      lblPressureTag.setVisible(b);
      lblDewpointTag.setVisible(b);
      lblWindTag.setVisible(b);
      lblVisibilityTag.setVisible(b);
      lblPrecipTag.setVisible(b);
      lblSnowDepthTag.setVisible(b);
   }

   /**
    * Sets visability of F/C button
    */
   private void showFCButton(boolean b)
   {
      btnTemp.setVisible(b);
   }
}
