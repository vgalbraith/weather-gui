package zchance;

import java.io.IOException;
import java.net.URLEncoder;

class FetchRadar
{
    private String urlString;

    /**
     * Constructs a FetchRadar object
     * @param query location to fetch
     */
    FetchRadar(String query)
    {
        try
        {
            if (query.equals(":auto"))
            {
                FetchWeather w = new FetchWeather(":auto");
                query = w.getLocation();
            }
            URLEncoder.encode(query, "UTF-8");

            // Build url
            urlString = "https://maps.aerisapi.com/" + APIKeys.CLIENT_ID + "_" + APIKeys.CLIENT_SECRET
                    + "/flat-dk,radar,counties,interstates,admin-cities-dk/400x400/"
                    + query + ",8/current.png";
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    String getImage()
    {
        return urlString;
    }
}