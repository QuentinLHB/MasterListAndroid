package com.example.masterlist;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tools {

    public static String getDateTimeFormat() {
        return "yyyy-MM-dd HH:mm:ss.SSS";
    }

    public static LocalDateTime convertStringDateToLocalDate(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getDateTimeFormat());
        return LocalDateTime.parse(dateString, formatter);
    }

    public static String convertLocalDateToStringDate(LocalDateTime dateTime){
        return dateTime.format(DateTimeFormatter.ofPattern(getDateTimeFormat()));
    }

    public static String getCurrentDateTimeFormattedString(){
        return convertLocalDateToStringDate(LocalDateTime.now());
    }

    /**
     * Converts an integer into a boolean (1 is true, 0 is false, everything else is false).
     * @param i Integer to parse
     * @return true if the passed integer is 1, else returns false.
     */
    public static boolean convertIntToBoolean(int i){
        return i == 1;
    }

    public static int convertBooleanToInt(boolean b){
        return b ? 1 : 0;
    }
}
