package ru.grig.ratingRestaurant.controller.web.converters;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static ru.grig.ratingRestaurant.util.DateTimeUtil.parseLocalDate;
import static ru.grig.ratingRestaurant.util.DateTimeUtil.parseLocalTime;

public class LocalTimeFormatter {

    public static class LocalDateFormatter implements Formatter<LocalDate> {
        @Override
        public LocalDate parse(String text, Locale locale) throws ParseException {
            return parseLocalDate(text);
        }

        @Override
        public String print(LocalDate lt, Locale locale) {
            return lt.format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }


//    public static class LocalTimeFormatter implements Formatter<LocalTime> {
//        @Override
//        public LocalTime parse(String text, Locale locale) throws ParseException {
//            return return parseLocalTime(text);;
//        }
//
//        @Override
//        public String print(LocalTime lt, Locale locale) {
//            return lt.format(DateTimeFormatter.ISO_LOCAL_TIME);
//        }
//    }

}
