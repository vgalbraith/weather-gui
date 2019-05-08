package zchance;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class CalendarTest
{
   public static void main(String[] args)
   {
      /*
      1557151200 should be mon
      1557237600 should be tues
      1557324000 should be weds
       */

      long timestamp = 1557151200;

      // EEE makes it a shorthand date (i.e. "Mon"), Locale.US gives us our day names
      SimpleDateFormat dateFormat = new SimpleDateFormat("EEE", Locale.US);

      // Needs to be multiplied by 1000 to convert into milliseconds
      System.out.println(dateFormat.format(timestamp * 1000L));
   }
}
