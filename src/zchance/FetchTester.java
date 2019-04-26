package zchance;

public class FetchTester
{
   public static void main(String[] args)
   {
      FetchWeather f = new FetchWeather("95661");
      System.out.println(f.toString());
      System.out.println("Location: " + f.getLocationName() + ", " + f.getLocationState());
      System.out.println("tempF: " + f.getFromOb("tempF"));
      System.out.println("windMPH: " + f.getFromOb("windMPH"));
      System.out.println("dewpointF: " + f.getFromOb("dewpointF"));
      System.out.println("feelslikeF: " + f.getFromOb("feelslikeF"));
      System.out.println("humidity: " + f.getFromOb("humidity"));
   }
}
