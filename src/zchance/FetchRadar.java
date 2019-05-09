package zchance;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

public class FetchRadar {
    private String query;
    String urlString;

    /**
     * Constructs a FetchWeather object
     * @param loc location to fetch
     */
    public FetchRadar(String loc)
    {
        final String CLIENT_ID = "wQhXMMnxoRV4HNKoRLZrL";
        final String CLIENT_SECRET = "JIZobWk2qyfbStTUJSShF1kTLp06WTXLcJKA5dpD";

        if (loc.equals(":auto")) {
            FetchWeather w = new FetchWeather(":auto");
            query = w.getLocation();
        }
        else {
            query = CityFormatter.format(loc);
        }

        try
        {
            if (query.equals(":auto"))
            {
                FetchWeather w = new FetchWeather(":auto");
                query = CityFormatter.format(w.getLocation());
            }
            else
            {
                URLEncoder.encode(query, "UTF-8");
            }

            // Build url
            /** Sample URL:
             *
             * https://maps.aerisapi.com/wQhXMMnxoRV4HNKoRLZrL_JIZobWk2qyfbStTUJSShF1kTLp06WTXLcJKA5dpD/flat-dk,radar,counties,interstates,admin-cities-dk/380x390/Rocklin,CA,8/current.png
             *
             */
            urlString = "https://maps.aerisapi.com/" + CLIENT_ID + "_" + CLIENT_SECRET
                    + "/flat-dk,radar,counties,interstates,admin-cities-dk/380x390/"
                    + query + ",8/current.png";
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public String getImage()
    {
        return urlString;
    }
}
