package zchance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sierra.AsyncTask;

/**
 * Controller for weather-gui
 * @author Zed Chance
 */
public class Controller
{
   @FXML
   TextField tfInput;

   @FXML
   Label lblLocation, lblTemperature, lblConditions, lblFeelsLike, lblHumidity,
         lblPressure, lblDewpoint, lblWind, lblVisibility, lblPrecip, lblSnowDepth,
         lblForecastHi0, lblForecastLo0, lblForecastHi1, lblForecastLo1, lblForecastHi2, lblForecastLo2,
         lblForecastHi3, lblForecastLo3, lblForecastHi4, lblForecastLo4, lblForecastHi5, lblForecastLo5,
         lblForecastHi6, lblForecastLo6, lblWeekday0, lblWeekday1, lblWeekday2, lblWeekday3, lblWeekday4,
         lblWeekday5, lblWeekday6, lblFeelsLikeTag, lblHumidityTag, lblPressureTag, lblDewpointTag,
         lblWindTag, lblVisibilityTag, lblPrecipTag, lblSnowDepthTag, lblRadar;

   @FXML
   Button btnGo, btnTemp;

   @FXML
   ImageView loadingCatView, weatherImageView, radarView, gCatView, forecastIcon0, forecastIcon1, forecastIcon2,
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
    * Fetch objects
    */
   private FetchForecast f;
   private FetchWeather w;

   /**
    * Background objects
    */
   private AsyncTask t;
   private AsyncTask g;
   private AsyncTask rt;

   /**
    * Location variable
    */
   private String location;
   /**
    * Handles the go button
    * @param ae ActionEvent
    */
   public void handleGo(ActionEvent ae)
   {
      // Get the location
      location = tfInput.getText();
      if (location.isEmpty())
      {
         location = ":auto";
      }
      else
      {
         location = CityFormatter.format(location);
      }

      clearLabels();
      showLabels(false);
      showFCButton(false);
      showForecast(false);
      radarView.setVisible(false);
      weatherImageView.setVisible(false);

      loadingCatView.setImage(new Image("file:Images/loading_nyan_cat.gif"));
      loadingCatView.setVisible(true);

      // Fetch all data in the background.
      g = new FetchForecastInBackground();
      g.execute(location);
      t = new FetchWeatherInBackground();
      t.execute(location);
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

         // Forecast labels for celsius
         lblForecastHi0.setText(f.getDayForecasts("maxTempC", 0) + "\u00B0C");
         lblForecastHi1.setText(f.getDayForecasts("maxTempC", 1) + "\u00B0C");
         lblForecastHi2.setText(f.getDayForecasts("maxTempC", 2) + "\u00B0C");
         lblForecastHi3.setText(f.getDayForecasts("maxTempC", 3) + "\u00B0C");
         lblForecastHi4.setText(f.getDayForecasts("maxTempC", 4) + "\u00B0C");
         lblForecastHi5.setText(f.getDayForecasts("maxTempC", 5) + "\u00B0C");
         lblForecastHi6.setText(f.getDayForecasts("maxTempC", 6) + "\u00B0C");

         lblForecastLo0.setText(f.getDayForecasts("minTempC", 0) + "\u00B0C");
         lblForecastLo1.setText(f.getDayForecasts("minTempC", 1) + "\u00B0C");
         lblForecastLo2.setText(f.getDayForecasts("minTempC", 2) + "\u00B0C");
         lblForecastLo3.setText(f.getDayForecasts("minTempC", 3) + "\u00B0C");
         lblForecastLo4.setText(f.getDayForecasts("minTempC", 4) + "\u00B0C");
         lblForecastLo5.setText(f.getDayForecasts("minTempC", 5) + "\u00B0C");
         lblForecastLo6.setText(f.getDayForecasts("minTempC", 6) + "\u00B0C");

         isFahrenheit = false;
      }
      else
      {
         lblTemperature.setText(tempF + "\u00B0F");
         lblFeelsLike.setText(feelsLikeF + "\u00B0F");
         lblDewpoint.setText(dewpointF + "\u00B0F");
         btnTemp.setText("\u00B0F");

         // Forecast labels for fahrenheit
         lblForecastHi0.setText(f.getDayForecasts("maxTempF", 0) + "\u00B0F");
         lblForecastHi1.setText(f.getDayForecasts("maxTempF", 1) + "\u00B0F");
         lblForecastHi2.setText(f.getDayForecasts("maxTempF", 2) + "\u00B0F");
         lblForecastHi3.setText(f.getDayForecasts("maxTempF", 3) + "\u00B0F");
         lblForecastHi4.setText(f.getDayForecasts("maxTempF", 4) + "\u00B0F");
         lblForecastHi5.setText(f.getDayForecasts("maxTempF", 5) + "\u00B0F");
         lblForecastHi6.setText(f.getDayForecasts("maxTempF", 6) + "\u00B0F");

         lblForecastLo0.setText(f.getDayForecasts("minTempF", 0) + "\u00B0F");
         lblForecastLo1.setText(f.getDayForecasts("minTempF", 1) + "\u00B0F");
         lblForecastLo2.setText(f.getDayForecasts("minTempF", 2) + "\u00B0F");
         lblForecastLo3.setText(f.getDayForecasts("minTempF", 3) + "\u00B0F");
         lblForecastLo4.setText(f.getDayForecasts("minTempF", 4) + "\u00B0F");
         lblForecastLo5.setText(f.getDayForecasts("minTempF", 5) + "\u00B0F");
         lblForecastLo6.setText(f.getDayForecasts("minTempF", 6) + "\u00B0F");

         isFahrenheit = true;
      }
   }

   /**
    *   Handles fetching the weather in the background.
    */
   private class FetchWeatherInBackground extends AsyncTask<String, FetchWeather>
   {
      @Override
      public FetchWeather doInBackground(String location)
      {
         // Fetch the weather data
         w = new FetchWeather(location);
         if (w.isSuccessful())
         {
            tempF = w.getFromOb("tempF");
            tempC = w.getFromOb("tempC");
            feelsLikeF = w.getFromOb("feelslikeF");
            feelsLikeC = w.getFromOb("feelslikeC");
            dewpointF = w.getFromOb("dewpointF");
            dewpointC = w.getFromOb("dewpointC");
         }

         return w;
      }

      @Override
      public void onPostExecute(FetchWeather w)
      {
         // Pull radar after weather pull is complete in case of ":auto" query
         rt = new FetchRadarInBackground();
         rt.execute(location);

         // Update the data on the screen
         if (!w.isSuccessful())
         {
            lblLocation.setText("Can't pull data for " + w.getQuery());
            lblTemperature.setText("");
            lblConditions.setText("");

            lblRadar.setText("Can't pull data for " + w.getQuery());
            radarView.setVisible(false);
            weatherImageView.setVisible(false);

            // Displays the grumpy cat when location cannot be pulled
            gCatView.setVisible(true);
            gCatView.setImage(new Image("file:Images/gCat.gif"));
         }
      }
   }

   /**
    *  Handles the background task when fetching the forecast.
    */
   private class FetchForecastInBackground extends AsyncTask<String, FetchForecast>
   {
      @Override
      public FetchForecast doInBackground(String location)
      {
         // Fetch the weather data
         f = new FetchForecast(location);
         return f;
      }

      @Override
      public void onPostExecute(FetchForecast f)
      {
         // Update the data on the screen
         if (f.isSuccessful())
         {
            if (isFahrenheit)
            {
               // Forecast labels for fahrenheit
               lblForecastHi0.setText(f.getDayForecasts("maxTempF", 0) + "\u00B0F");
               lblForecastHi1.setText(f.getDayForecasts("maxTempF", 1) + "\u00B0F");
               lblForecastHi2.setText(f.getDayForecasts("maxTempF", 2) + "\u00B0F");
               lblForecastHi3.setText(f.getDayForecasts("maxTempF", 3) + "\u00B0F");
               lblForecastHi4.setText(f.getDayForecasts("maxTempF", 4) + "\u00B0F");
               lblForecastHi5.setText(f.getDayForecasts("maxTempF", 5) + "\u00B0F");
               lblForecastHi6.setText(f.getDayForecasts("maxTempF", 6) + "\u00B0F");

               lblForecastLo0.setText(f.getDayForecasts("minTempF", 0) + "\u00B0F");
               lblForecastLo1.setText(f.getDayForecasts("minTempF", 1) + "\u00B0F");
               lblForecastLo2.setText(f.getDayForecasts("minTempF", 2) + "\u00B0F");
               lblForecastLo3.setText(f.getDayForecasts("minTempF", 3) + "\u00B0F");
               lblForecastLo4.setText(f.getDayForecasts("minTempF", 4) + "\u00B0F");
               lblForecastLo5.setText(f.getDayForecasts("minTempF", 5) + "\u00B0F");
               lblForecastLo6.setText(f.getDayForecasts("minTempF", 6) + "\u00B0F");
            }
            else
            {
               // Forecast labels for celsius
               lblForecastHi0.setText(f.getDayForecasts("maxTempC", 0) + "\u00B0C");
               lblForecastHi1.setText(f.getDayForecasts("maxTempC", 1) + "\u00B0C");
               lblForecastHi2.setText(f.getDayForecasts("maxTempC", 2) + "\u00B0C");
               lblForecastHi3.setText(f.getDayForecasts("maxTempC", 3) + "\u00B0C");
               lblForecastHi4.setText(f.getDayForecasts("maxTempC", 4) + "\u00B0C");
               lblForecastHi5.setText(f.getDayForecasts("maxTempC", 5) + "\u00B0C");
               lblForecastHi6.setText(f.getDayForecasts("maxTempC", 6) + "\u00B0C");

               lblForecastLo0.setText(f.getDayForecasts("minTempC", 0) + "\u00B0C");
               lblForecastLo1.setText(f.getDayForecasts("minTempC", 1) + "\u00B0C");
               lblForecastLo2.setText(f.getDayForecasts("minTempC", 2) + "\u00B0C");
               lblForecastLo3.setText(f.getDayForecasts("minTempC", 3) + "\u00B0C");
               lblForecastLo4.setText(f.getDayForecasts("minTempC", 4) + "\u00B0C");
               lblForecastLo5.setText(f.getDayForecasts("minTempC", 5) + "\u00B0C");
               lblForecastLo6.setText(f.getDayForecasts("minTempC", 6) + "\u00B0C");
            }

            forecastIcon0.setImage(new Image("file:Images/" + f.getDayForecasts("icon", 0)));
            forecastIcon1.setImage(new Image("file:Images/" + f.getDayForecasts("icon", 1)));
            forecastIcon2.setImage(new Image("file:Images/" + f.getDayForecasts("icon", 2)));
            forecastIcon3.setImage(new Image("file:Images/" + f.getDayForecasts("icon", 3)));
            forecastIcon4.setImage(new Image("file:Images/" + f.getDayForecasts("icon", 4)));
            forecastIcon5.setImage(new Image("file:Images/" + f.getDayForecasts("icon", 5)));
            forecastIcon6.setImage(new Image("file:Images/" + f.getDayForecasts("icon", 6)));

            // Set weekday labels
            lblWeekday0.setText("Today");
            lblWeekday1.setText(new DateFormatter().getDay(f.getDayForecasts("timestamp", 1)));
            lblWeekday2.setText(new DateFormatter().getDay(f.getDayForecasts("timestamp", 2)));
            lblWeekday3.setText(new DateFormatter().getDay(f.getDayForecasts("timestamp", 3)));
            lblWeekday4.setText(new DateFormatter().getDay(f.getDayForecasts("timestamp", 4)));
            lblWeekday5.setText(new DateFormatter().getDay(f.getDayForecasts("timestamp", 5)));
            lblWeekday6.setText(new DateFormatter().getDay(f.getDayForecasts("timestamp", 6)));
         }
      }
   }

   private class FetchRadarInBackground extends AsyncTask<String, FetchRadar>
   {
      @Override
      public FetchRadar doInBackground(String location)
      {
         // Fetch the radar data
         if (location.equals(":auto"))
         {
            location = w.getLocation();
         }
         return new FetchRadar(location);
      }

      @Override
      public void onPostExecute(FetchRadar r)
      {
         if (w.isSuccessful())
         {
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

            lblLocation.setText(w.getLocation());
            lblConditions.setText(w.getFromOb("weather"));
            lblWind.setText(w.getFromOb("windMPH") + " MPH " + w.getFromOb("windDir"));
            lblPressure.setText(w.getFromOb("pressureIN") + " inHg");
            lblHumidity.setText(w.getFromOb("humidity") + "%");
            lblVisibility.setText(w.getFromOb("visibilityMI") + " MI");
            lblPrecip.setText(w.getFromOb("precipIN") + " IN");
            lblSnowDepth.setText(w.getFromOb("snowDepthIN") + " IN");
            weatherImageView.setImage(new Image("file:Images/" + w.getFromOb("icon")));

            showLabels(true);
            showFCButton(true);
            showForecast(true);
            weatherImageView.setVisible(true);

            // Update the radar data on the screen
            radarView.setVisible(true);
            radarView.setImage(new Image(r.getImage()));
            gCatView.setVisible(false);
            loadingCatView.setVisible(false);
         }
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
    * Sets visibility of hidden labels
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

      lblLocation.setVisible(b);
      lblConditions.setVisible(b);
      lblWind.setVisible(b);
      lblPressure.setVisible(b);
      lblHumidity.setVisible(b);
      lblDewpoint.setVisible(b);
      lblVisibility.setVisible(b);
      lblPrecip.setVisible(b);
      lblSnowDepth.setVisible(b);
      lblFeelsLike.setVisible(b);
   }

   /**
    * Sets visibility of F/C button
    */
   private void showFCButton(boolean b)
   {
      btnTemp.setVisible(b);
   }

   /**
    * Sets visibility for forecast
    */
   private void showForecast(boolean b)
   {
      lblForecastHi0.setVisible(b);
      lblForecastHi1.setVisible(b);
      lblForecastHi2.setVisible(b);
      lblForecastHi3.setVisible(b);
      lblForecastHi4.setVisible(b);
      lblForecastHi5.setVisible(b);
      lblForecastHi6.setVisible(b);

      lblForecastLo0.setVisible(b);
      lblForecastLo1.setVisible(b);
      lblForecastLo2.setVisible(b);
      lblForecastLo3.setVisible(b);
      lblForecastLo4.setVisible(b);
      lblForecastLo5.setVisible(b);
      lblForecastLo6.setVisible(b);

      lblWeekday0.setVisible(b);
      lblWeekday1.setVisible(b);
      lblWeekday2.setVisible(b);
      lblWeekday3.setVisible(b);
      lblWeekday4.setVisible(b);
      lblWeekday5.setVisible(b);
      lblWeekday6.setVisible(b);

      forecastIcon0.setVisible(b);
      forecastIcon1.setVisible(b);
      forecastIcon2.setVisible(b);
      forecastIcon3.setVisible(b);
      forecastIcon4.setVisible(b);
      forecastIcon5.setVisible(b);
      forecastIcon6.setVisible(b);
   }

   void stop()
   {
      t.cancel();
      g.cancel();
      rt.cancel();
   }
}
