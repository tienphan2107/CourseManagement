package helper;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateTimeHelper {

    public static LocalDateTime convertDateObjToLDT(Date date) {
        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate().atStartOfDay();
        } else {
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
    }

    public static LocalTime parseTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            return LocalTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }
}
