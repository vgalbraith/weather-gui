package zchance;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

public class FetchMapBox
{
   private String query;
   private JsonElement results;

   public FetchMapBox(String q)
   {
      query = q;
   }

   public void fetch()
   {
      try
      {
         String urlString = "https://api.mapbox.com/geocoding/v5/mapbox.places/" +
                 URLEncoder.encode(query, "UTF-8") + ".json?access_token=" +
                 APIKeys.MAPBOX_KEY + "&limit=5&types=place";

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
}
