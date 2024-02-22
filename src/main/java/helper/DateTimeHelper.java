package helper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeHelper {

    public static LocalDateTime convertDateObjToLDT(Date date) {
        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate().atStartOfDay();
        } else {
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
    }
}
