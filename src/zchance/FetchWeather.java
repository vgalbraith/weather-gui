package zchance;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * FetchWeather pulls weather data using AerisAPI
 *
 * @author Zed Chance
 */
public class FetchWeather
{
   private final String CLIENT_ID = "wQhXMMnxoRV4HNKoRLZrL";
   private final String CLIENT_SECRET = "rUOW0GEyf5bT9JhUzro2WQAuUpj3A7nFHgVCRGEK";

   private String query;
   private JsonElement results;

   /**
    * Constructs a FetchWeather object
    * @param loc location to fetch
    */
   public FetchWeather(String loc)
   {
      try
      {
         // Encode location
         query = URLEncoder.encode(loc, "UTF-8");

         // Build url
         String urlString = "https://api.aerisapi.com/observations/" + query
                 + "?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET;
         URL url = new URL(urlString);

         // Open streams
         InputStream is = url.openStream();
         InputStreamReader isr = new InputStreamReader(is);
         BufferedReader br = new BufferedReader(isr);

         // Parse JSON
         JsonParser parser = new JsonParser();
         results = parser.parse(br);
      }
      catch (UnsupportedEncodingException e)
      {
         e.printStackTrace();
      }
      catch (MalformedURLException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   /**
    * Getter for query
    * @return query as String
    */
   public String getQuery()
   {
      return query;
   }

   /**
    * Checks if the API query was successful or not
    * @return success true or false
    */
   public boolean isSuccessful()
   {
      return results.getAsJsonObject().get("success").getAsBoolean();
   }

   /**
    * Returns the location name
    * @return location name
    */
   public String getLocationName()
   {
      return results.getAsJsonObject().get("response").getAsJsonObject()
                    .get("place").getAsJsonObject().get("name")
                    .getAsString();
   }

   /**
    * Returns the location's state
    * @return location's state
    */
   public String getLocationState()
   {
      return results.getAsJsonObject().get("response").getAsJsonObject()
              .get("place").getAsJsonObject().get("state")
              .getAsString();
   }

   /**
    * Returns a description of the current conditions
    * (i.e. "Sunny", "Cloudy")
    *
    * @return current condition
    */
   public String getConditions()
   {
      return results.getAsJsonObject().get("response").getAsJsonObject()
                    .get("ob").getAsJsonObject().get("weather")
                    .getAsString();
   }

   /**
    * Returns current temperature in Fahrenheit
    * @return current temp in F
    */
   public String getTemperatureF()
   {
      return results.getAsJsonObject().get("response").getAsJsonObject()
                    .get("ob").getAsJsonObject().get("tempF")
                    .getAsString() + "F";
   }

   /**
    * Returns current temperature in Celsius
    * @return current temp in C
    */
   public String getTemperatureC()
   {
      return results.getAsJsonObject().get("response").getAsJsonObject()
              .get("ob").getAsJsonObject().get("tempC")
              .getAsString() + "C";
   }

   /**
    * Returns stat from "ob" json object
    * (i.e. "dewpointF", "windMPH", "feelslikeF", "humidity", "windDir",
    * "sunrise", "sunset", "precipIN", "snowDepthIN" ... )
    *
    * @param s stat requests
    * @return string
    */
   public String getFromOb(String s)
   {
      return results.getAsJsonObject().get("response").getAsJsonObject()
              .get("ob").getAsJsonObject().get(s)
              .getAsString();
   }
}
