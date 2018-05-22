package database;

import android.arch.persistence.room.TypeConverter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampConverter {
    static DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    @TypeConverter
    public static Date toTimestamp(String value) {
        if (value != null) {
            try {
                return df.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
    }

    @TypeConverter
    public static String fromTimestamp(Date d){
        if(d!=null)
            return  df.format(d);
        else return null;
    }
}