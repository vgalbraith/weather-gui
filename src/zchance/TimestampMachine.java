package zchance;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TimestampMachine
{
    // Instance Variables
    private String day;

    // Constructor
    // Initializes day to ""
    public TimestampMachine()
    {
        day = "";
    }

    public String getDay(long ts)
    {
        long timestamp = ts;

        // EEE makes it a shorthand date (i.e. "Mon"), Locale.US gives us our day names
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE", Locale.US);

        // Needs to be multiplied by 1000 to convert into milliseconds
        day = dateFormat.format(timestamp * 1000L);

        return day;
    }
}
