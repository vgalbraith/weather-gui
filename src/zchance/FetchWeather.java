package zchance;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

/**
 * FetchWeather pulls weather data using Aeris API
 *
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
   public FetchWeather(String loc)
   {
      final String CLIENT_ID = "wQhXMMnxoRV4HNKoRLZrL";
      final String CLIENT_SECRET = "JIZobWk2qyfbStTUJSShF1kTLp06WTXLcJKA5dpD";

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
      String temp = results.getAsJsonObject().get("response").getAsJsonObject()
                    .get("place").getAsJsonObject().get("name")
                    .getAsString();
      return temp.substring(0, 1).toUpperCase() + temp.substring(1);
      /* TODO make this capitalize first letter of all words */
   }

   /**
    * Returns the location's state
    * @return location's state
    */
   public String getLocationState()
   {
      return results.getAsJsonObject().get("response").getAsJsonObject()
                    .get("place").getAsJsonObject().get("state")
                    .getAsString().toUpperCase();
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
   public String getFromOb(String s)
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
    * String representation of FetchWeather object
    * @return string
    */
   public String toString()
   {
      return "Query: " + getQuery() + ", Success: " + isSuccessful();
   }

}
