package zchance;

public class CityFormatter
{
   static String format(String query)
   {
      if (query.equals(":auto"))
      {
         return ":auto";
      }
      query = query.stripLeading();
      query = query.replaceAll(",", " ");
      query = query.replaceAll("  +", " ");

      String[] tokens = query.split( " ");
      StringBuilder ret = new StringBuilder();

      for (int i = 0; i < tokens.length - 1; i++)
      {
         tokens[i] = tokens[i].substring(0, 1).toUpperCase() + tokens[i].substring(1).toLowerCase();
         ret.append(tokens[i]);
         if (i == tokens.length - 2 && !tokens[i].contains(","))
         {
            ret.append(",");
         }
         ret.append(" ");
      }
      ret.append(tokens[tokens.length - 1].toUpperCase());

      return ret.toString();
   }

   public static void main(String[] args)
   {
      String test1 = "san francisco ca";
      String test2 = "roseville, ca";
      String test3 = "95661";
      String test4 = "SACRAMENTO     CA";
      String test5 = "san francisco,ca";

      test1 = CityFormatter.format(test1);
      test2 = CityFormatter.format(test2);
      test3 = CityFormatter.format(test3);
      test4 = CityFormatter.format(test4);
      test5 = CityFormatter.format(test5);

      System.out.println(test1);
      System.out.println(test2);
      System.out.println(test3);
      System.out.println(test4);
      System.out.println(test5);
   }
}
