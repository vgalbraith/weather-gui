package zchance;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class FetchWeather
{
   private final String CLIENT_ID = "wQhXMMnxoRV4HNKoRLZrL";
   private final String CLIENT_SECRET = "rUOW0GEyf5bT9JhUzro2WQAuUpj3A7nFHgVCRGEK";

   private String location;
   private JsonElement results;

   public FetchWeather(String loc)
   {
      try
      {
         // Encode location
         location = URLEncoder.encode(loc, "UTF-8");

         // Build url
         String urlString = "https://api.aerisapi.com/observations/" + location
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

   public String getLocationName()
   {
      return results.getAsJsonObject().get("response").getAsJsonObject()
                    .get("place").getAsJsonObject().get("name")
                    .getAsString();
   }

   public String getLocationState()
   {
      return results.getAsJsonObject().get("response").getAsJsonObject()
              .get("place").getAsJsonObject().get("state")
              .getAsString();
   }

   public String getConditions()
   {
      return results.getAsJsonObject().get("response").getAsJsonObject()
                    .get("ob").getAsJsonObject().get("weather")
                    .getAsString();
   }

   public String getTemperature()
   {
      return results.getAsJsonObject().get("response").getAsJsonObject()
                    .get("ob").getAsJsonObject().get("tempF")
                    .getAsString();
   }
}
