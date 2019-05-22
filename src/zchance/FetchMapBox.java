package zchance;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

/**
 * FetchMapBox uses MapBox API to retrieve complete city names and latitude and longitude
 */
class FetchMapBox
{
   private String query;
   private JsonElement results;

   /**
    * Constructs a FetchMapBox object
    * @param q query to search for
    */
   FetchMapBox(String q)
   {
      query = q;
   }

   /**
    * Fetches results
    */
   void fetch()
   {
      String type = "place";
      String country = "";
      if (query.matches("[0-9]+"))
      {
         type = "postcode";
         country = "&country=US";
      }
      try
      {
         String urlString = "https://api.mapbox.com/geocoding/v5/mapbox.places/" +
                 URLEncoder.encode(query, "UTF-8") + ".json?access_token=" +
                 APIKeys.MAPBOX_KEY + "&limit=5&types=" + type + country;

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
    * Gets the place name for a query
    * @param index of result to return (0 - 4)
    * @return place name as string
    */
   String getPlaceName(int index)
   {
      return results.getAsJsonObject().get("features").getAsJsonArray().get(index).getAsJsonObject()
              .get("place_name").getAsString();
   }

   /**
    * Gets the latitude and longitude
    * @param index of result to return (0 - 4)
    * @return lat and longitude with comma inbetween as string
    */
   String getCenter(int index)
   {
      String lat = results.getAsJsonObject().get("features").getAsJsonArray().get(index).getAsJsonObject()
              .get("center").getAsJsonArray().get(0).getAsString();

      String lon = results.getAsJsonObject().get("features").getAsJsonArray().get(index).getAsJsonObject()
              .get("center").getAsJsonArray().get(1).getAsString();

      return lon + "," + lat;
   }

   int getResultsNumber()
   {
      JsonArray temp = results.getAsJsonObject().get("features").getAsJsonArray();
      return temp.size();
   }
}
