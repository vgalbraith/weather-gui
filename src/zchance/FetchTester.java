package zchance;

/**
 * Test class for FetchWeather
 */
public class FetchTester
{
   public static void main(String[] args)
   {
      /*
      // Aeris current conditions test
      FetchWeather f = new FetchWeather("dull, scotland");
      System.out.println(f.toString());
      System.out.println("Error: " + f.getError());
      System.out.println("location: " + f.getLocationName() + ", " + f.getLocationState());
      System.out.println("timestamp: " + f.getFromOb("timestamp"));
      System.out.println("tempF: " + f.getFromOb("tempF"));
      System.out.println("dewpointF: " + f.getFromOb("dewpointF"));
      System.out.println("humidity: " + f.getFromOb("humidity"));
      System.out.println("pressureIN: " + f.getFromOb("pressureIN"));
      System.out.println("windMPH: " + f.getFromOb("windMPH"));
      System.out.println("windDir: " + f.getFromOb("windDir"));
      System.out.println("windDirDEG: " + f.getFromOb("windDirDEG"));
      System.out.println("visibilityMI: " + f.getFromOb("visibilityMI"));
      System.out.println("weather: " + f.getFromOb("weather"));
      System.out.println("icon: " + f.getFromOb("icon"));
      System.out.println("feelslikeF: " + f.getFromOb("feelslikeF"));
      System.out.println("isDay: " + f.getFromOb("isDay"));
      System.out.println("sunrise: " + f.getFromOb("sunrise"));
      System.out.println("sunset: " + f.getFromOb("sunset"));
      System.out.println("snowDepthIN: " + f.getFromOb("snowDepthIN"));
      System.out.println("precipIN: " + f.getFromOb("precipIN"));
      System.out.println("light: " + f.getFromOb("light"));
       */

      // Aeris forecast test
      FetchForecast f2 = new FetchForecast("dull, scotland");
      System.out.println("avgTempF: " + f2.getDayForecasts("avgTempF", 1));

      /*
      // MapBox tests
      FetchMapBox mb = new FetchMapBox("95661");
      mb.fetch();
      System.out.println("Full name: " + mb.getPlaceName(0));
      System.out.println("Lat,Long: " + mb.getCenter(0));

      System.out.println("\n" + mb.getResultsNumber() +  " result(s):");
      for (int i = 0; i < mb.getResultsNumber(); i++)
      {
         System.out.println(mb.getPlaceName(i) + "    " + mb.getCenter(i));
      }
       */
   }
}
