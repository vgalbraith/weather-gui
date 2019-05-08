package zchance;

public class CityFormatter
{
   public static String format(String query)
   {
      String[] tokens = query.split(" ");
      String ret = "";

      for (int i = 0; i < tokens.length - 1; i++)
      {
         tokens[i] = tokens[i].substring(0, 1).toUpperCase() + tokens[i].substring(1).toLowerCase();
         ret += tokens[i];
         if (i == tokens.length - 2 && !tokens[i].contains(","))
         {
            ret += ",";
         }
         ret += " ";
      }
      ret += tokens[tokens.length - 1].toUpperCase();

      return ret;
   }

   public static void main(String[] args)
   {
      String test1 = "san francisco ca";
      String test2 = "roseville, ca";
      String test3 = "los angeles ca";
      String test4 = "SACRAMENTO CA";

      test1 = CityFormatter.format(test1);
      test2 = CityFormatter.format(test2);
      test3 = CityFormatter.format(test3);
      test4 = CityFormatter.format(test4);

      System.out.println(test1);
      System.out.println(test2);
      System.out.println(test3);
      System.out.println(test4);
   }
}
