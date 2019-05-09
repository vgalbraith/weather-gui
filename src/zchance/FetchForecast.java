package zchance;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

/**
 * This class constructs objects that will be
 * inserted into an array representing the forecast
 * for the seven different days of the week.
 */
class FetchForecast {
    private String query;
    private JsonElement results;
    private String dayoftheWeek;

    /**
     * Constructs a FetchForecast object which consists of
     * the weather conditions for a specific day of the week
     */
    FetchForecast(String loc) {
        final String CLIENT_ID = "wQhXMMnxoRV4HNKoRLZrL";
        final String CLIENT_SECRET = "JIZobWk2qyfbStTUJSShF1kTLp06WTXLcJKA5dpD";

        try
        {
            //Encode location
            query = URLEncoder.encode(loc, "UTF-8");

            //URL concatenation
            String urlString = "https://api.aerisapi.com/forecasts/" + query
                    + "?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET;
            URL url = new URL(urlString);

            // Open streams
            InputStream is;
            is = url.openStream();
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
     * This method is a generic getter that will return a string result
     * of the requested object (i.e. maxTempC, timestamp) using the specified
     * day of the week as a means to access the index
     */
    String getDayForecasts(String accessLabel, int index)
    {
        if (index >= 0 && index <= 6) {
            try
            {
                return results.getAsJsonObject().get("response").getAsJsonArray().get(0)
                              .getAsJsonObject().get("periods").getAsJsonArray().get(index)
                              .getAsJsonObject().get(accessLabel).getAsString();
            }
            catch (java.lang.NullPointerException e) //Exception disregards if checked object is empty
            {

            }
            return "";
        }
        else
        {
            return "invalid index";
        }
    }
}
