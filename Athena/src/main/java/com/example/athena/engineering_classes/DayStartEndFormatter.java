package com.example.athena.engineering_classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DayStartEndFormatter {

    private DayStartEndFormatter() {

    }

    public static String formatDayStartEnd(LocalDate day, LocalTime start, LocalTime end) {
        String tutoringDayHours = "" ;
        tutoringDayHours = tutoringDayHours + day.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " - " ;
        tutoringDayHours = tutoringDayHours + start.format(DateTimeFormatter.ofPattern("hh:mm")) + " - " ;
        tutoringDayHours = tutoringDayHours + end.format(DateTimeFormatter.ofPattern("hh:mm")) ;

        return  tutoringDayHours ;
    }
}
