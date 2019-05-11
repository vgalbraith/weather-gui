package zchance;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

/**
 * FetchWeather pulls weather data using Aeris API
 * @author Zed Chance
 */
public class FetchWeather
{
   private String query;
   private JsonElement results;

   /**
    * Constructs a FetchWeather object
    * @param loc location to fetch
    */
   FetchWeather(String loc)
   {
      query = loc;

      try
      {
         // Build url
         String urlString = "https://api.aerisapi.com/observations/" + URLEncoder.encode(loc, "UTF-8")
                            + "?client_id=" + APIKeys.CLIENT_ID + "&client_secret=" + APIKeys.CLIENT_SECRET;
         URL url = new URL(urlString);

         // Open streams
         InputStream is = url.openStream();
         InputStreamReader isr = new InputStreamReader(is);
         BufferedReader br = new BufferedReader(isr);

         // Parse JSON
         results = new JsonParser().parse(br);
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
   String getQuery()
   {
      return query;
   }

   /**
    * Checks if the API query was successful or not
    * @return success true or false
    */
   boolean isSuccessful()
   {
      return results.getAsJsonObject().get("success").getAsBoolean();
   }

   /**
    * Returns the location name
    * @return location name
    */
   String getLocationName()
   {
      return results.getAsJsonObject().get("response").getAsJsonObject()
                    .get("place").getAsJsonObject().get("name")
                    .getAsString();
   }

   /**
    * Returns the location's state
    * @return location's state
    */
   String getLocationState()
   {
      return results.getAsJsonObject().get("response").getAsJsonObject()
                    .get("place").getAsJsonObject().get("state")
                    .getAsString();
   }

   /**
    * Returns the location of the query in a formatted state
    * @return formatted location
    */
   String getLocation()
   {
      String temp = getLocationName() + "," + getLocationState();
      return CityFormatter.format(temp);
   }

   /**
    * Returns stat from "ob" json object
    * (i.e. "tempF", "tempC", "weather", "dewpointF", "windMPH",
    * "feelslikeF", "humidity", "windDir", "sunrise", "sunset",
    * "precipIN", "snowDepthIN" ... )
    *
    * @param s stat requested
    * @return string
    */
   String getFromOb(String s)
   {
      try
      {
         return results.getAsJsonObject().get("response").getAsJsonObject()
                 .get("ob").getAsJsonObject().get(s)
                 .getAsString();
      }
      catch (java.lang.UnsupportedOperationException e)
      {
         //e.printStackTrace();
      }
      return "";
   }

   /**
    * Returns the timezone
    * (i.e. "America\/Los_Angeles")
    * @return timezone
    */
   public String getTimezone()
   {
      try
      {
         return results.getAsJsonObject().get("response").getAsJsonObject()
                 .get("profile").getAsJsonObject().get("tz")
                 .getAsString();
      }
      catch (java.lang.UnsupportedOperationException e)
      {
         //e.printStackTrace();
      }
      return "";
   }

   /**
    * String representation of FetchWeather object
    * @return string
    */
   public String toString()
   {
      return "Query: " + getQuery() + ", Success: " + isSuccessful();
   }
}
