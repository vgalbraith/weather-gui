package zchance;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * This class constructs objects that will be
 * inserted into an array representing the forecast
 * for the seven different days of the week.
 */
public class FetchForecast {
    private String query;
    private JsonElement results;
    private String dayoftheWeek;

    /**
     * Constructs a FetchForecast object which consists of
     * the weather conditions for a specific day of the week
     */
    public FetchForecast(String loc, String day) {
        dayoftheWeek = day;
        final String CLIENT_ID = "wQhXMMnxoRV4HNKoRLZrL";
        final String CLIENT_SECRET = "rUOW0GEyf5bT9JhUzro2WQAuUpj3A7nFHgVCRGEK";

        try
        {
            //Encode location
            query = URLEncoder.encode(loc, "UTF-8");

            //URL concatenation
            String urlString = "https://api.aerisapi.com/forecasts/" + query
                    + "?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET;
            try
            {
                URL url = new URL(urlString);
                // Open streams
                InputStream is = null;
                try {
                    is = url.openStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                // Parse JSON
                results = new JsonParser().parse(br);
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }

        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method is a generic getter that will return a string result
     * of the requested object (i.e. maxTempC, timestamp) using the specified
     * day of the week as a means to access the index
     */
    public String getDayForecasts(String accessLabel)
    {
        int index;
        switch(dayoftheWeek)
        {
            case "Sunday":
                index = 0;
                break;
            case "Monday":
                index = 1;
                break;
            case "Tuesday":
                index = 2;
                break;
            case "Wednesday":
                index = 3;
                break;
            case "Thursday":
                index = 4;
                break;
            case "Friday":
                index = 5;
                break;
            case "Saturday":
                index = 6;
                break;
            default:
                return "Invalid Day of the Week";
        }

        try
        {
            return results.getAsJsonObject().get("response").getAsJsonArray().get(0)
                    .getAsJsonObject().get("periods").getAsJsonArray().get(index)
                    .getAsJsonObject().get(accessLabel).getAsString();
        }
        catch(java.lang.NullPointerException e) //Exception disregards if checked object is empty
        {
        }
        return "";
    }
}
