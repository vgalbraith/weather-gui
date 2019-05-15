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
   private String location;
   /**
    * These arrays create sets of labels representing the different
    * names of each day and their respective high and low temps
    * ex: forecastHi0 = high temp for current day, forecastLo1 =
    * high temp for next day, forecastDay2 = name of third day.
    */
   private String[] forecastHis = new String[7];
   private String[] forecastLos = new String[7];
   private FetchForecast f;
   private FetchRadar r;
   private FetchWeather w;



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

      if (radarTab.isSelected())
      {
           AsyncTask rt = new GetDataInBackground();
           rt.execute(location);
      }

      else
      {
          // Fetch weather and forecast in the background.
          AsyncTask t = new GetWeatherDataInBackground();
          t.execute(location);

          AsyncTask g = new GetForecastDataInBackground();
          g.execute(location);
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
         for(int i = 0; i < 7; i++)
         {
            forecastHis[i] = f.getDayForecasts("maxTempC", i) + "\u00B0C";
         }
         for(int i = 0; i < 7; i++)
         {
            forecastLos[i] = f.getDayForecasts("minTempC", i) + "\u00B0C";
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
         for(int i = 0; i < 7; i++)
         {
            forecastHis[i] = f.getDayForecasts("maxTempF", i) + "\u00B0F";
         }
         for(int i = 0; i < 7; i++)
         {
            forecastLos[i] = f.getDayForecasts("minTempF", i) + "\u00B0F";
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
    *   Handles fetching the weather in the background.
    */
   private class GetWeatherDataInBackground extends AsyncTask<String, FetchWeather>
   {
      @Override
      public FetchWeather doInBackground(String location)
      {
         // Fetch the weather data
         w = new FetchWeather(location);
         return w;
      }

      @Override
      public void onPostExecute(FetchWeather w)
      {
         // Update the data on the screen
         if (w.isSuccessful())
         {
            if (weatherTab.isSelected())
            {
               showLabels(true);
               clearLabels();
               showFCButton(true);
               showForecast(true);
               weatherImageView.setVisible(true);
               gCatView.setVisible(false);

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

               lblLocation.setText(w.getLocation());
               lblConditions.setText(w.getFromOb("weather"));
               lblWind.setText(w.getFromOb("windMPH") + " MPH " + w.getFromOb("windDir"));
               lblPressure.setText(w.getFromOb("pressureIN") + " inHg");
               lblHumidity.setText(w.getFromOb("humidity") + "%");
               lblVisibility.setText(w.getFromOb("visibilityMI") + " MI");
               lblPrecip.setText(w.getFromOb("precipIN") + " IN");
               lblSnowDepth.setText(w.getFromOb("snowDepthIN") + " IN");
               weatherImageView.setImage(new Image("file:Images/" + w.getFromOb("icon")));
            }
         }
         else
         {
            clearLabels();
            showLabels(false);
            showFCButton(false);
            showForecast(false);

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
   private class GetForecastDataInBackground extends AsyncTask<String, FetchForecast>
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
         if (w.isSuccessful())
         {
            if (weatherTab.isSelected())
            {

               if (isFahrenheit)
               {
                  // Instantiation for forecast elements in degrees Fahrenheit
                  for(int i = 0; i < 7; i++)
                  {
                     forecastHis[i] = f.getDayForecasts("maxTempF", i) + "\u00B0F";
                  }
                  for(int i = 0; i < 7; i++)
                  {
                     forecastLos[i] = f.getDayForecasts("minTempF", i) + "\u00B0F";
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

               }
               else
               {
                  // Instantiation for forecast elements in degrees Celsius
                  for(int i = 0; i < 7; i++)
                  {
                     forecastHis[i] = f.getDayForecasts("maxTempC", i) + "\u00B0C";
                  }
                  for(int i = 0; i < 7; i++)
                  {
                     forecastLos[i] = f.getDayForecasts("minTempC", i) + "\u00B0C";
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
               }

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
            }
         }
      }
   }

    private class GetDataInBackground extends AsyncTask<String, FetchRadar>
    {
        @Override
        public FetchRadar doInBackground(String location)
        {
            // Fetch the radar data
            r = new FetchRadar(location);
            return r;
        }

        @Override
        public void onPostExecute(FetchRadar r)
        {
            // Update the radar data on the screen
            radarView.setVisible(true);
            radarView.setImage(new Image(r.getImage()));
            gCatView.setVisible(false);
        }
    }




   /**
    * Handle for tab swap
    */
   /*
   radarTab.setOnSelectionChanged(new EventHandler<Event>()
   {
      @Override
      public void handleTabToRadar(Event t)
      {
         if (radarTab.isSelected() && w.isSuccessful())
         {
            gCatView.setVisible(true);
         }
      }
   });
   */






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

   /**
    * Sets visibility of radar
    */
   private void showRadar(boolean b)
   {
      radarView.setVisible(b);
   }



}